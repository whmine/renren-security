<template>
    <device-line-detail  :is-edit="false" />
</template>

<script>
    import DeviceLineDetail from './components/DeviceLineDetail'

    export default {
        name: 'CreateForm',
        components: { DeviceLineDetail }
    }
</script>
