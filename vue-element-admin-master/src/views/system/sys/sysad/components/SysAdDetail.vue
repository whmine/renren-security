<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['sys:sysad:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['sys:sysad:save']"
          @click="draftForm"
        >确认</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item style="margin-bottom: 40px;" prop="adName" label-width="90px" label="广告名字:">
          <el-input
            v-model="postForm.adName"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写广告名字"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="adLink" label-width="90px" label="链接地址:">
          <el-input
            v-model="postForm.adLink"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写链接地址"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="adCode" label-width="90px" label="图片地址:">
          <!-- <el-input
            v-model="postForm.adCode"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写图片地址"
          />-->
          <Upload v-model="postForm.adCode" />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="type" label-width="90px" label="类型:">
          <el-select v-model="postForm.type" placeholder="类型">
            <el-option
              v-for="item in sysAdTypeListOptions"
              :key="item.name"
              :label="item.cnName"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="enabled" label-width="90px" label="是否显示:">
          <el-select v-model="postForm.enabled" placeholder="是否显示">
            <el-option
              v-for="item in sysAdShowListOptions"
              :key="item.name"
              :label="item.cnName"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="startTime"
          label-width="90px"
          label="开始时间:"
        >
          <el-date-picker
            v-model="postForm.startTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            placeholder="请填写开始时间"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="endTime" label-width="90px" label="结束时间:">
          <el-date-picker
            v-model="postForm.endTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            placeholder="请填写开始时间"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="clickCount"
          label-width="90px"
          label="点击量:"
        >
          <el-input
            v-model="postForm.clickCount"
            :rows="1"
            :disabled="true"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写点击量"
          />
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import permission from "@/directive/permission/index.js"; // 权限判断指令
import checkPermission from "@/utils/permission"; // 权限判断函数
import Tinymce from "@/components/Tinymce";
import Upload from "@/components/Upload/SingleImage3";
import Sticky from "@/components/Sticky"; // 粘性header组件
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import {
  findByIdSysAd,
  createSysAd,
  updateSysAd,
  pushSysAd
} from "@/api/sys/SysAdApi";

const defaultForm = {
  id: undefined,
  adName: "",
  adLink: "",
  adCode: "",
  type: "",
  linkPhone: "",
  startTime: "",
  endTime: "",
  clickCount: 0,
  enabled: ""
};

export default {
  name: "SysAdDetail",
  components: { Tinymce, Upload, Sticky },
  directives: { permission },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === "") {
        this.$message({
          message: rule.field + "为必传项",
          type: "error"
        });
        callback(new Error(rule.field + "为必传项"));
      } else {
        callback();
      }
    };
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      sysAdTypeListOptions: [],
      sysAdShowListOptions: [],
      rules: {
        adName: [{ validator: validateRequire }],
        adLink: [{ validator: validateRequire }],
        adCode: [{ validator: validateRequire }],
        type: [{ validator: validateRequire }],
        linkPhone: [{ validator: validateRequire }],
        startTime: [{ validator: validateRequire }],
        endTime: [{ validator: validateRequire }],
        clickCount: [{ validator: validateRequire }],
        enabled: [{ validator: validateRequire }]
      },
      tempRoute: {}
    };
  },
  computed: {},
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id;
      this.fetchData(id);
    } else {
      this.postForm = Object.assign({}, defaultForm);
    }
    this.getSysAdTypeDictList("SysAdType");
    this.getSysAdShowDictList("SysAdShow");
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  methods: {
    getSysAdTypeDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.sysAdTypeListOptions = response.data;
      });
    },
    getSysAdShowDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.sysAdShowListOptions = response.data;
      });
    },
    fetchData(id) {
      findByIdSysAd({ id: id })
        .then(response => {
          this.postForm = response.data;
          // set tagsview title
          this.setTagsViewTitle();
          // set page title
          this.setPageTitle();
        })
        .catch(err => {
          console.log(err);
        });
    },
    createData(data) {
      createSysAd(data)
        .then(response => {
          this.$notify({
            title: "成功",
            message: "保存成功",
            type: "success",
            duration: 2000
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    updateData(data) {
      updateSysAd(data)
        .then(response => {
          this.$notify({
            title: "成功",
            message: "保存成功",
            type: "success",
            duration: 2000
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    pushData(data) {
      pushSysAd(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-广告轮播图";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.id}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-广告轮播图";
      document.title = `${title} - ${this.postForm.id}`;
    },
    submitForm() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          debugger;
          console.log(this.postForm);
          this.loading = true;
          this.pushData(this.postForm);
          this.loading = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    draftForm() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          debugger;
          console.log(this.postForm);
          this.loading = true;
          if (!this.isEdit) {
            this.createData(this.postForm);
          } else {
            this.updateData(this.postForm);
          }
          this.loading = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    getRemoteUserList(query) {
      searchUser(query).then(response => {
        if (!response.data.items) return;
        this.userListOptions = response.data.items.map(v => v.name);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea /deep/ {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}
</style>
