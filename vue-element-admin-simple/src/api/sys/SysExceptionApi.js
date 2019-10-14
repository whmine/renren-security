import request from '@/utils/request'

export function findByListSysException(data) {
    return request({
        url: '/api-sys/sys/sysexception/list',
        method: 'post',
        data
    })
}

export function findByIdSysException(data) {
    return request({
        url: '/api-sys/sys/sysexception/info',
        method: 'post',
        data
    })
}
export function createSysException(data) {
    return request({
        url: '/api-sys/sys/sysexception/save',
        method: 'post',
        data
    })
}

export function updateSysException(data) {
    return request({
        url: '/api-sys/sys/sysexception/update',
        method: 'post',
        data
    })
}

export function delSysException(data) {
    return request({
        url: '/api-sys/sys/sysexception/delete',
        method: 'post',
        data
    })
}
