<template>
  <img :src="tempUrl" min-width="25" height="25" />
</template>
<script>
import { findByNameSysConfig } from "@/api/sys/SysConfigApi";
import { findByIdSysAttachment } from "@/api/sys/SysAttachmentApi";
import { getToken } from "@/utils/auth";
import { debuglog } from "util";
export default {
  name: "ElTableImg",
  props: {
    value: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      tempUrl: "",
      pathUrl: "",
      headers: { token: getToken() }
    };
  },
  created() {
    findByNameSysConfig({ name: "PROFILE" })
      .then(response => {
        this.pathUrl = response.data.value;
      })
      .catch(err => {
        console.log(err);
      });
    findByIdSysAttachment({ id: this.value })
      .then(response => {
        if (response.code == 0) {
          this.tempUrl = this.pathUrl + response.data.urlPath;
        }
      })
      .catch(err => {
        console.log(err);
      });
  },
  computed: {
    imageUrl() {
      return this.tempUrl;
    }
  },
  methods: {
    rmImage() {},
    emitInput(val) {},
    handleImageSuccess(res) {},
    beforeUpload() {}
  }
};
</script>