<template>
    <agent-shop-detail :is-edit="true" />
</template>

<script>
    import AgentShopDetail from './components/AgentShopDetail'

    export default {
        name: 'EditForm',
        components: { AgentShopDetail }
    }
</script>

