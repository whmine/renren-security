import Layout from '@/layout'

const SystemResourceRoutes = {
  path: '/systemResource',
  component: Layout,
  redirect: '/systemResource/list',
  name: 'systemResource',
  meta: {
    title: '系统资源配置',
    icon: 'component',
    authzCode: ['sys:systemResource:menu']
  },
  children: [
    {
      path: 'SysServiceCreate',
      component: () => import('@/views/system/sys/sysservice/SysServiceCreate'),
      name: 'CreateSysService',
      hidden: true,
      meta: { title: '系统服务创建', icon: 'edit', authzCode: ['sys:sysservice:save'] }
    },
    {
      path: 'SysServiceEdit/:id',
      component: () => import('@/views/system/sys/sysservice/SysServiceEdit'),
      name: 'EditSysService',
      hidden: true,
      meta: { title: '系统服务编辑', noCache: true, activeMenu: '/SysService/SysServiceList', authzCode: ['sys:sysservice:update'] }

    },
    {
      path: 'SysServiceList',
      component: () => import('@/views/system/sys/sysservice/SysServiceList'),
      name: 'SysServiceList',
      meta: { title: '系统服务管理', icon: 'list', authzCode: ['sys:sysservice:list'] }
    },
    {
      path: 'SysAttachmentCreate',
      component: () => import('@/views/system/sys/sysattachment/SysAttachmentCreate'),
      name: 'CreateSysAttachment',
      hidden: true,
      meta: { title: '系统文件管理创建', icon: 'edit', authzCode: ['sys:sysattachment:save'] }
    },
    {
      path: 'SysAttachmentEdit/:id',
      component: () => import('@/views/system/sys/sysattachment/SysAttachmentEdit'),
      name: 'EditSysAttachment',
      meta: { title: '系统文件管理编辑', noCache: true, activeMenu: '/SysAttachment/SysAttachmentList', authzCode: ['sys:sysattachment:update'] },
      hidden: true
    },
    {
      path: 'SysAttachmentList',
      component: () => import('@/views/system/sys/sysattachment/SysAttachmentList'),
      name: 'SysAttachmentList',
      meta: { title: '系统文件管理管理', icon: 'list', authzCode: ['sys:sysattachment:list'] }
    },
    {
      path: 'SysConfigCreate',
      component: () => import('@/views/system/sys/sysconfig/SysConfigCreate'),
      name: 'CreateSysConfig',
      hidden: true,
      meta: { title: '系统常量创建', icon: 'edit', authzCode: ['sys:sysconfig:save'] }
    },
    {
      path: 'SysConfigEdit/:id',
      component: () => import('@/views/system/sys/sysconfig/SysConfigEdit'),
      name: 'EditSysConfig',
      meta: { title: '系统常量编辑', noCache: true, activeMenu: '/SysConfig/SysConfigList', authzCode: ['sys:sysconfig:update'] },
      hidden: true
    },
    {
      path: 'SysConfigList',
      component: () => import('@/views/system/sys/sysconfig/SysConfigList'),
      name: 'SysConfigList',
      meta: { title: '系统常量管理', icon: 'list', authzCode: ['sys:sysconfig:list'] }
    },
    {
      path: 'SysDictDataCreate',
      component: () => import('@/views/system/sys/sysdictdata/SysDictDataCreate'),
      name: 'CreateSysDictData',
      hidden: true,
      meta: { title: '系统数据字典创建', icon: 'edit', authzCode: ['sys:sysdictdata:save'] }
    },
    {
      path: 'SysDictDataEdit/:id',
      component: () => import('@/views/system/sys/sysdictdata/SysDictDataEdit'),
      name: 'EditSysDictData',
      meta: { title: '系统数据字典编辑', noCache: true, activeMenu: '/SysDictData/SysDictDataList', authzCode: ['sys:sysdictdata:update'] },
      hidden: true
    },
    {
      path: 'SysDictDataList',
      component: () => import('@/views/system/sys/sysdictdata/SysDictDataList'),
      name: 'SysDictDataList',
      meta: { title: '系统数据字典管理', icon: 'list', authzCode: ['sys:sysdictdata:list'] }
    },
    {
      path: 'SysDictOptionCreate/:type',
      component: () => import('@/views/system/sys/sysdictoption/SysDictOptionCreate'),
      name: 'CreateSysDictOption',
      hidden: true,
      meta: { title: '系统数据字典属性创建', icon: 'edit', authzCode: ['sys:sysdictoption:save'] }
    },
    {
      path: 'SysDictOptionEdit/:id',
      component: () => import('@/views/system/sys/sysdictoption/SysDictOptionEdit'),
      name: 'EditSysDictOption',
      meta: { title: '系统数据字典属性编辑', noCache: true, activeMenu: '/SysDictOption/SysDictOptionList', authzCode: ['sys:sysdictoption:update'] },
      hidden: true
    },
    {
      path: 'SysDictOptionList/:type',
      component: () => import('@/views/system/sys/sysdictoption/SysDictOptionList'),
      name: 'SysDictOptionList',
      meta: { title: '系统数据字典属性管理', icon: 'list', authzCode: ['sys:sysdictoption:list'] },
      hidden: true
    },
    {
      path: 'SysNoticeCreate',
      component: () => import('@/views/system/sys/sysnotice/SysNoticeCreate'),
      name: 'CreateSysNotice',
      hidden: true,
      meta: { title: '系统公告表创建', icon: 'edit', authzCode: ['sys:sysnotice:save'] }
    },
    {
      path: 'SysNoticeEdit/:id',
      component: () => import('@/views/system/sys/sysnotice/SysNoticeEdit'),
      name: 'EditSysNotice',
      meta: { title: '系统公告表编辑', noCache: true, activeMenu: '/SysNotice/SysNoticeList', authzCode: ['sys:sysnotice:update'] },
      hidden: true
    },
    {
      path: 'SysNoticeList',
      component: () => import('@/views/system/sys/sysnotice/SysNoticeList'),
      name: 'SysNoticeList',
      meta: { title: '系统公告表管理', icon: 'list', authzCode: ['sys:sysnotice:list'] }
    },
    {
      path: 'SysAdCreate',
      component: () => import('@/views/system/sys/sysad/SysAdCreate'),
      name: 'CreateSysAd',
      hidden: true,
      meta: { title: '广告轮播图创建', icon: 'edit', authzCode: ['sys:sysad:save'] }
    },
    {
      path: 'SysAdEdit/:id',
      component: () => import('@/views/system/sys/sysad/SysAdEdit'),
      name: 'EditSysAd',
      meta: { title: '广告轮播图编辑', noCache: true, activeMenu: '/SysAd/SysAdList', authzCode: ['sys:sysad:update'] },
      hidden: true
    },
    {
      path: 'SysAdList',
      component: () => import('@/views/system/sys/sysad/SysAdList'),
      name: 'SysAdList',
      meta: { title: '广告轮播图管理', icon: 'list', authzCode: ['sys:sysad:list'] }
    }
  ]
}

export default SystemResourceRoutes
