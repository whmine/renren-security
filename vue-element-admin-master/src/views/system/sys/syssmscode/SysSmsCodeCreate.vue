<template>
    <sys-sms-code-detail  :is-edit="false" />
</template>

<script>
    import SysSmsCodeDetail from './components/SysSmsCodeDetail'

    export default {
        name: 'CreateForm',
        components: { SysSmsCodeDetail }
    }
</script>
