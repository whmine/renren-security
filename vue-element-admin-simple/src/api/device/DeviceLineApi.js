import request from '@/utils/request'

export function findByListDeviceLine(data) {
  return request({
    url: '/api-device/device/deviceline/list',
    method: 'post',
    data
  })
}

export function findByIdDeviceLine(data) {
  return request({
    url: '/api-device/device/deviceline/info',
    method: 'post',
    data
  })
}
export function createDeviceLine(data) {
  return request({
    url: '/api-device/device/deviceline/save',
    method: 'post',
    data
  })
}

export function updateDeviceLine(data) {
  return request({
    url: '/api-device/device/deviceline/update',
    method: 'post',
    data
  })
}

export function delDeviceLine(data) {
  return request({
    url: '/api-device/device/deviceline/delete',
    method: 'post',
    data
  })
}

export function pushDeviceLine(data) {
  return request({
    url: '/api-device/device/deviceline/push',
    method: 'post',
    data
  })
}

