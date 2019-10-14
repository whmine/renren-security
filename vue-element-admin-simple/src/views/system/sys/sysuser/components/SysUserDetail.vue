<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['sys:sysuser:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['sys:sysuser:save']"
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
        <el-form-item style="margin-bottom: 40px;" prop="sysPasswd" label-width="90px" label="密码:">
          <el-input
            v-model="postForm.sysPasswd"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写密码"
          />
        </el-form-item>
        <!-- <el-form-item style="margin-bottom: 40px;" prop="sysPasswd" label-width="90px" label="密码:">
          <video
            src="https://sharkapp.natapp4.cc/api-sys/sys/sysattachment/play"
            controls="controls"
          >your browser does not support the video tag</video>
        </el-form-item>-->
        <!--  <el-form-item style="margin-bottom: 40px;" prop="isAuth" label-width="90px" label="是否实名认证:">
          <el-input
            v-model="postForm.isAuth"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否实名认证"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="isSys" label-width="90px" label="是否系统管理员:">
          <el-input
            v-model="postForm.isSys"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否系统管理员"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="isOver" label-width="90px" label="是否超期:">
          <el-input
            v-model="postForm.isOver"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否超期"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="overTime" label-width="90px" label="超期日期:">
          <el-input
            v-model="postForm.overTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写超期日期"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="isAct" label-width="90px" label="是否激活:">
          <el-input
            v-model="postForm.isAct"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否激活"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="actTime" label-width="90px" label="激活日期:">
          <el-input
            v-model="postForm.actTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写激活日期"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="isLocked" label-width="90px" label="是否锁定:">
          <el-input
            v-model="postForm.isLocked"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否锁定"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="lockedTime"
          label-width="90px"
          label="锁定日期:"
        >
          <el-input
            v-model="postForm.lockedTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写锁定日期"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="isExpired"
          label-width="90px"
          label="是否过期凭证:"
        >
          <el-input
            v-model="postForm.isExpired"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否过期凭证"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="expiredTime"
          label-width="90px"
          label="过期凭证时间:"
        >
          <el-input
            v-model="postForm.expiredTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写过期凭证时间"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="staffId" label-width="90px" label="所属员工:">
          <el-input
            v-model="postForm.staffId"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属员工"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="areaId" label-width="90px" label="服务区:">
          <el-input
            v-model="postForm.areaId"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写服务区"
          />
        </el-form-item>-->
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
  findByIdSysUser,
  createSysUser,
  updateSysUser,
  pushSysUser
} from "@/api/sys/SysUserApi";
import { searchUser } from "@/api/remote-search";

const defaultForm = {
  id: undefined,
  name: "",
  cnName: "",
  sysPasswd: "",
  isAuth: "",
  isSys: "",
  isOver: "",
  overTime: "",
  isAct: "",
  actTime: "",
  isLocked: "",
  lockedTime: "",
  isExpired: "",
  expiredTime: "",
  staffId: "",
  areaId: ""
};

export default {
  name: "SysUserDetail",
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
      isExpiredMap: [],
      isLockedMap: [],
      isActMap: [],
      isOverMap: [],
      isSysMap: [],
      isAuthMap: [],
      rules: {
        name: [{ validator: validateRequire }],
        cnName: [{ validator: validateRequire }],
        sysPasswd: [{ validator: validateRequire }]
        // isAuth: [{ validator: validateRequire }],
        // isSys: [{ validator: validateRequire }],
        // isOver: [{ validator: validateRequire }],
        // overTime: [{ validator: validateRequire }],
        // isAct: [{ validator: validateRequire }],
        // actTime: [{ validator: validateRequire }],
        // isLocked: [{ validator: validateRequire }],
        // lockedTime: [{ validator: validateRequire }],
        // isExpired: [{ validator: validateRequire }],
        // expiredTime: [{ validator: validateRequire }],
        // staffId: [{ validator: validateRequire }],
        // areaId: [{ validator: validateRequire }]
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
    this.getIsExpiredDictList("isExpired");
    this.getIsLockedDictList("isLocked");
    this.getIsActDictList("isAct");
    this.getIsOverDictList("isOver");
    this.getIsSysDictList("isSys");
    this.getIsAuthDictList("isAuth");

    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  methods: {
    getIsAuthDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isAuthMap = response.data;
      });
    },
    getIsSysDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isSysMap = response.data;
      });
    },
    getIsActDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isActMap = response.data;
      });
    },
    getIsOverDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isOverMap = response.data;
      });
    },
    getIsActDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isActMap = response.data;
      });
    },
    getIsLockedDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isLockedMap = response.data;
      });
    },
    getIsExpiredDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.isExpiredMap = response.data;
      });
    },
    fetchData(id) {
      findByIdSysUser({ id: id })
        .then(response => {
          this.postForm = response.data;
          this.postForm.sysPasswd = "";
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
      createSysUser(data)
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
      updateSysUser(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    pushData(data) {
      pushSysUser(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-系统用户";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.cnName}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-系统用户";
      document.title = `${title} - ${this.postForm.cnName}`;
    },
    submitForm() {
      // this.$refs.postForm.validate(valid => {
      //   if (valid) {
      debugger;
      this.loading = true;
      this.pushData({ id: this.postForm.id });
      this.loading = false;
      // } else {
      //   console.log("error submit!!");
      //   return false;
      // }
      // });
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
