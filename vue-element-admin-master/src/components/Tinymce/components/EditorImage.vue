<template>
  <div class="upload-container">
    <el-button
      :style="{background:color,borderColor:color}"
      icon="el-icon-upload"
      size="mini"
      type="primary"
      @click=" dialogVisible=true"
    >上传图片</el-button>
    <el-dialog :visible.sync="dialogVisible">
      <el-upload
        :multiple="true"
        :file-list="fileList"
        :show-file-list="true"
        :on-remove="handleRemove"
        :on-success="handleSuccess"
        :before-upload="beforeUpload"
        :headers="headers"
        :data="data"
        class="editor-slide-upload"
        action="/prod-api/api-sys/sys/sysattachment/uploadImage"
        list-type="picture-card"
      >
        <el-button size="small" type="primary">上传图片</el-button>
      </el-upload>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit">确定</el-button>
    </el-dialog>
  </div>
</template>

<script>
import { findByNameSysConfig } from "@/api/sys/SysConfigApi";
import {
  findByIdSysAttachment,
  delSysAttachment
} from "@/api/sys/SysAttachmentApi";
import { getToken } from "@/utils/auth";
export default {
  name: "EditorSlideUpload",
  props: {
    color: {
      type: String,
      default: "#1890ff"
    }
  },
  data() {
    return {
      dialogVisible: false,
      pathUrl: "",
      listObj: {},
      fileList: [],
      headers: { token: getToken() },
      data: {}
    };
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
    checkAllSuccess() {
      return Object.keys(this.listObj).every(
        item => this.listObj[item].hasSuccess
      );
    },
    handleSubmit() {
      const arr = Object.keys(this.listObj).map(v => this.listObj[v]);
      if (!this.checkAllSuccess()) {
        this.$message(
          "Please wait for all images to be uploaded successfully. If there is a network problem, please refresh the page and upload again!"
        );
        return;
      }
      this.$emit("successCBK", arr);
      this.listObj = {};
      this.fileList = [];
      this.dialogVisible = false;
    },
    handleSuccess(response, file) {
      debugger;
      const uid = file.uid;
      const objKeyArr = Object.keys(this.listObj);
      for (let i = 0, len = objKeyArr.length; i < len; i++) {
        if (this.listObj[objKeyArr[i]].uid === uid) {
          this.listObj[objKeyArr[i]].url = this.pathUrl + response.data.urlPath;
          this.listObj[objKeyArr[i]].hasSuccess = true;
          return;
        }
      }
    },
    handleRemove(file) {
      debugger;
      const uid = file.uid;
      const objKeyArr = Object.keys(this.listObj);
      for (let i = 0, len = objKeyArr.length; i < len; i++) {
        if (this.listObj[objKeyArr[i]].uid === uid) {
          delete this.listObj[objKeyArr[i]];
          this.deleteSysAttachment(file.response.data.id);
          return;
        }
      }
    },
    beforeUpload(file) {
      const _self = this;
      const _URL = window.URL || window.webkitURL;
      const fileName = file.uid;
      this.listObj[fileName] = {};
      return new Promise((resolve, reject) => {
        findByNameSysConfig({ name: "PROFILE" })
          .then(response => {
            this.pathUrl = response.data.value;
            resolve(true);
          })
          .catch(err => {
            console.log(err);
            reject(false);
          });

        const img = new Image();
        img.src = _URL.createObjectURL(file);
        img.onload = function() {
          _self.listObj[fileName] = {
            hasSuccess: false,
            uid: file.uid,
            width: this.width,
            height: this.height
          };
        };
        resolve(true);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.editor-slide-upload {
  margin-bottom: 20px;
  /deep/ .el-upload--picture-card {
    width: 100%;
  }
}
</style>
