<template>
    <cu-sign-detail  :is-edit="false" />
</template>

<script>
    import CuSignDetail from './components/CuSignDetail'

    export default {
        name: 'CreateForm',
        components: { CuSignDetail }
    }
</script>
