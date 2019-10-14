<template>
    <device-cabinet-log-detail :is-edit="true" />
</template>

<script>
    import DeviceCabinetLogDetail from './components/DeviceCabinetLogDetail'

    export default {
        name: 'EditForm',
        components: { DeviceCabinetLogDetail }
    }
</script>

