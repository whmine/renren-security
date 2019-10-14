<template>
  <div class="upload-container">
    <el-upload
      :data="dataObj"
      :multiple="false"
      :show-file-list="false"
      :on-success="handleImageSuccess"
      :before-upload="beforeUpload"
      :headers="headers"
      class="image-uploader"
      drag
      action="/prod-api/api-sys/sys/sysattachment/uploadImage"
    >
      <i class="el-icon-upload" />
      <div class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
    </el-upload>
    <div class="image-preview">
      <div v-show="imageUrl.length>1" class="image-preview-wrapper">
        <video :src="imageUrl+'?imageView2/1/w/200/h/200'" controls="controls" width="198px" height="198px"/>
        <!-- <img :src="imageUrl+'?imageView2/1/w/200/h/200'" /> -->
        <div class="image-preview-action">
          <i class="el-icon-delete" @click="rmImage" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { findByNameSysConfig } from "@/api/sys/SysConfigApi";
import {
  findByIdSysAttachment,
  delSysAttachment
} from "@/api/sys/SysAttachmentApi";
import { debuglog } from "util";
import { getToken } from "@/utils/auth";
export default {
  name: "SingleImageUpload",
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
      headers: { token: getToken() },
      dataObj: {}
    };
  },
  watch: {
    value: function(n, o) {
      debugger;
      if (n !== "" && n !== null) {
        findByIdSysAttachment({ id: n })
          .then(response => {
            debugger;
            if (response.code == 0) {
              if (response.data !== "" && response.data !== null) {
                this.tempUrl = this.pathUrl + response.data.urlPath;
                this.emitInput(response.data.id);
              } else {
                this.emitInput("");
                this.tempUrl = "";
              }
            }
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.tempUrl = "";
        this.emitInput("");
      }
    }
  },
  created() {
    findByNameSysConfig({ name: "PROFILE" })
      .then(response => {
        this.pathUrl = response.data.value;
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
    deleteSysAttachment(data) {
      delSysAttachment({ id: data }).then(response => {
        this.$notify({
          title: "成功",
          message: "删除成功",
          type: "success",
          duration: 2000
        });
      });
    },
    rmImage() {
      this.deleteSysAttachment(this.value);
      this.emitInput("");
    },
    emitInput(val) {
      this.$emit("input", val);
    },
    handleImageSuccess(res) {
      if (res.code == 0) {
        this.tempUrl = this.pathUrl + res.data.urlPath;
        this.emitInput(res.data.id);
      }
    },
    beforeUpload() {}
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";
.upload-container {
  width: 100%;
  position: relative;
  @include clearfix;
  .image-uploader {
    width: 60%;
    float: left;
  }
  .image-preview {
    width: 200px;
    height: 200px;
    position: relative;
    border: 1px dashed #d9d9d9;
    float: left;
    margin-left: 50px;
    .image-preview-wrapper {
      position: relative;
      width: 100%;
      height: 100%;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .image-preview-action {
      position: absolute;
      width: 100%;
      height: 100%;
      left: 0;
      top: 0;
      cursor: default;
      text-align: center;
      color: #fff;
      opacity: 0;
      font-size: 20px;
      background-color: rgba(0, 0, 0, 0.5);
      transition: opacity 0.3s;
      cursor: pointer;
      text-align: center;
      line-height: 200px;
      .el-icon-delete {
        font-size: 36px;
      }
    }
    &:hover {
      .image-preview-action {
        opacity: 1;
      }
    }
  }
}
</style>
