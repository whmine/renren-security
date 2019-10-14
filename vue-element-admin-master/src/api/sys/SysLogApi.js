import request from '@/utils/request'

export function findByListSysLog(data) {
    return request({
        url: '/api-sys/sys/syslog/list',
        method: 'post',
        data
    })
}

export function findByIdSysLog(data) {
    return request({
        url: '/api-sys/sys/syslog/info',
        method: 'post',
        data
    })
}
export function createSysLog(data) {
    return request({
        url: '/api-sys/sys/syslog/save',
        method: 'post',
        data
    })
}

export function updateSysLog(data) {
    return request({
        url: '/api-sys/sys/syslog/update',
        method: 'post',
        data
    })
}

export function delSysLog(data) {
    return request({
        url: '/api-sys/sys/syslog/delete',
        method: 'post',
        data
    })
}
