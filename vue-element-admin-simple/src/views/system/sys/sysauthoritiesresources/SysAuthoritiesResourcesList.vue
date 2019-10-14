<template>
  <div class="components-container">
    <div class="editor-container">
      <dnd-list
        @deleteEle="deleteSysAuthoritiesResources"
        @pushEle="createSysAuthoritiesResources"
        :listLeft="listLeft"
        :listRight="listRight"
        list-left-title="已选资源"
        list-right-title="资源库"
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
  findByListSysAuthoritiesResources,
  findByLeftListSysAuthoritiesResources,
  findByRightListSysAuthoritiesResources,
  createSysAuthoritiesResources,
  delSysAuthoritiesResources
} from "@/api/sys/SysAuthoritiesResourcesApi";
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
let vm = {};
export default {
  name: "SysAuthoritiesResourcesList",
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
        sysAuthoritiesId: "",
        sysResourcesId: ""
      }
    };
  },
  created() {
    const sysAuthoritiesId =
      this.$route.params && this.$route.params.sysAuthoritiesId;
    this.listQuery.sysAuthoritiesId = sysAuthoritiesId;
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      findByLeftListSysAuthoritiesResources(this.listQuery).then(response => {
        this.listLeft = response.data;
        this.total = response.data.total;
        this.listLoading = false;
      });
      findByRightListSysAuthoritiesResources(this.listQuery).then(response => {
        this.listRight = response.data;
        this.total = response.data.total;
        this.listLoading = false;
      });
    },
    createSysAuthoritiesResources(params) {
      this.listLoading = true;
      this.listQuery.sysResourcesId = params;
      createSysAuthoritiesResources(this.listQuery).then(response => {
        this.listLoading = false;
      });
    },
    deleteSysAuthoritiesResources(params) {
      this.listQuery.sysResourcesId = params;
      this.listLoading = true;
      delSysAuthoritiesResources(this.listQuery).then(response => {
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
