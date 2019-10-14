import request from '@/utils/request'

export function findByListDeviceCabinet(data) {
  return request({
    url: '/api-device/device/devicecabinet/list',
    method: 'post',
    data
  })
}

export function findByIdDeviceCabinet(data) {
  return request({
    url: '/api-device/device/devicecabinet/info',
    method: 'post',
    data
  })
}
export function createDeviceCabinet(data) {
  return request({
    url: '/api-device/device/devicecabinet/save',
    method: 'post',
    data
  })
}

export function updateDeviceCabinet(data) {
  return request({
    url: '/api-device/device/devicecabinet/update',
    method: 'post',
    data
  })
}

export function delDeviceCabinet(data) {
  return request({
    url: '/api-device/device/devicecabinet/delete',
    method: 'post',
    data
  })
}

export function pushDeviceCabinet(data) {
  return request({
    url: '/api-device/device/devicecabinet/push',
    method: 'post',
    data
  })
}

