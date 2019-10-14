<template>
    <cu-account-detail  :is-edit="false" />
</template>

<script>
    import CuAccountDetail from './components/CuAccountDetail'

    export default {
        name: 'CreateForm',
        components: { CuAccountDetail }
    }
</script>
