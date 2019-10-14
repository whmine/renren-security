<template>
    <sys-role-authorities-detail :is-edit="true" />
</template>

<script>
    import SysRoleAuthoritiesDetail from './components/SysRoleAuthoritiesDetail'

    export default {
        name: 'EditForm',
        components: { SysRoleAuthoritiesDetail }
    }
</script>

