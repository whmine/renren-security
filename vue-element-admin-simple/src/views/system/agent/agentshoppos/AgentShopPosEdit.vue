<template>
    <agent-shop-pos-detail :is-edit="true" />
</template>

<script>
    import AgentShopPosDetail from './components/AgentShopPosDetail'

    export default {
        name: 'EditForm',
        components: { AgentShopPosDetail }
    }
</script>

