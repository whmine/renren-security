import request from '@/utils/request'

export function findByListSysActivemqRequest(data) {
  return request({
    url: '/api-sys/sys/sysactivemqrequest/list',
    method: 'post',
    data
  })
}

export function findByIdSysActivemqRequest(data) {
  return request({
    url: '/api-sys/sys/sysactivemqrequest/info',
    method: 'post',
    data
  })
}
export function createSysActivemqRequest(data) {
  return request({
    url: '/api-sys/sys/sysactivemqrequest/save',
    method: 'post',
    data
  })
}

export function updateSysActivemqRequest(data) {
  return request({
    url: '/api-sys/sys/sysactivemqrequest/update',
    method: 'post',
    data
  })
}

export function delSysActivemqRequest(data) {
  return request({
    url: '/api-sys/sys/sysactivemqrequest/delete',
    method: 'post',
    data
  })
}

export function pushSysActivemqRequest(data) {
  return request({
    url: '/api-sys/sys/sysactivemqrequest/push',
    method: 'post',
    data
  })
}

