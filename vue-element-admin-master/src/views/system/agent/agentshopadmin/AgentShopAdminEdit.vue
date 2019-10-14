<template>
    <agent-shop-admin-detail :is-edit="true" />
</template>

<script>
    import AgentShopAdminDetail from './components/AgentShopAdminDetail'

    export default {
        name: 'EditForm',
        components: { AgentShopAdminDetail }
    }
</script>

