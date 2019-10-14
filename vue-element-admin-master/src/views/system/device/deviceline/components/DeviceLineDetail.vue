<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px;"
          type="success"
          @click="submitForm"
          v-permission="['device:deviceline:push']"
        >发布</el-button>
        <el-button
          v-loading="loading"
          type="warning"
          v-permission="['device:deviceline:save']"
          @click="draftForm"
        >确认</el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-form-item style="margin-bottom: 40px;" prop="lineSn" label-width="90px" label="机柜编码:">
          <el-input
            v-model="postForm.lineSn"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写机柜编码"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="qrCode" label-width="90px" label="二维码编号:">
          <el-input
            v-model="postForm.qrCode"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写二维码编号"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="agent" label-width="90px" label="所属代理商:">
           <!-- <el-input
            v-model="postForm.agent"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属代理商"
          /> -->
          <el-select v-model="postForm.agent" placeholder="请填写所属代理商">
            <el-option
              v-for="item in agentListOptions"
              :key="item.id"
              :label="item.shopName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="bus" label-width="90px" label="所属业务员:">
          <!-- <el-input
            v-model="postForm.bus"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属业务员"
          /> -->
          <el-select v-model="postForm.bus" placeholder="请填写所属业务员">
            <el-option
              v-for="item in busListOptions"
              :key="item.id"
              :label="item.busName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="pos" label-width="90px" label="所属商户:">
          <el-input
            v-model="postForm.pos"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写所属商户"
          />
        </el-form-item>
        <!-- <el-form-item style="margin-bottom: 40px;" prop="lease" label-width="90px" label="是否可以租借:">
          <el-input
            v-model="postForm.lease"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写是否可以租借"
          />
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" prop="state" label-width="90px" label="设备状态:">
          <el-input
            v-model="postForm.state"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="请填写设备状态"
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
import { findByListAgentShop } from "@/api/agent/AgentShopApi";
import { findByListAgentShopBus } from "@/api/agent/AgentShopBusApi";
import {
  findByIdDeviceLine,
  createDeviceLine,
  updateDeviceLine,
  pushDeviceLine
} from "@/api/device/DeviceLineApi";
import { searchUser } from "@/api/remote-search";

const defaultForm = {
  id: undefined,
  lineSn: "",
  qrCode: "",
  agent: "",
  bus: "",
  pos: "",
  lease: "",
  state: ""
};

export default {
  name: "DeviceLineDetail",
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
      agentListOptions: [],
      busListOptions:[],
      rules: {
        lineSn: [{ validator: validateRequire }],
        qrCode: [{ validator: validateRequire }],
        agent: [{ validator: validateRequire }],
        bus: [{ validator: validateRequire }],
        pos: [{ validator: validateRequire }]
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
    this.getAgentDictList();
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route);
  },
  watch: {
    "postForm.agent": function(value) {
      this.getBusDictList(value);
    }
  },
  methods: {
    getAgentDictList() {
      findByListAgentShop({ limit: 100, page: 1 }).then(response => {
        this.agentListOptions = response.data.items;
      });
    },
    getBusDictList(busParent) {
      findByListAgentShopBus({
        busParent: busParent,
        limit: 100,
        page: 1
      }).then(response => {
        this.busListOptions = response.data.items;
      });
    },
    fetchData(id) {
      findByIdDeviceLine({ id: id })
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
      createDeviceLine(data)
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
      updateDeviceLine(data)
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
      pushDeviceLine(data)
        .then(response => {})
        .catch(err => {
          console.log(err);
        });
    },
    setTagsViewTitle() {
      const title = "编辑-密码线";
      const route = Object.assign({}, this.tempRoute, {
        title: `${title}-${this.postForm.id}`
      });
      this.$store.dispatch("tagsView/updateVisitedView", route);
    },
    setPageTitle() {
      const title = "编辑-密码线";
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
