<template>
    <sys-authorities-detail  :is-edit="false" />
</template>

<script>
    import SysAuthoritiesDetail from './components/SysAuthoritiesDetail'

    export default {
        name: 'CreateForm',
        components: { SysAuthoritiesDetail }
    }
</script>
