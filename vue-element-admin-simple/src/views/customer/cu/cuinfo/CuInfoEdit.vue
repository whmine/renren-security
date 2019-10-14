<template>
    <cu-info-detail :is-edit="true" />
</template>

<script>
    import CuInfoDetail from './components/CuInfoDetail'

    export default {
        name: 'EditForm',
        components: { CuInfoDetail }
    }
</script>

