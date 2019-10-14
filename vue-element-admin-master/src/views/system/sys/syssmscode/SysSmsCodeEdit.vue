<template>
    <sys-sms-code-detail :is-edit="true" />
</template>

<script>
    import SysSmsCodeDetail from './components/SysSmsCodeDetail'

    export default {
        name: 'EditForm',
        components: { SysSmsCodeDetail }
    }
</script>

