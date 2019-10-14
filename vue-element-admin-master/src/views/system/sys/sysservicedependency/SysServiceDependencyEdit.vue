<template>
    <sys-service-dependency-detail :is-edit="true" />
</template>

<script>
    import SysServiceDependencyDetail from './components/SysServiceDependencyDetail'

    export default {
        name: 'EditForm',
        components: { SysServiceDependencyDetail }
    }
</script>

