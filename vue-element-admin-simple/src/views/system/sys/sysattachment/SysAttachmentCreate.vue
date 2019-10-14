<template>
    <sys-attachment-detail  :is-edit="false" />
</template>

<script>
    import SysAttachmentDetail from './components/SysAttachmentDetail'

    export default {
        name: 'CreateForm',
        components: { SysAttachmentDetail }
    }
</script>
