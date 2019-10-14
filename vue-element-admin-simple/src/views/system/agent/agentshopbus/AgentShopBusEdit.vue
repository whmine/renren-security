<template>
    <agent-shop-bus-detail :is-edit="true" />
</template>

<script>
    import AgentShopBusDetail from './components/AgentShopBusDetail'

    export default {
        name: 'EditForm',
        components: { AgentShopBusDetail }
    }
</script>

