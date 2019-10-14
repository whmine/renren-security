import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: [],
  authzCodes: [],
  user: {}
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_AUTHZCODES: (state, authzCodes) => {
    state.authzCodes = authzCodes
  },
  SET_SYSUSER: (state, sysUser) => {
    state.user = sysUser
  }

}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo({}).then(response => {
        var sysAuthzCodes = JSON.parse(response.data.authzCodes)
        var sysUser = JSON.parse(response.data.sysUser)
        var cuInfo = JSON.parse(response.data.cuInfo)
        var sysRoles = JSON.parse(response.data.sysRoles)
        var data = {
          user: sysUser,
          roles: sysRoles,
          authzCodes: sysAuthzCodes,
          introduction: 'I am a super administrator',
          avatar: cuInfo.customerHead,
          name: cuInfo.customerName
        }
        // if (!data) {
        //   reject('Verification failed, please Login again.')
        // }

        const { user, roles, authzCodes, name, avatar, introduction } = data

        // roles must be a non-empty array
        // if (!roles || roles.length <= 0) {
        //   reject('getInfo: roles must be a non-null array!')
        // }

        commit('SET_ROLES', roles)
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        commit('SET_INTRODUCTION', introduction)
        commit('SET_AUTHZCODES', authzCodes)
        commit('SET_SYSUSER', user)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout({}).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  changeRoles({ commit, dispatch }, role) {
    return new Promise(async resolve => {
      const token = role + '-token'

      commit('SET_TOKEN', token)
      setToken(token)

      const { roles, authzCodes } = await dispatch('getInfo')

      resetRouter()

      // generate accessible routes map based on roles
      const accessRoutes = await dispatch('permission/generateRoutes', { roles: roles, authzCodes: authzCodes }, { root: true })

      // dynamically add accessible routes
      router.addRoutes(accessRoutes)

      // reset visited views and cached views
      dispatch('tagsView/delAllViews', null, { root: true })

      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
