import request from '@/utils/request'

export function findByListSysServiceDependency(data) {
    return request({
        url: '/api-sys/sys/sysservicedependency/list',
        method: 'post',
        data
    })
}

export function findByIdSysServiceDependency(data) {
    return request({
        url: '/api-sys/sys/sysservicedependency/info',
        method: 'post',
        data
    })
}
export function createSysServiceDependency(data) {
    return request({
        url: '/api-sys/sys/sysservicedependency/save',
        method: 'post',
        data
    })
}

export function updateSysServiceDependency(data) {
    return request({
        url: '/api-sys/sys/sysservicedependency/update',
        method: 'post',
        data
    })
}

export function delSysServiceDependency(data) {
    return request({
        url: '/api-sys/sys/sysservicedependency/delete',
        method: 'post',
        data
    })
}

export function pushSysServiceDependency(data) {
    return request({
        url: '/api-sys/sys/sysservicedependency/push',
        method: 'post',
        data
    })
}

