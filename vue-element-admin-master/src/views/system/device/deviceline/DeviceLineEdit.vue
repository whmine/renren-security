<template>
    <device-line-detail :is-edit="true" />
</template>

<script>
    import DeviceLineDetail from './components/DeviceLineDetail'

    export default {
        name: 'EditForm',
        components: { DeviceLineDetail }
    }
</script>

