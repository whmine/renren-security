<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['agent:agentshoppos:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['agent:agentshoppos:save']"
          @click="draftForm"
        >确认</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item v-show="isEdit" style="margin-bottom: 40px;" prop="posSn" label-width="90px" label="商户编码:">
          <el-input
            v-model="postForm.posSn"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写商户编码"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="posName" label-width="90px" label="商户名称:">
          <el-input
            v-model="postForm.posName"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写商户名称"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="posAdmin"
          label-width="90px"
          label="商户管理员:"
        >
          <el-input
            v-model="postForm.posAdmin"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写商户管理员"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="posBus" label-width="90px" label="所属业务员:">
          <el-input
            v-model="postForm.posBus"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属业务员"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="posBusRatio"
          label-width="90px"
          label="业务员佣金百分比:"
        >
          <el-input
            v-model="postForm.posBusRatio"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写业务员佣金百分比"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="posRatio"
          label-width="90px"
          label="商铺佣金百分比:"
        >
          <el-input
            v-model="postForm.posRatio"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写商铺佣金百分比"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="posLogo"
          label-width="90px"
          label="商铺LOGO:"
        >
          <el-input
            v-model="postForm.posLogo"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写商铺LOGO"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="posImg" label-width="90px" label="商铺图片:">
          <el-input
            v-model="postForm.posImg"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写商铺图片"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="province" label-width="90px" label="省:">
          <el-input
            v-model="postForm.province"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写省"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="city" label-width="90px" label="市:">
          <el-input
            v-model="postForm.city"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写市"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="district" label-width="90px" label="区:">
          <el-input
            v-model="postForm.district"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写区"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="address" label-width="90px" label="详细地址:">
          <el-input
            v-model="postForm.address"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写详细地址"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="warp" label-width="90px" label="经度:">
          <el-input
            v-model="postForm.warp"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写经度"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="weft" label-width="90px" label="纬度:">
          <el-input
            v-model="postForm.weft"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写纬度"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="chargingType"
          label-width="90px"
          label="计费方式:"
        >
          <el-input
            v-model="postForm.chargingType"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写计费方式"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="chargingCompany"
          label-width="90px"
          label="计费单位:"
        >
          <el-input
            v-model="postForm.chargingCompany"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写计费单位"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="chargingStandard"
          label-width="90px"
          label="计费标准:"
        >
          <el-input
            v-model="postForm.chargingStandard"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写计费标准"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="freeTime" label-width="90px" label="免费时长:">
          <el-input
            v-model="postForm.freeTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写免费时长"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="consume"
          label-width="90px"
          label="每日最高消费:"
        >
          <el-input
            v-model="postForm.consume"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写每日最高消费"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="cipherLine"
          label-width="90px"
          label="密码线:"
        >
          <el-input
            v-model="postForm.cipherLine"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写密码线"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="shopPhone"
          label-width="90px"
          label="店铺电话:"
        >
          <el-input
            v-model="postForm.shopPhone"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写店铺电话"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="startTime"
          label-width="90px"
          label="营业时间开始:"
        >
          <el-input
            v-model="postForm.startTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写营业时间开始"
          />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px;"
          prop="endTime"
          label-width="90px"
          label="营业时间结束:"
        >
          <el-input
            v-model="postForm.endTime"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写营业时间结束"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="money" label-width="90px" label="人均消费:">
          <el-input
            v-model="postForm.money"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写人均消费"
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
  findByIdAgentShopPos,
  createAgentShopPos,
  updateAgentShopPos,
  pushAgentShopPos
} from "@/api/agent/AgentShopPosApi";
import { searchUser } from "@/api/remote-search";

const defaultForm = {
  id: undefined,
  posSn: "",
  posName: "",
  posAdmin: "",
  posBus: "",
  posBusRatio: "",
  posRatio: "",
  posLogo: "",
  posImg: "",
  province: "",
  city: "",
  district: "",
  address: "",
  warp: "",
  weft: "",
  chargingType: "",
  chargingCompany: "",
  chargingStandard: "",
  freeTime: "",
  consume: "",
  cipherLine: "",
  shopPhone: "",
  startTime: "",
  endTime: "",
  money: ""
};

export default {
  name: "AgentShopPosDetail",
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
        posSn: [{ validator: validateRequire }],
        posName: [{ validator: validateRequire }],
        posAdmin: [{ validator: validateRequire }],
        posBus: [{ validator: validateRequire }],
        posBusRatio: [{ validator: validateRequire }],
        posRatio: [{ validator: validateRequire }],
        posLogo: [{ validator: validateRequire }],
        posImg: [{ validator: validateRequire }],
        province: [{ validator: validateRequire }],
        city: [{ validator: validateRequire }],
        district: [{ validator: validateRequire }],
        address: [{ validator: validateRequire }],
        warp: [{ validator: validateRequire }],
        weft: [{ validator: validateRequire }],
        chargingType: [{ validator: validateRequire }],
        chargingCompany: [{ validator: validateRequire }],
        chargingStandard: [{ validator: validateRequire }],
        freeTime: [{ validator: validateRequire }],
        consume: [{ validator: validateRequire }],
        cipherLine: [{ validator: validateRequire }],
        shopPhone: [{ validator: validateRequire }],
        startTime: [{ validator: validateRequire }],
        endTime: [{ validator: validateRequire }],
        money: [{ validator: validateRequire }]
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
      findByIdAgentShopPos({ id: id })
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
      createAgentShopPos(data)
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
      updateAgentShopPos(data)
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
      pushAgentShopPos(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-代理商商户";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.id}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-代理商商户";
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
