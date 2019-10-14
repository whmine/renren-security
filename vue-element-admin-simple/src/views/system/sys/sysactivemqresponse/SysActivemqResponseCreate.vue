<template>
    <sys-activemq-response-detail  :is-edit="false" />
</template>

<script>
    import SysActivemqResponseDetail from './components/SysActivemqResponseDetail'

    export default {
        name: 'CreateForm',
        components: { SysActivemqResponseDetail }
    }
</script>
