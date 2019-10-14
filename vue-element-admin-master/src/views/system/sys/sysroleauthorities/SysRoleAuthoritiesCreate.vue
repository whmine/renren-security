<template>
    <sys-role-authorities-detail  :is-edit="false" />
</template>

<script>
    import SysRoleAuthoritiesDetail from './components/SysRoleAuthoritiesDetail'

    export default {
        name: 'CreateForm',
        components: { SysRoleAuthoritiesDetail }
    }
</script>
