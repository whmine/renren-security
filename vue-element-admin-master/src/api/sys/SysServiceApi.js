import request from '@/utils/request'

export function findByListSysService(data) {
    return request({
        url: '/api-sys/sys/sysservice/list',
        method: 'post',
        data
    })
}

export function findByIdSysService(data) {
    return request({
        url: '/api-sys/sys/sysservice/info',
        method: 'post',
        data
    })
}
export function createSysService(data) {
    return request({
        url: '/api-sys/sys/sysservice/save',
        method: 'post',
        data
    })
}

export function updateSysService(data) {
    return request({
        url: '/api-sys/sys/sysservice/update',
        method: 'post',
        data
    })
}

export function delSysService(data) {
    return request({
        url: '/api-sys/sys/sysservice/delete',
        method: 'post',
        data
    })
}

export function pushSysService(data) {
    return request({
        url: '/api-sys/sys/sysservice/push',
        method: 'post',
        data
    })
}

