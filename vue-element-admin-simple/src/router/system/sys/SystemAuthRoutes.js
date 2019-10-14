import Layout from '@/layout'

const SystemAuthRoutes = {
  path: '/systemAuth',
  component: Layout,
  redirect: '/systemAuth/list',
  name: 'systemAuth',
  meta: {
    title: '系统权限配置',
    icon: 'size',
    authzCode: ['sys:systemAuth:menu']
  },
  children: [
    {
      path: 'SysResourcesCreate',
      component: () => import('@/views/system/sys/sysresources/SysResourcesCreate'),
      name: 'CreateSysResources',
      hidden: true,
      meta: { title: '系统资源创建', icon: 'edit', authzCode: ['sys:sysresources:save'] }
    },
    {
      path: 'SysResourcesEdit/:id',
      component: () => import('@/views/system/sys/sysresources/SysResourcesEdit'),
      name: 'EditSysResources',
      meta: { title: '系统资源编辑', noCache: true, activeMenu: '/SysResources/SysResourcesList', authzCode: ['sys:sysresources:update'] },
      hidden: true
    },
    {
      path: 'SysResourcesList',
      component: () => import('@/views/system/sys/sysresources/SysResourcesList'),
      name: 'SysResourcesList',
      meta: { title: '系统资源管理', icon: 'list', authzCode: ['sys:sysresources:list'] }
    },
    {
      path: 'SysAuthoritiesResourcesCreate',
      component: () => import('@/views/system/sys/sysauthoritiesresources/SysAuthoritiesResourcesCreate'),
      name: 'CreateSysAuthoritiesResources',
      meta: { title: '系统权限资源关系创建', icon: 'edit', authzCode: ['sys:sysauthoritiesresources:save'] },
      hidden: true
    },
    {
      path: 'SysAuthoritiesResourcesEdit/:id',
      component: () => import('@/views/system/sys/sysauthoritiesresources/SysAuthoritiesResourcesEdit'),
      name: 'EditSysAuthoritiesResources',
      meta: { title: '系统权限资源关系编辑', noCache: true, activeMenu: '/SysAuthoritiesResources/SysAuthoritiesResourcesList', authzCode: ['sys:sysauthoritiesresources:update'] },
      hidden: true
    },
    {
      path: 'SysAuthoritiesResourcesList/:sysAuthoritiesId',
      component: () => import('@/views/system/sys/sysauthoritiesresources/SysAuthoritiesResourcesList'),
      name: 'SysAuthoritiesResourcesList',
      meta: { title: '系统权限资源关系管理', icon: 'list', authzCode: ['sys:sysauthoritiesresources:list'] },
      hidden: true
    },
    {
      path: 'SysAuthoritiesCreate',
      component: () => import('@/views/system/sys/sysauthorities/SysAuthoritiesCreate'),
      name: 'CreateSysAuthorities',
      hidden: true,
      meta: { title: '系统权限创建', icon: 'edit', authzCode: ['sys:sysauthorities:save'] }
    },
    {
      path: 'SysAuthoritiesEdit/:id',
      component: () => import('@/views/system/sys/sysauthorities/SysAuthoritiesEdit'),
      name: 'EditSysAuthorities',
      meta: { title: '系统权限编辑', noCache: true, activeMenu: '/SysAuthorities/SysAuthoritiesList', authzCode: ['sys:sysauthorities:update'] },
      hidden: true
    },
    {
      path: 'SysAuthoritiesList',
      component: () => import('@/views/system/sys/sysauthorities/SysAuthoritiesList'),
      name: 'SysAuthoritiesList',
      meta: { title: '系统权限管理', icon: 'list', authzCode: ['sys:sysauthorities:list'] }
    },
    {
      path: 'SysRoleAuthoritiesCreate',
      component: () => import('@/views/system/sys/sysroleauthorities/SysRoleAuthoritiesCreate'),
      name: 'CreateSysRoleAuthorities',
      meta: { title: '系统角色权限关系创建', icon: 'edit', authzCode: ['sys:sysroleauthorities:save'] },
      hidden: true
    },
    {
      path: 'SysRoleAuthoritiesEdit/:id',
      component: () => import('@/views/system/sys/sysroleauthorities/SysRoleAuthoritiesEdit'),
      name: 'EditSysRoleAuthorities',
      meta: { title: '系统角色权限关系编辑', noCache: true, activeMenu: '/SysRoleAuthorities/SysRoleAuthoritiesList', authzCode: ['sys:sysroleauthorities:update'] },
      hidden: true
    },
    {
      path: 'SysRoleAuthoritiesList/:sysRoleId',
      component: () => import('@/views/system/sys/sysroleauthorities/SysRoleAuthoritiesList'),
      name: 'SysRoleAuthoritiesList',
      meta: { title: '系统角色权限关系管理', icon: 'list', authzCode: ['sys:sysroleauthorities:list'] },
      hidden: true
    },
    {
      path: 'SysRoleCreate',
      component: () => import('@/views/system/sys/sysrole/SysRoleCreate'),
      name: 'CreateSysRole',
      hidden: true,
      meta: { title: '系统角色创建', icon: 'edit', authzCode: ['sys:sysrole:save'] }
    },
    {
      path: 'SysRoleEdit/:id',
      component: () => import('@/views/system/sys/sysrole/SysRoleEdit'),
      name: 'EditSysRole',
      meta: { title: '系统角色编辑', noCache: true, activeMenu: '/SysRole/SysRoleList', authzCode: ['sys:sysrole:update'] },
      hidden: true
    },
    {
      path: 'SysRoleList',
      component: () => import('@/views/system/sys/sysrole/SysRoleList'),
      name: 'SysRoleList',
      meta: { title: '系统角色管理', icon: 'list', authzCode: ['sys:sysrole:list'] }
    },
    {
      path: 'SysUserRoleCreate',
      component: () => import('@/views/system/sys/sysuserrole/SysUserRoleCreate'),
      name: 'CreateSysUserRole',
      meta: { title: '系统用户角色关系创建', icon: 'edit', authzCode: ['sys:sysuserrole:save'] },
      hidden: true
    },
    {
      path: 'SysUserRoleEdit/:id',
      component: () => import('@/views/system/sys/sysuserrole/SysUserRoleEdit'),
      name: 'EditSysUserRole',
      meta: { title: '系统用户角色关系编辑', noCache: true, activeMenu: '/SysUserRole/SysUserRoleList', authzCode: ['sys:sysuserrole:update'] },
      hidden: true
    },
    {
      path: 'SysUserRoleList/:sysUserId',
      component: () => import('@/views/system/sys/sysuserrole/SysUserRoleList'),
      name: 'SysUserRoleList',
      meta: { title: '系统用户角色关系管理', icon: 'list', authzCode: ['sys:sysuserrole:list'] },
      hidden: true
    },
    {
      path: 'SysUserCreate',
      component: () => import('@/views/system/sys/sysuser/SysUserCreate'),
      name: 'CreateSysUser',
      hidden: true,
      meta: { title: '系统用户表创建', icon: 'edit', authzCode: ['sys:sysuser:save'] }
    },
    {
      path: 'SysUserEdit/:id',
      component: () => import('@/views/system/sys/sysuser/SysUserEdit'),
      name: 'EditSysUser',
      meta: { title: '系统用户表编辑', noCache: true, activeMenu: '/SysUser/SysUserList', authzCode: ['sys:sysuser:update'] },
      hidden: true
    },
    {
      path: 'SysUserList',
      component: () => import('@/views/system/sys/sysuser/SysUserList'),
      name: 'SysUserList',
      meta: { title: '系统用户表管理', icon: 'list', authzCode: ['sys:sysuser:list'] }
    }

  ]
}

export default SystemAuthRoutes
