<template>
    <sys-service-dependency-detail  :is-edit="false" />
</template>

<script>
    import SysServiceDependencyDetail from './components/SysServiceDependencyDetail'

    export default {
        name: 'CreateForm',
        components: { SysServiceDependencyDetail }
    }
</script>
