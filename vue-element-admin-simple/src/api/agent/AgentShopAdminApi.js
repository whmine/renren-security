import request from '@/utils/request'

export function findByListAgentShopAdmin(data) {
  return request({
    url: '/api-agent/agent/agentshopadmin/list',
    method: 'post',
    data
  })
}

export function findByIdAgentShopAdmin(data) {
  return request({
    url: '/api-agent/agent/agentshopadmin/info',
    method: 'post',
    data
  })
}
export function createAgentShopAdmin(data) {
  return request({
    url: '/api-agent/agent/agentshopadmin/save',
    method: 'post',
    data
  })
}

export function updateAgentShopAdmin(data) {
  return request({
    url: '/api-agent/agent/agentshopadmin/update',
    method: 'post',
    data
  })
}

export function delAgentShopAdmin(data) {
  return request({
    url: '/api-agent/agent/agentshopadmin/delete',
    method: 'post',
    data
  })
}

export function pushAgentShopAdmin(data) {
  return request({
    url: '/api-agent/agent/agentshopadmin/push',
    method: 'post',
    data
  })
}

