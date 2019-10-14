<template>
    <sys-authorities-detail :is-edit="true" />
</template>

<script>
    import SysAuthoritiesDetail from './components/SysAuthoritiesDetail'

    export default {
        name: 'EditForm',
        components: { SysAuthoritiesDetail }
    }
</script>

