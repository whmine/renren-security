import request from '@/utils/request'

export function findByListSysActivemqResponse(data) {
  return request({
    url: '/api-sys/sys/sysactivemqresponse/list',
    method: 'post',
    data
  })
}

export function findByIdSysActivemqResponse(data) {
  return request({
    url: '/api-sys/sys/sysactivemqresponse/info',
    method: 'post',
    data
  })
}
export function createSysActivemqResponse(data) {
  return request({
    url: '/api-sys/sys/sysactivemqresponse/save',
    method: 'post',
    data
  })
}

export function updateSysActivemqResponse(data) {
  return request({
    url: '/api-sys/sys/sysactivemqresponse/update',
    method: 'post',
    data
  })
}

export function delSysActivemqResponse(data) {
  return request({
    url: '/api-sys/sys/sysactivemqresponse/delete',
    method: 'post',
    data
  })
}

export function pushSysActivemqResponse(data) {
  return request({
    url: '/api-sys/sys/sysactivemqresponse/push',
    method: 'post',
    data
  })
}

