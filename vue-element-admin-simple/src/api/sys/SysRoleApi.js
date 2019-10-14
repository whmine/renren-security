import request from '@/utils/request'

export function findByListSysRole(data) {
    return request({
        url: '/api-sys/sys/sysrole/list',
        method: 'post',
        data
    })
}

export function findByIdSysRole(data) {
    return request({
        url: '/api-sys/sys/sysrole/info',
        method: 'post',
        data
    })
}
export function createSysRole(data) {
    return request({
        url: '/api-sys/sys/sysrole/save',
        method: 'post',
        data
    })
}

export function updateSysRole(data) {
    return request({
        url: '/api-sys/sys/sysrole/update',
        method: 'post',
        data
    })
}

export function delSysRole(data) {
    return request({
        url: '/api-sys/sys/sysrole/delete',
        method: 'post',
        data
    })
}

export function pushSysRole(data) {
    return request({
        url: '/api-sys/sys/sysrole/push',
        method: 'post',
        data
    })
}

