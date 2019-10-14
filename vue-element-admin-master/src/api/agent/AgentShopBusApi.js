import request from '@/utils/request'

export function findByListAgentShopBus(data) {
  return request({
    url: '/api-agent/agent/agentshopbus/list',
    method: 'post',
    data
  })
}

export function findByIdAgentShopBus(data) {
  return request({
    url: '/api-agent/agent/agentshopbus/info',
    method: 'post',
    data
  })
}
export function createAgentShopBus(data) {
  return request({
    url: '/api-agent/agent/agentshopbus/save',
    method: 'post',
    data
  })
}

export function updateAgentShopBus(data) {
  return request({
    url: '/api-agent/agent/agentshopbus/update',
    method: 'post',
    data
  })
}

export function delAgentShopBus(data) {
  return request({
    url: '/api-agent/agent/agentshopbus/delete',
    method: 'post',
    data
  })
}

export function pushAgentShopBus(data) {
  return request({
    url: '/api-agent/agent/agentshopbus/push',
    method: 'post',
    data
  })
}

