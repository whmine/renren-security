import Layout from '@/layout'

const Customers = {
  path: '/customer',
  component: Layout,
  redirect: '/customer/list',
  name: 'customer',
  meta: {
    title: '客户信息管理',
    icon: 'peoples',
    authzCode: ['cu:customer:menu']
  },
  children: [
    {
      path: 'CuAccountCreate',
      component: () => import('@/views/customer/cu/cuaccount/CuAccountCreate'),
      name: 'CreateCuAccount',
      hidden: true,
      meta: { title: '客户账目信息创建', icon: 'edit', authzCode: ['cu:cuaccount:save'] }
    },
    {
      path: 'CuAccountEdit/:id',
      component: () => import('@/views/customer/cu/cuaccount/CuAccountEdit'),
      name: 'EditCuAccount',
      meta: { title: '客户账目信息编辑', noCache: true, activeMenu: '/CuAccount/CuAccountList', authzCode: ['cu:cuaccount:update'] },
      hidden: true
    },
    {
      path: 'CuAccountList',
      component: () => import('@/views/customer/cu/cuaccount/CuAccountList'),
      name: 'CuAccountList',
      meta: { title: '客户账目信息管理', icon: 'list', authzCode: ['cu:cuaccount:list'] }
    }, {
      path: 'CuCountCreate',
      component: () => import('@/views/customer/cu/cucount/CuCountCreate'),
      name: 'CreateCuCount',
      hidden: true,
      meta: { title: '客户统计信息创建', icon: 'edit', authzCode: ['cu:cucount:save'] }
    },
    {
      path: 'CuCountEdit/:id',
      component: () => import('@/views/customer/cu/cucount/CuCountEdit'),
      name: 'EditCuCount',
      meta: { title: '客户统计信息编辑', noCache: true, activeMenu: '/CuCount/CuCountList', authzCode: ['cu:cucount:update'] },
      hidden: true
    },
    {
      path: 'CuCountList',
      component: () => import('@/views/customer/cu/cucount/CuCountList'),
      name: 'CuCountList',
      meta: { title: '客户统计信息管理', icon: 'list', authzCode: ['cu:cucount:list'] }
    }, {
      path: 'CuInfoCreate',
      component: () => import('@/views/customer/cu/cuinfo/CuInfoCreate'),
      name: 'CreateCuInfo',
      hidden: true,
      meta: { title: '客户基础信息创建', icon: 'edit', authzCode: ['cu:cuinfo:save'] }
    },
    {
      path: 'CuInfoEdit/:id',
      component: () => import('@/views/customer/cu/cuinfo/CuInfoEdit'),
      name: 'EditCuInfo',
      meta: { title: '客户基础信息编辑', noCache: true, activeMenu: '/CuInfo/CuInfoList', authzCode: ['cu:cuinfo:update'] },
      hidden: true
    },
    {
      path: 'CuInfoList',
      component: () => import('@/views/customer/cu/cuinfo/CuInfoList'),
      name: 'CuInfoList',
      meta: { title: '客户基础信息管理', icon: 'list', authzCode: ['cu:cuinfo:list'] }
    }, {
      path: 'CuProfitCreate',
      component: () => import('@/views/customer/cu/cuprofit/CuProfitCreate'),
      name: 'CreateCuProfit',
      hidden: true,
      meta: { title: '客户收益信息创建', icon: 'edit', authzCode: ['cu:cuprofit:save'] }
    },
    {
      path: 'CuProfitEdit/:id',
      component: () => import('@/views/customer/cu/cuprofit/CuProfitEdit'),
      name: 'EditCuProfit',
      meta: { title: '客户收益信息编辑', noCache: true, activeMenu: '/CuProfit/CuProfitList', authzCode: ['cu:cuprofit:update'] },
      hidden: true
    },
    {
      path: 'CuProfitList',
      component: () => import('@/views/customer/cu/cuprofit/CuProfitList'),
      name: 'CuProfitList',
      meta: { title: '客户收益信息管理', icon: 'list', authzCode: ['cu:cuprofit:list'] }
    }, {
      path: 'CuSignCreate',
      component: () => import('@/views/customer/cu/cusign/CuSignCreate'),
      name: 'CreateCuSign',
      hidden: true,
      meta: { title: '客户签到创建', icon: 'edit', authzCode: ['cu:cusign:save'] }
    },
    {
      path: 'CuSignEdit/:id',
      component: () => import('@/views/customer/cu/cusign/CuSignEdit'),
      name: 'EditCuSign',
      meta: { title: '客户签到编辑', noCache: true, activeMenu: '/CuSign/CuSignList', authzCode: ['cu:cusign:update'] },
      hidden: true
    },
    {
      path: 'CuSignList',
      component: () => import('@/views/customer/cu/cusign/CuSignList'),
      name: 'CuSignList',
      meta: { title: '客户签到管理', icon: 'list', authzCode: ['cu:cusign:list'] }
    }
  ]
}
export default Customers