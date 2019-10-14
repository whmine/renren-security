<template>
    <cu-profit-detail :is-edit="true" />
</template>

<script>
    import CuProfitDetail from './components/CuProfitDetail'

    export default {
        name: 'EditForm',
        components: { CuProfitDetail }
    }
</script>

