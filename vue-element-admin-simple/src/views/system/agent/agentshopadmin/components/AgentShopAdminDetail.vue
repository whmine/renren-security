<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['agent:agentshopadmin:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['agent:agentshopadmin:save']"
          @click="draftForm"
        >确认</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item
          v-show="isEdit"
          style="margin-bottom: 40px;"
          prop="adminSn"
          label-width="90px"
          label="管理员编码:"
        >
          <el-input
            v-model="postForm.adminSn"
            :rows="1"
            type="textarea"
            disabled="disabled"
            class="article-textarea"
            autosize
            placeholder="请填写管理员编码"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="adminName"
          label-width="90px"
          label="管理员名称:"
        >
          <el-input
            v-model="postForm.adminName"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写管理员名称"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="adminPhone"
          label-width="90px"
          label="管理员手机:"
        >
          <el-input
            v-model="postForm.adminPhone"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写管理员手机,用于登录运营APP"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="adminParent"
          label-width="90px"
          label="所属代理商:"
        >
          <!-- <el-input
            v-model="postForm.adminParent"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属代理商"
          />-->
          <el-select v-model="postForm.adminParent" placeholder="请填写所属代理商">
            <el-option
              v-for="item in adminParentListOptions"
              :key="item.id"
              :label="item.shopName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="adminBusParent"
          label-width="90px"
          label="所属业务员:"
        >
          <!-- <el-input
            v-model="postForm.adminBusParent"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属业务员"
          />-->
          <el-select v-model="postForm.adminBusParent" placeholder="请填写所属业务员">
            <el-option
              v-for="item in adminBusParentListOptions"
              :key="item.id"
              :label="item.busName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="adminRatio"
          label-width="90px"
          label="佣金百分比:"
        >
          <el-input
            v-model="postForm.adminRatio"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写佣金百分比"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="adminOpen"
          label-width="90px"
          label="可打开所属机柜:"
        >
          <el-select v-model="postForm.adminOpen" placeholder="可打开所属机柜">
            <el-option
              v-for="item in shopOpenListOptions"
              :key="item.name"
              :label="item.cnName"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <!-- <el-form-item
          style="margin-bottom: 40px;"
          prop="customerId"
          label-width="90px"
          label="客户编码:"
        >
          <el-input
            v-model="postForm.customerId"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写客户编码"
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
  findByIdAgentShopAdmin,
  createAgentShopAdmin,
  updateAgentShopAdmin,
  pushAgentShopAdmin
} from "@/api/agent/AgentShopAdminApi";
import { findByListAgentShop } from "@/api/agent/AgentShopApi";
import { findByListAgentShopBus } from "@/api/agent/AgentShopBusApi";
import { searchUser } from "@/api/remote-search";

const defaultForm = {
  id: undefined,
  adminSn: "",
  adminName: "",
  adminPhone: "",
  adminParent: "",
  adminBusParent: "",
  adminRatio: "",
  adminOpen: "",
  customerId: ""
};

export default {
  name: "AgentShopAdminDetail",
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
      shopOpenListOptions: [],
      adminParentListOptions: [],
      adminBusParentListOptions: [],
      rules: {
        // adminSn: [{ validator: validateRequire }],
        adminName: [{ validator: validateRequire }],
        adminPhone: [{ validator: validateRequire }],
        adminParent: [{ validator: validateRequire }],
        adminBusParent: [{ validator: validateRequire }],
        adminRatio: [{ validator: validateRequire }],
        adminOpen: [{ validator: validateRequire }],
        customerId: [{ validator: validateRequire }]
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
    this.getShopOpenDictList("shopOpen");
    this.getAdminParentDictList();
    // this.getAdminBusParentDictList("");
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  watch: {
    "postForm.adminParent": function(value) {
      this.getAdminBusParentDictList(value);
    }
  },
  methods: {
    getShopOpenDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.shopOpenListOptions = response.data;
      });
    },
    getAdminParentDictList() {
      findByListAgentShop({ limit: 100, page: 1 }).then(response => {
        this.adminParentListOptions = response.data.items;
      });
    },
    getAdminBusParentDictList(busParent) {
      findByListAgentShopBus({
        busParent: busParent,
        limit: 100,
        page: 1
      }).then(response => {
        this.adminBusParentListOptions = response.data.items;
      });
    },
    fetchData(id) {
      findByIdAgentShopAdmin({ id: id })
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
      createAgentShopAdmin(data)
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
      updateAgentShopAdmin(data)
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
      pushAgentShopAdmin(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-代理商管理员";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.id}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-代理商管理员";
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
