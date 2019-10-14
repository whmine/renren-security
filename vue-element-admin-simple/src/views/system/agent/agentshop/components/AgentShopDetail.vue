<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['agent:agentshop:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['agent:agentshop:save']"
          @click="draftForm"
        >确认</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item v-show="isEdit" style="margin-bottom: 40px;" prop="shopSn" label-width="90px" label="代理商编码:">
          <el-input
            v-model="postForm.shopSn"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            disabled="disabled"
            placeholder="请填写代理商编码"
            
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopName"
          label-width="90px"
          label="代理商名称:"
        >
          <el-input
            v-model="postForm.shopName"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写代理商名称"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopPhone"
          label-width="90px"
          label="代理商手机:"
        >
          <el-input
            v-model="postForm.shopPhone"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写代理商手机，用于登录运营APP"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopType"
          label-width="90px"
          label="代理商模式:"
        >
          <el-select v-model="postForm.shopType" placeholder="代理商模式">
            <el-option
              v-for="item in shopTypeListOptions"
              :key="item.name"
              :label="item.cnName"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopParent"
          label-width="90px"
          label="代理商上级:"
        >
          <el-input
            v-model="postForm.shopParent"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写代理商上级"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopRatio"
          label-width="90px"
          label="佣金百分比:"
        >
          <el-input
            v-model="postForm.shopRatio"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写佣金百分比"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopOpen"
          label-width="90px"
          label="可打开所属机柜:"
        >
          <!-- <el-input
            v-model="postForm.shopOpen"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写可打开所属机柜"
          /> -->
          <el-select v-model="postForm.shopOpen" placeholder="可打开所属机柜">
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
        </el-form-item> -->
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
  findByIdAgentShop,
  createAgentShop,
  updateAgentShop,
  pushAgentShop
} from "@/api/agent/AgentShopApi";
import { searchUser } from "@/api/remote-search";

const defaultForm = {
  id: undefined,
  shopSn: "",
  shopName: "",
  shopPhone: "",
  shopType: "",
  shopParent: "",
  shopRatio: "",
  shopOpen: "",
  customerId: ""
};

export default {
  name: "AgentShopDetail",
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
      shopTypeListOptions: [],
      shopOpenListOptions: [],
      rules: {
        // shopSn: [{ validator: validateRequire }],
        shopName: [{ validator: validateRequire }],
        shopPhone: [{ validator: validateRequire }],
        shopType: [{ validator: validateRequire }],
        shopParent: [{ validator: validateRequire }],
        shopRatio: [{ validator: validateRequire }],
        shopOpen: [{ validator: validateRequire }],
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
    this.getShopTypeDictList("shopType");
    this.getShopOpenDictList("shopOpen");
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  methods: {
    getShopTypeDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.shopTypeListOptions = response.data;
      });
    },
    getShopOpenDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.shopOpenListOptions = response.data;
      });
    },
    fetchData(id) {
      findByIdAgentShop({ id: id })
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
      createAgentShop(data)
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
      updateAgentShop(data)
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
      pushAgentShop(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-代理商";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.id}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-代理商";
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
