import request from '@/utils/request'
// var pathUser = '/api-token'
var pathUser = 'api-simple'

export function login(data) {
  return request({
    url: pathUser + '/api/token/loginSys',
    method: 'post',
    data
  })
}

export function getInfo(data) {
  return request({
    url: pathUser + '/api/token/info',
    method: 'post',
    data
  })
}

export function logout(data) {
  return request({
    url: pathUser + '/api/token/logOut',
    method: 'post',
    data
  })
}
