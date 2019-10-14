<template>
    <agent-shop-bus-detail  :is-edit="false" />
</template>

<script>
    import AgentShopBusDetail from './components/AgentShopBusDetail'

    export default {
        name: 'CreateForm',
        components: { AgentShopBusDetail }
    }
</script>
