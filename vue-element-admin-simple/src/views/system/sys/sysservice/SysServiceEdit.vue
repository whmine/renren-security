<template>
    <sys-service-detail :is-edit="true" />
</template>

<script>
    import SysServiceDetail from './components/SysServiceDetail'

    export default {
        name: 'EditForm',
        components: { SysServiceDetail }
    }
</script>

