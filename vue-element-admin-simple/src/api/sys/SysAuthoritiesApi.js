import request from '@/utils/request'

export function findByListSysAuthorities(data) {
    return request({
        url: '/api-sys/sys/sysauthorities/list',
        method: 'post',
        data
    })
}

export function findByIdSysAuthorities(data) {
    return request({
        url: '/api-sys/sys/sysauthorities/info',
        method: 'post',
        data
    })
}
export function createSysAuthorities(data) {
    return request({
        url: '/api-sys/sys/sysauthorities/save',
        method: 'post',
        data
    })
}

export function updateSysAuthorities(data) {
    return request({
        url: '/api-sys/sys/sysauthorities/update',
        method: 'post',
        data
    })
}

export function delSysAuthorities(data) {
    return request({
        url: '/api-sys/sys/sysauthorities/delete',
        method: 'post',
        data
    })
}

export function pushSysAuthorities(data) {
    return request({
        url: '/api-sys/sys/sysauthorities/push',
        method: 'post',
        data
    })
}

