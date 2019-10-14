<template>
    <sys-activemq-request-detail  :is-edit="false" />
</template>

<script>
    import SysActivemqRequestDetail from './components/SysActivemqRequestDetail'

    export default {
        name: 'CreateForm',
        components: { SysActivemqRequestDetail }
    }
</script>
