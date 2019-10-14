<template>
  <div class="components-container">
    <div class="editor-container">
      <dnd-list
        @deleteEle="deleteSysUserRole"
        @pushEle="createSysUserRole"
        :listLeft="listLeft"
        :listRight="listRight"
        list-left-title="已选角色"
        list-right-title="角色库"
      />
    </div>
  </div>
</template>

<script>
import DndList from "@/components/DndList";
import permission from "@/directive/permission/index.js"; // 权限判断指令
import checkPermission from "@/utils/permission"; // 权限判断函数
import waves from "@/directive/waves";
import {
  findByListSysUserRole,
  findByLeftListSysUserRole,
  findByRightListSysUserRole,
  createSysUserRole,
  delSysUserRole
} from "@/api/sys/SysUserRoleApi";
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
let vm = {};
export default {
  name: "SysUserRoleList",
  components: { Pagination, DndList },
  directives: { permission, waves },
  data() {
    vm = this;
    return {
      list: null,
      total: 0,
      listLoading: true,
      statusMap: [],
      listLeft: [],
      listRight: [],
      listQuery: {
        page: 1,
        limit: 10,
        sysUserId: "",
        sysRoleId: ""
      }
    };
  },
  created() {
    const sysUserId = this.$route.params && this.$route.params.sysUserId;
    this.listQuery.sysUserId = sysUserId;
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      findByLeftListSysUserRole(this.listQuery).then(response => {
        this.listLeft = response.data;
        this.total = response.data.total;
        this.listLoading = false;
      });
      findByRightListSysUserRole(this.listQuery).then(response => {
        this.listRight = response.data;
        this.total = response.data.total;
        this.listLoading = false;
      });
    },
    createSysUserRole(params) {
      this.listLoading = true;
      this.listQuery.sysRoleId = params;
      createSysUserRole(this.listQuery).then(response => {
        this.listLoading = false;
      });
    },
    deleteSysUserRole(params) {
      debugger;
      this.listQuery.sysRoleId = params;
      this.listLoading = true;
      delSysUserRole(this.listQuery).then(response => {
        this.listLoading = false;
      });
    }
  }
};
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
</style>
