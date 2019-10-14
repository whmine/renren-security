<template>
    <sys-ad-detail :is-edit="true" />
</template>

<script>
    import SysAdDetail from './components/SysAdDetail'

    export default {
        name: 'EditForm',
        components: { SysAdDetail }
    }
</script>

