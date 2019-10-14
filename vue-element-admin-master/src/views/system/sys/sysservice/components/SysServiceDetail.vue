<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['sys:sysservice:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['sys:sysservice:save']"
          @click="draftForm"
        >确认</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item style="margin-bottom: 40px;" prop="name" label-width="90px" label="名称:">
          <el-input
            v-model="postForm.name"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写名称"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="cnName" label-width="90px" label="中文名称:">
          <el-input
            v-model="postForm.cnName"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写中文名称"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="icon" label-width="90px" label="图标:">
          <el-input
            v-model="postForm.icon"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写图标"
          />
          <!-- <Upload v-model="postForm.icon" /> -->
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="content" label-width="90px" label="描述:">
          <!-- <el-input
            v-model="postForm.content"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写描述"
          /> -->
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="authzCode" label-width="90px" label="授权码:">
          <el-input
            v-model="postForm.authzCode"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写授权码"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="perms" label-width="90px" label="参数:">
          <el-input
            v-model="postForm.perms"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写参数"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="isShow" label-width="90px" label="是否显示:">
          <el-input
            v-model="postForm.isShow"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否显示"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="startUpType"
          label-width="90px"
          label="启动类型:"
        >
          <el-input
            v-model="postForm.startUpType"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写启动类型"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="state" label-width="90px" label="服务状态:">
          <el-input
            v-model="postForm.state"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写服务状态"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="method" label-width="90px" label="方法和类名:">
          <el-input
            v-model="postForm.method"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写方法和类名"
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
import {
  findByIdSysService,
  createSysService,
  updateSysService,
  pushSysService
} from "@/api/sys/SysServiceApi";
import { searchUser } from "@/api/remote-search";

const defaultForm = {
  id: undefined,
  name: "",
  cnName: "",
  icon: "",
  content: "",
  authzCode: "",
  perms: "",
  isShow: "",
  startUpType: "",
  state: "",
  method: ""
};

export default {
  name: "SysServiceDetail",
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
      rules: {
        name: [{ validator: validateRequire }],
        cnName: [{ validator: validateRequire }],
        icon: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
        authzCode: [{ validator: validateRequire }],
        perms: [{ validator: validateRequire }],
        isShow: [{ validator: validateRequire }],
        startUpType: [{ validator: validateRequire }],
        state: [{ validator: validateRequire }],
        method: [{ validator: validateRequire }]
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

    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  methods: {
    fetchData(id) {
      findByIdSysService({ id: id })
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
      createSysService(data)
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
      updateSysService(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    pushData(data) {
      pushSysService(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-系统服务";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.cnName}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-系统服务";
      document.title = `${title} - ${this.postForm.cnName}`;
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
