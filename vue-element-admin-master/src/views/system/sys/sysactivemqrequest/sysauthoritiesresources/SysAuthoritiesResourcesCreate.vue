<template>
    <sys-authorities-resources-detail  :is-edit="false" />
</template>

<script>
    import SysAuthoritiesResourcesDetail from './components/SysAuthoritiesResourcesDetail'

    export default {
        name: 'CreateForm',
        components: { SysAuthoritiesResourcesDetail }
    }
</script>
