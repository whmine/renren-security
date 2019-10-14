<template>
    <sys-attachment-detail :is-edit="true" />
</template>

<script>
    import SysAttachmentDetail from './components/SysAttachmentDetail'

    export default {
        name: 'EditForm',
        components: { SysAttachmentDetail }
    }
</script>

