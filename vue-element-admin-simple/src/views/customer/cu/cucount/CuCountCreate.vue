<template>
    <cu-count-detail  :is-edit="false" />
</template>

<script>
    import CuCountDetail from './components/CuCountDetail'

    export default {
        name: 'CreateForm',
        components: { CuCountDetail }
    }
</script>
