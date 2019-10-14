<template>
    <sys-user-role-detail  :is-edit="false" />
</template>

<script>
    import SysUserRoleDetail from './components/SysUserRoleDetail'

    export default {
        name: 'CreateForm',
        components: { SysUserRoleDetail }
    }
</script>
