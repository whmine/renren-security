<template>
    <device-charge-log-detail :is-edit="true" />
</template>

<script>
    import DeviceChargeLogDetail from './components/DeviceChargeLogDetail'

    export default {
        name: 'EditForm',
        components: { DeviceChargeLogDetail }
    }
</script>

