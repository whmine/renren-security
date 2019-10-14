<template>
    <device-charge-detail :is-edit="true" />
</template>

<script>
    import DeviceChargeDetail from './components/DeviceChargeDetail'

    export default {
        name: 'EditForm',
        components: { DeviceChargeDetail }
    }
</script>

