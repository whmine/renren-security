<template>
    <sys-oauth-code-detail  :is-edit="false" />
</template>

<script>
    import SysOauthCodeDetail from './components/SysOauthCodeDetail'

    export default {
        name: 'CreateForm',
        components: { SysOauthCodeDetail }
    }
</script>
