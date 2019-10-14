import Layout from '@/layout'

const AgentResources = {
  path: '/agentResource',
  component: Layout,
  redirect: '/agentResource/list',
  name: 'agentResource',
  meta: {
    title: '代理商管理',
    icon: 'shopping',
    authzCode: ['agent:agentResource:menu']
  },
  children: [
    {
      path: 'AgentShopCreate',
      component: () => import('@/views/system/agent/agentshop/AgentShopCreate'),
      name: 'CreateAgentShop',
      hidden: true,
      meta: { title: '代理商创建', icon: 'edit', authzCode: ['agent:agentshop:save'] }
    },
    {
      path: 'AgentShopEdit/:id',
      component: () => import('@/views/system/agent/agentshop/AgentShopEdit'),
      name: 'EditAgentShop',
      meta: { title: '代理商编辑', noCache: true, activeMenu: '/AgentShop/AgentShopList', authzCode: ['agent:agentshop:update'] },
      hidden: true
    },
    {
      path: 'AgentShopList',
      component: () => import('@/views/system/agent/agentshop/AgentShopList'),
      name: 'AgentShopList',
      meta: { title: '代理商管理', icon: 'list', authzCode: ['agent:agentshop:list'] }
    },
    {
      path: 'AgentShopPosCreate',
      component: () => import('@/views/system/agent/agentshoppos/AgentShopPosCreate'),
      name: 'CreateAgentShopPos',
      hidden: true,
      meta: { title: '代理商商户创建', icon: 'edit', authzCode: ['agent:agentshoppos:save'] }
    },
    {
      path: 'AgentShopPosEdit/:id',
      component: () => import('@/views/system/agent/agentshoppos/AgentShopPosEdit'),
      name: 'EditAgentShopPos',
      meta: { title: '代理商商户编辑', noCache: true, activeMenu: '/AgentShopPos/AgentShopPosList', authzCode: ['agent:agentshoppos:update'] },
      hidden: true
    },
    {
      path: 'AgentShopPosList',
      component: () => import('@/views/system/agent/agentshoppos/AgentShopPosList'),
      name: 'AgentShopPosList',
      meta: { title: '代理商商户管理', icon: 'list', authzCode: ['agent:agentshoppos:list'] }
    }, {
      path: 'AgentShopAdminCreate',
      component: () => import('@/views/system/agent/agentshopadmin/AgentShopAdminCreate'),
      name: 'CreateAgentShopAdmin',
      hidden: true,
      meta: { title: '代理商管理员创建', icon: 'edit', authzCode: ['agent:agentshopadmin:save'] }
    },
    {
      path: 'AgentShopAdminEdit/:id',
      component: () => import('@/views/system/agent/agentshopadmin/AgentShopAdminEdit'),
      name: 'EditAgentShopAdmin',
      meta: { title: '代理商管理员编辑', noCache: true, activeMenu: '/AgentShopAdmin/AgentShopAdminList', authzCode: ['agent:agentshopadmin:update'] },
      hidden: true
    },
    {
      path: 'AgentShopAdminList',
      component: () => import('@/views/system/agent/agentshopadmin/AgentShopAdminList'),
      name: 'AgentShopAdminList',
      meta: { title: '代理商管理员管理', icon: 'list', authzCode: ['agent:agentshopadmin:list'] }
    }, {
      path: 'AgentShopBusCreate',
      component: () => import('@/views/system/agent/agentshopbus/AgentShopBusCreate'),
      name: 'CreateAgentShopBus',
      hidden: true,
      meta: { title: '代理商业务员创建', icon: 'edit', authzCode: ['agent:agentshopbus:save'] }
    },
    {
      path: 'AgentShopBusEdit/:id',
      component: () => import('@/views/system/agent/agentshopbus/AgentShopBusEdit'),
      name: 'EditAgentShopBus',
      meta: { title: '代理商业务员编辑', noCache: true, activeMenu: '/AgentShopBus/AgentShopBusList', authzCode: ['agent:agentshopbus:update'] },
      hidden: true
    },
    {
      path: 'AgentShopBusList',
      component: () => import('@/views/system/agent/agentshopbus/AgentShopBusList'),
      name: 'AgentShopBusList',
      meta: { title: '代理商业务员管理', icon: 'list', authzCode: ['agent:agentshopbus:list'] }
    }
  ]
}

export default AgentResources