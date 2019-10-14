<template>
    <cu-sign-detail :is-edit="true" />
</template>

<script>
    import CuSignDetail from './components/CuSignDetail'

    export default {
        name: 'EditForm',
        components: { CuSignDetail }
    }
</script>

