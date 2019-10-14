import request from '@/utils/request'

export function findByListDeviceCharge(data) {
    return request({
        url: '/api-device/device/devicecharge/list',
        method: 'post',
        data
    })
}

export function findByIdDeviceCharge(data) {
    return request({
        url: '/api-device/device/devicecharge/info',
        method: 'post',
        data
    })
}
export function createDeviceCharge(data) {
    return request({
        url: '/api-device/device/devicecharge/save',
        method: 'post',
        data
    })
}

export function updateDeviceCharge(data) {
    return request({
        url: '/api-device/device/devicecharge/update',
        method: 'post',
        data
    })
}

export function delDeviceCharge(data) {
    return request({
        url: '/api-device/device/devicecharge/delete',
        method: 'post',
        data
    })
}

export function pushDeviceCharge(data) {
    return request({
        url: '/api-device/device/devicecharge/push',
        method: 'post',
        data
    })
}

