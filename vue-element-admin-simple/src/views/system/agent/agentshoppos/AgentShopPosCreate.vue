<template>
    <agent-shop-pos-detail  :is-edit="false" />
</template>

<script>
    import AgentShopPosDetail from './components/AgentShopPosDetail'

    export default {
        name: 'CreateForm',
        components: { AgentShopPosDetail }
    }
</script>
