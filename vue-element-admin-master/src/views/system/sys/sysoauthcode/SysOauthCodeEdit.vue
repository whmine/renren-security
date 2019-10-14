<template>
    <sys-oauth-code-detail :is-edit="true" />
</template>

<script>
    import SysOauthCodeDetail from './components/SysOauthCodeDetail'

    export default {
        name: 'EditForm',
        components: { SysOauthCodeDetail }
    }
</script>

