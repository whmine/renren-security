<template>
    <sys-service-detail  :is-edit="false" />
</template>

<script>
    import SysServiceDetail from './components/SysServiceDetail'

    export default {
        name: 'CreateForm',
        components: { SysServiceDetail }
    }
</script>
