import request from '@/utils/request'

export function findByListSysNotice(data) {
    return request({
        url: '/api-sys/sys/sysnotice/list',
        method: 'post',
        data
    })
}

export function findByIdSysNotice(data) {
    return request({
        url: '/api-sys/sys/sysnotice/info',
        method: 'post',
        data
    })
}
export function createSysNotice(data) {
    return request({
        url: '/api-sys/sys/sysnotice/save',
        method: 'post',
        data
    })
}

export function updateSysNotice(data) {
    return request({
        url: '/api-sys/sys/sysnotice/update',
        method: 'post',
        data
    })
}

export function delSysNotice(data) {
    return request({
        url: '/api-sys/sys/sysnotice/delete',
        method: 'post',
        data
    })
}

export function pushSysNotice(data) {
    return request({
        url: '/api-sys/sys/sysnotice/push',
        method: 'post',
        data
    })
}

