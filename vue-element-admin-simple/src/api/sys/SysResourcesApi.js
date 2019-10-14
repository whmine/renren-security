import request from '@/utils/request'

export function findByListSysResources(data) {
    return request({
        url: '/api-sys/sys/sysresources/list',
        method: 'post',
        data
    })
}

export function findByIdSysResources(data) {
    return request({
        url: '/api-sys/sys/sysresources/info',
        method: 'post',
        data
    })
}
export function createSysResources(data) {
    return request({
        url: '/api-sys/sys/sysresources/save',
        method: 'post',
        data
    })
}

export function updateSysResources(data) {
    return request({
        url: '/api-sys/sys/sysresources/update',
        method: 'post',
        data
    })
}

export function delSysResources(data) {
    return request({
        url: '/api-sys/sys/sysresources/delete',
        method: 'post',
        data
    })
}

export function pushSysResources(data) {
    return request({
        url: '/api-sys/sys/sysresources/push',
        method: 'post',
        data
    })
}

