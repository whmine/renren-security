<template>
    <sys-resources-detail :is-edit="true" />
</template>

<script>
    import SysResourcesDetail from './components/SysResourcesDetail'

    export default {
        name: 'EditForm',
        components: { SysResourcesDetail }
    }
</script>

