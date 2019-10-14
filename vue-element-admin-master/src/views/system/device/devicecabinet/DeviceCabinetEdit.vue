<template>
    <device-cabinet-detail :is-edit="true" />
</template>

<script>
    import DeviceCabinetDetail from './components/DeviceCabinetDetail'

    export default {
        name: 'EditForm',
        components: { DeviceCabinetDetail }
    }
</script>

