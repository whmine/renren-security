import request from '@/utils/request'

export function findByListDeviceCabinetLog(data) {
  return request({
    url: '/api-device/device/devicecabinetlog/list',
    method: 'post',
    data
  })
}

export function findByIdDeviceCabinetLog(data) {
  return request({
    url: '/api-device/device/devicecabinetlog/info',
    method: 'post',
    data
  })
}
export function createDeviceCabinetLog(data) {
  return request({
    url: '/api-device/device/devicecabinetlog/save',
    method: 'post',
    data
  })
}

export function updateDeviceCabinetLog(data) {
  return request({
    url: '/api-device/device/devicecabinetlog/update',
    method: 'post',
    data
  })
}

export function delDeviceCabinetLog(data) {
  return request({
    url: '/api-device/device/devicecabinetlog/delete',
    method: 'post',
    data
  })
}

export function pushDeviceCabinetLog(data) {
  return request({
    url: '/api-device/device/devicecabinetlog/push',
    method: 'post',
    data
  })
}

