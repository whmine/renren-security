<template>
    <device-charge-log-detail  :is-edit="false" />
</template>

<script>
    import DeviceChargeLogDetail from './components/DeviceChargeLogDetail'

    export default {
        name: 'CreateForm',
        components: { DeviceChargeLogDetail }
    }
</script>
