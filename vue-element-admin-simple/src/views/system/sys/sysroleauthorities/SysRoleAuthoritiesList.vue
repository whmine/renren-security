 <template>
  <div class="components-container">
    <div class="editor-container">
      <dnd-list
        @deleteEle="deleteSysRoleAuthorities"
        @pushEle="createSysRoleAuthorities"
        :listLeft="listLeft"
        :listRight="listRight"
        list-left-title="已选权限"
        list-right-title="权限库"
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
  findByListSysRoleAuthorities,
  findByRightListSysRoleAuthorities,
  findByLeftListSysRoleAuthorities,
  createSysRoleAuthorities,
  delSysRoleAuthorities
} from "@/api/sys/SysRoleAuthoritiesApi";
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
let vm = {};
export default {
  name: "SysRoleAuthoritiesList",
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
        sysRoleId: ""
      }
    };
  },
  created() {
    const sysRoleId = this.$route.params && this.$route.params.sysRoleId;
    this.listQuery.sysRoleId = sysRoleId;
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      findByLeftListSysRoleAuthorities(this.listQuery).then(response => {
        this.listLeft = response.data;
        this.total = response.data.total;
        this.listLoading = false;
      });
      findByRightListSysRoleAuthorities(this.listQuery).then(response => {
        this.listRight = response.data;
        this.total = response.data.total;
        this.listLoading = false;
      });
    },
    createSysRoleAuthorities(params) {
      this.listLoading = true;
      this.listQuery.sysAuthoritiesId = params;
      createSysRoleAuthorities(this.listQuery).then(response => {
        this.listLoading = false;
      });
    },
    deleteSysRoleAuthorities(params) {
      debugger;
      this.listQuery.sysAuthoritiesId = params;
      this.listLoading = true;
      delSysRoleAuthorities(this.listQuery).then(response => {
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
