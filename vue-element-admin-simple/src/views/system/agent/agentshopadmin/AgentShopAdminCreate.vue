<template>
    <agent-shop-admin-detail  :is-edit="false" />
</template>

<script>
    import AgentShopAdminDetail from './components/AgentShopAdminDetail'

    export default {
        name: 'CreateForm',
        components: { AgentShopAdminDetail }
    }
</script>
