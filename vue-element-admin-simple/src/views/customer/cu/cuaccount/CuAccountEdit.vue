<template>
    <cu-account-detail :is-edit="true" />
</template>

<script>
    import CuAccountDetail from './components/CuAccountDetail'

    export default {
        name: 'EditForm',
        components: { CuAccountDetail }
    }
</script>

