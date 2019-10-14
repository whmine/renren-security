<template>
    <cu-profit-detail  :is-edit="false" />
</template>

<script>
    import CuProfitDetail from './components/CuProfitDetail'

    export default {
        name: 'CreateForm',
        components: { CuProfitDetail }
    }
</script>
