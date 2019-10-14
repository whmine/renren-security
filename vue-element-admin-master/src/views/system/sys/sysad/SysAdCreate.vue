<template>
    <sys-ad-detail  :is-edit="false" />
</template>

<script>
    import SysAdDetail from './components/SysAdDetail'

    export default {
        name: 'CreateForm',
        components: { SysAdDetail }
    }
</script>
