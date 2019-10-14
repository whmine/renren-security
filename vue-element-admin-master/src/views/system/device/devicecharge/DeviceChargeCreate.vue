<template>
    <device-charge-detail  :is-edit="false" />
</template>

<script>
    import DeviceChargeDetail from './components/DeviceChargeDetail'

    export default {
        name: 'CreateForm',
        components: { DeviceChargeDetail }
    }
</script>
