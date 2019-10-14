import request from '@/utils/request'

export function findByListSysAd(data) {
    return request({
        url: '/api-sys/sys/sysad/list',
        method: 'post',
        data
    })
}

export function findByIdSysAd(data) {
    return request({
        url: '/api-sys/sys/sysad/info',
        method: 'post',
        data
    })
}
export function createSysAd(data) {
    return request({
        url: '/api-sys/sys/sysad/save',
        method: 'post',
        data
    })
}

export function updateSysAd(data) {
    return request({
        url: '/api-sys/sys/sysad/update',
        method: 'post',
        data
    })
}

export function delSysAd(data) {
    return request({
        url: '/api-sys/sys/sysad/delete',
        method: 'post',
        data
    })
}

export function pushSysAd(data) {
    return request({
        url: '/api-sys/sys/sysad/push',
        method: 'post',
        data
    })
}

