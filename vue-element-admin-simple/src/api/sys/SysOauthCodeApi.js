import request from '@/utils/request'

export function findByListSysOauthCode(data) {
    return request({
        url: '/api-sys/sys/sysoauthcode/list',
        method: 'post',
        data
    })
}

export function findByIdSysOauthCode(data) {
    return request({
        url: '/api-sys/sys/sysoauthcode/info',
        method: 'post',
        data
    })
}
export function createSysOauthCode(data) {
    return request({
        url: '/api-sys/sys/sysoauthcode/save',
        method: 'post',
        data
    })
}

export function updateSysOauthCode(data) {
    return request({
        url: '/api-sys/sys/sysoauthcode/update',
        method: 'post',
        data
    })
}

export function delSysOauthCode(data) {
    return request({
        url: '/api-sys/sys/sysoauthcode/delete',
        method: 'post',
        data
    })
}

export function pushSysOauthCode(data) {
    return request({
        url: '/api-sys/sys/sysoauthcode/push',
        method: 'post',
        data
    })
}

