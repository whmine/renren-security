<template>
    <cu-count-detail :is-edit="true" />
</template>

<script>
    import CuCountDetail from './components/CuCountDetail'

    export default {
        name: 'EditForm',
        components: { CuCountDetail }
    }
</script>

