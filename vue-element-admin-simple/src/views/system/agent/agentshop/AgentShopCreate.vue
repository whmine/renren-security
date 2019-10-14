<template>
    <agent-shop-detail  :is-edit="false" />
</template>

<script>
    import AgentShopDetail from './components/AgentShopDetail'

    export default {
        name: 'CreateForm',
        components: { AgentShopDetail }
    }
</script>
