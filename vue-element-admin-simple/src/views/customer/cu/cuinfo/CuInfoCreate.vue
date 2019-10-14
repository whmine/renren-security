<template>
    <cu-info-detail  :is-edit="false" />
</template>

<script>
    import CuInfoDetail from './components/CuInfoDetail'

    export default {
        name: 'CreateForm',
        components: { CuInfoDetail }
    }
</script>
