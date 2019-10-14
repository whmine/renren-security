import request from '@/utils/request'

export function findByListSysSmsCode(data) {
    return request({
        url: '/api-sys/sys/syssmscode/list',
        method: 'post',
        data
    })
}

export function findByIdSysSmsCode(data) {
    return request({
        url: '/api-sys/sys/syssmscode/info',
        method: 'post',
        data
    })
}
export function createSysSmsCode(data) {
    return request({
        url: '/api-sys/sys/syssmscode/save',
        method: 'post',
        data
    })
}

export function updateSysSmsCode(data) {
    return request({
        url: '/api-sys/sys/syssmscode/update',
        method: 'post',
        data
    })
}

export function delSysSmsCode(data) {
    return request({
        url: '/api-sys/sys/syssmscode/delete',
        method: 'post',
        data
    })
}

export function pushSysSmsCode(data) {
    return request({
        url: '/api-sys/sys/syssmscode/push',
        method: 'post',
        data
    })
}

