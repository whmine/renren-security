import { asyncRoutes, constantRoutes } from '@/router'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(authzCodes, route) {
  if (route.meta && route.meta.authzCode) {
    return authzCodes.some(authzCode => route.meta.authzCode.includes(authzCode))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, authzCodes) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(authzCodes, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, authzCodes)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, param) {
    var roles = param.roles
    var authzCodes = param.authzCodes
    var user = param.user
    return new Promise(resolve => {
      let accessedRoutes
      if (user.isSys == 'Y') {
        accessedRoutes = asyncRoutes || []
      } else {
        accessedRoutes = filterAsyncRoutes(asyncRoutes, authzCodes)
      }
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
