<template>
    <sys-dict-option-detail  :is-edit="false" />
</template>

<script>
    import SysDictOptionDetail from './components/SysDictOptionDetail'

    export default {
        name: 'CreateForm',
        components: { SysDictOptionDetail }
    }
</script>
