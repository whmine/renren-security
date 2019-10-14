<template>
    <device-cabinet-detail  :is-edit="false" />
</template>

<script>
    import DeviceCabinetDetail from './components/DeviceCabinetDetail'

    export default {
        name: 'CreateForm',
        components: { DeviceCabinetDetail }
    }
</script>
