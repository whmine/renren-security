<template>
    <device-cabinet-log-detail  :is-edit="false" />
</template>

<script>
    import DeviceCabinetLogDetail from './components/DeviceCabinetLogDetail'

    export default {
        name: 'CreateForm',
        components: { DeviceCabinetLogDetail }
    }
</script>
