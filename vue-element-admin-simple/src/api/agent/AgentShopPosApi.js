import request from '@/utils/request'

export function findByListAgentShopPos(data) {
    return request({
        url: '/api-agent/agent/agentshoppos/list',
        method: 'post',
        data
    })
}

export function findByIdAgentShopPos(data) {
    return request({
        url: '/api-agent/agent/agentshoppos/info',
        method: 'post',
        data
    })
}
export function createAgentShopPos(data) {
    return request({
        url: '/api-agent/agent/agentshoppos/save',
        method: 'post',
        data
    })
}

export function updateAgentShopPos(data) {
    return request({
        url: '/api-agent/agent/agentshoppos/update',
        method: 'post',
        data
    })
}

export function delAgentShopPos(data) {
    return request({
        url: '/api-agent/agent/agentshoppos/delete',
        method: 'post',
        data
    })
}

export function pushAgentShopPos(data) {
    return request({
        url: '/api-agent/agent/agentshoppos/push',
        method: 'post',
        data
    })
}

