<template>
    <sys-activemq-request-detail :is-edit="true" />
</template>

<script>
    import SysActivemqRequestDetail from './components/SysActivemqRequestDetail'

    export default {
        name: 'EditForm',
        components: { SysActivemqRequestDetail }
    }
</script>

