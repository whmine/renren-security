import request from '@/utils/request'

export function findByListAgentShop(data) {
    return request({
        url: '/api-agent/agent/agentshop/list',
        method: 'post',
        data
    })
}

export function findByIdAgentShop(data) {
    return request({
        url: '/api-agent/agent/agentshop/info',
        method: 'post',
        data
    })
}
export function createAgentShop(data) {
    return request({
        url: '/api-agent/agent/agentshop/save',
        method: 'post',
        data
    })
}

export function updateAgentShop(data) {
    return request({
        url: '/api-agent/agent/agentshop/update',
        method: 'post',
        data
    })
}

export function delAgentShop(data) {
    return request({
        url: '/api-agent/agent/agentshop/delete',
        method: 'post',
        data
    })
}

export function pushAgentShop(data) {
    return request({
        url: '/api-agent/agent/agentshop/push',
        method: 'post',
        data
    })
}

