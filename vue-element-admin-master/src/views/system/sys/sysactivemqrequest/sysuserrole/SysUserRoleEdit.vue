<template>
    <sys-user-role-detail :is-edit="true" />
</template>

<script>
    import SysUserRoleDetail from './components/SysUserRoleDetail'

    export default {
        name: 'EditForm',
        components: { SysUserRoleDetail }
    }
</script>

