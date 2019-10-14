import Layout from '@/layout'

const DeviceResources = {
  path: '/deviceResource',
  component: Layout,
  redirect: '/deviceResource/list',
  name: 'deviceResource',
  meta: {
    title: '设备管理',
    icon: 'example',
    authzCode: ['shop:deviceResource:menu']
  },
  children: [
    {
      path: 'DeviceCabinetCreate',
      component: () => import('@/views/system/device/devicecabinet/DeviceCabinetCreate'),
      name: 'CreateDeviceCabinet',
      hidden: true,
      meta: { title: '机柜创建', icon: 'edit', authzCode: ['device:devicecabinet:save'] }
    },
    {
      path: 'DeviceCabinetEdit/:id',
      component: () => import('@/views/system/device/devicecabinet/DeviceCabinetEdit'),
      name: 'EditDeviceCabinet',
      meta: { title: '机柜编辑', noCache: true, activeMenu: '/DeviceCabinet/DeviceCabinetList', authzCode: ['device:devicecabinet:update'] },
      hidden: true
    },
    {
      path: 'DeviceCabinetList',
      component: () => import('@/views/system/device/devicecabinet/DeviceCabinetList'),
      name: 'DeviceCabinetList',
      meta: { title: '机柜管理', icon: 'list', authzCode: ['device:devicecabinet:list'] }
    },
    {
      path: 'DeviceCabinetLogCreate',
      component: () => import('@/views/system/device/devicecabinetlog/DeviceCabinetLogCreate'),
      name: 'CreateDeviceCabinetLog',
      hidden: true,
      meta: { title: '机柜租借创建', icon: 'edit', authzCode: ['device:devicecabinetlog:save'] }
    },
    {
      path: 'DeviceCabinetLogEdit/:id',
      component: () => import('@/views/system/device/devicecabinetlog/DeviceCabinetLogEdit'),
      name: 'EditDeviceCabinetLog',
      meta: { title: '机柜租借编辑', noCache: true, activeMenu: '/DeviceCabinetLog/DeviceCabinetLogList', authzCode: ['device:devicecabinetlog:update'] },
      hidden: true
    },
    {
      path: 'DeviceCabinetLogList',
      component: () => import('@/views/system/device/devicecabinetlog/DeviceCabinetLogList'),
      name: 'DeviceCabinetLogList',
      hidden: true,
      meta: { title: '机柜租借管理', icon: 'list', authzCode: ['device:devicecabinetlog:list'] }
    },
    {
      path: 'DeviceChargeCreate',
      component: () => import('@/views/system/device/devicecharge/DeviceChargeCreate'),
      name: 'CreateDeviceCharge',
      hidden: true,
      meta: { title: '充电宝创建', icon: 'edit', authzCode: ['device:devicecharge:save'] }
    },
    {
      path: 'DeviceChargeEdit/:id',
      component: () => import('@/views/system/device/devicecharge/DeviceChargeEdit'),
      name: 'EditDeviceCharge',
      meta: { title: '充电宝编辑', noCache: true, activeMenu: '/DeviceCharge/DeviceChargeList', authzCode: ['device:devicecharge:update'] },
      hidden: true
    },
    {
      path: 'DeviceChargeList',
      component: () => import('@/views/system/device/devicecharge/DeviceChargeList'),
      name: 'DeviceChargeList',
      meta: { title: '充电宝管理', icon: 'list', authzCode: ['device:devicecharge:list'] }
    },
    {
      path: 'DeviceChargeLogCreate',
      component: () => import('@/views/system/device/devicechargelog/DeviceChargeLogCreate'),
      name: 'CreateDeviceChargeLog',
      hidden: true,
      meta: { title: '充电宝租借创建', icon: 'edit', authzCode: ['device:devicechargelog:save'] }
    },
    {
      path: 'DeviceChargeLogEdit/:id',
      component: () => import('@/views/system/device/devicechargelog/DeviceChargeLogEdit'),
      name: 'EditDeviceChargeLog',
      meta: { title: '充电宝租借编辑', noCache: true, activeMenu: '/DeviceChargeLog/DeviceChargeLogList', authzCode: ['device:devicechargelog:update'] },
      hidden: true
    },
    {
      path: 'DeviceChargeLogList',
      component: () => import('@/views/system/device/devicechargelog/DeviceChargeLogList'),
      name: 'DeviceChargeLogList',
      hidden: true,
      meta: { title: '充电宝租借管理', icon: 'list', authzCode: ['device:devicechargelog:list'] }
    },
    {
      path: 'DeviceLineCreate',
      component: () => import('@/views/system/device/deviceline/DeviceLineCreate'),
      name: 'CreateDeviceLine',
      hidden: true,
      meta: { title: '密码线创建', icon: 'edit', authzCode: ['device:deviceline:save'] }
    },
    {
      path: 'DeviceLineEdit/:id',
      component: () => import('@/views/system/device/deviceline/DeviceLineEdit'),
      name: 'EditDeviceLine',
      meta: { title: '密码线编辑', noCache: true, activeMenu: '/DeviceLine/DeviceLineList', authzCode: ['device:deviceline:update'] },
      hidden: true
    },
    {
      path: 'DeviceLineList',
      component: () => import('@/views/system/device/deviceline/DeviceLineList'),
      name: 'DeviceLineList',
      meta: { title: '密码线管理', icon: 'list', authzCode: ['device:deviceline:list'] }
    }
  ]
}

export default DeviceResources