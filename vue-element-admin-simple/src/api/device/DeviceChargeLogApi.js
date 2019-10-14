import request from '@/utils/request'

export function findByListDeviceChargeLog(data) {
  return request({
    url: '/api-device/device/devicechargelog/list',
    method: 'post',
    data
  })
}

export function findByIdDeviceChargeLog(data) {
  return request({
    url: '/api-device/device/devicechargelog/info',
    method: 'post',
    data
  })
}
export function createDeviceChargeLog(data) {
  return request({
    url: '/api-device/device/devicechargelog/save',
    method: 'post',
    data
  })
}

export function updateDeviceChargeLog(data) {
  return request({
    url: '/api-device/device/devicechargelog/update',
    method: 'post',
    data
  })
}

export function delDeviceChargeLog(data) {
  return request({
    url: '/api-device/device/devicechargelog/delete',
    method: 'post',
    data
  })
}

export function pushDeviceChargeLog(data) {
  return request({
    url: '/api-device/device/devicechargelog/push',
    method: 'post',
    data
  })
}

