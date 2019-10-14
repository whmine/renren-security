<template>
    <sys-authorities-resources-detail :is-edit="true" />
</template>

<script>
    import SysAuthoritiesResourcesDetail from './components/SysAuthoritiesResourcesDetail'

    export default {
        name: 'EditForm',
        components: { SysAuthoritiesResourcesDetail }
    }
</script>

