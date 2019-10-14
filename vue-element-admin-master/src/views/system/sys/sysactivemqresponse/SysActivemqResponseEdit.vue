<template>
    <sys-activemq-response-detail :is-edit="true" />
</template>

<script>
    import SysActivemqResponseDetail from './components/SysActivemqResponseDetail'

    export default {
        name: 'EditForm',
        components: { SysActivemqResponseDetail }
    }
</script>

