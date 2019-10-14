<template>
    <sys-resources-detail  :is-edit="false" />
</template>

<script>
    import SysResourcesDetail from './components/SysResourcesDetail'

    export default {
        name: 'CreateForm',
        components: { SysResourcesDetail }
    }
</script>
