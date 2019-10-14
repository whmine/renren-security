<template>
  <div class="app-container">
    <div class="filter-container">
      <router-link v-permission="['sys:sysuser:save']" :to="'/systemAuth/SysUserCreate'">
        <el-tooltip class="item" effect="dark" content="创建" placement="bottom">
          <el-button
            v-waves
            class="filter-item"
            style="margin-left: 10px;"
            type="primary"
            icon="el-icon-plus"
          />
        </el-tooltip>
      </router-link>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column align="center" label="ID" width="280px">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="名称">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="中文名称">
        <template slot-scope="scope">
          <span>{{ scope.row.cnName }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column width="280px" v-if="show" align="center" label="密码">
        <template slot-scope="scope">
          <span>{{ scope.row.sysPasswd }}</span>
        </template>
      </el-table-column>-->
      <el-table-column width="280px" align="center" label="是否实名认证">
        <template slot-scope="scope">
          <span>{{ scope.row.isAuth|isAuthFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="是否系统管理员">
        <template slot-scope="scope">
          <span>{{ scope.row.isSys|isSysFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="是否超期">
        <template slot-scope="scope">
          <span>{{ scope.row.isOver |isOverFilter}}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="超期日期">
        <template slot-scope="scope">
          <span>{{ scope.row.overTime }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="是否激活">
        <template slot-scope="scope">
          <span>{{ scope.row.isAct|isActFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="激活日期">
        <template slot-scope="scope">
          <span>{{ scope.row.actTime }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="是否锁定">
        <template slot-scope="scope">
          <span>{{ scope.row.isLocked|isLockedFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="锁定日期">
        <template slot-scope="scope">
          <span>{{ scope.row.lockedTime }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="是否过期凭证">
        <template slot-scope="scope">
          <span>{{ scope.row.isExpired|isExpiredFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="过期凭证时间">
        <template slot-scope="scope">
          <span>{{ scope.row.expiredTime }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column width="280px" align="center" label="所属员工">
        <template slot-scope="scope">
          <span>{{ scope.row.staffId }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="服务区">
        <template slot-scope="scope">
          <span>{{ scope.row.areaId }}</span>
        </template>
      </el-table-column>-->
      <el-table-column class-name="status-col" label="状态" width="110px">
        <template slot-scope="{row}">
          <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column fixed="right" align="left" label="操作" width="240px">
        <template slot-scope="scope">
          <router-link
            v-permission="['sys:sysuser:update']"
            :to="'/systemAuth/SysUserEdit/'+scope.row.id"
          >
            <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
              <el-button type="primary" size="small" icon="el-icon-edit" />
            </el-tooltip>
          </router-link>
          <el-tooltip
            v-permission="['sys:sysuser:delete']"
            class="item"
            effect="dark"
            content="删除"
            placement="bottom"
          >
            <el-button
              size="small"
              type="primary"
              icon="el-icon-delete"
              @click="deleteSysUser(scope.row.id)"
            />
          </el-tooltip>
          <el-tooltip
            v-permission="['sys:sysuser:push']"
            class="item"
            effect="dark"
            content="发布redis"
            placement="bottom"
          >
            <el-button
              size="small"
              type="primary"
              icon="el-icon-refresh"
              @click="pushSysUser(scope.row.id)"
            />
          </el-tooltip>
          <router-link
            v-permission="['sys:sysdictdata:delete']"
            :to="'/systemAuth/SysUserRoleList/'+scope.row.id"
          >
            <el-tooltip class="item" effect="dark" content="用户角色" placement="bottom">
              <el-button
                v-permission="['sys:sysuserrole:list']"
                type="primary"
                size="small"
                icon="el-icon-edit-outline"
              />
            </el-tooltip>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />
  </div>
</template>

<script>
import permission from "@/directive/permission/index.js"; // 权限判断指令
import checkPermission from "@/utils/permission"; // 权限判断函数
import waves from "@/directive/waves";
import {
  findByListSysUser,
  pushSysUser,
  delSysUser
} from "@/api/sys/SysUserApi";
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
let vm = {};
export default {
  name: "SysUserList",
  components: { Pagination },
  directives: { permission, waves },
  filters: {
    statusFilter(status) {
      for (let i = 0; i < vm.statusMap.length; i++) {
        var statusMap = vm.statusMap[i];
        if (statusMap.name == status) {
          return statusMap.value;
        }
      }
      return "";
    },
    isExpiredFilter(status) {
      for (let i = 0; i < vm.isExpiredMap.length; i++) {
        var isExpiredMap = vm.isExpiredMap[i];
        if (isExpiredMap.name == status) {
          return isExpiredMap.value;
        }
      }
      return "";
    },
    isLockedFilter(status) {
      for (let i = 0; i < vm.isLockedMap.length; i++) {
        var isLockedMap = vm.isLockedMap[i];
        if (isLockedMap.name == status) {
          return isLockedMap.value;
        }
      }
      return "";
    },
    isActFilter(status) {
      for (let i = 0; i < vm.isActMap.length; i++) {
        var isActMap = vm.isActMap[i];
        if (isActMap.name == status) {
          return isActMap.value;
        }
      }
      return "";
    },
    isOverFilter(status) {
      for (let i = 0; i < vm.isOverMap.length; i++) {
        var isOverMap = vm.isOverMap[i];
        if (isOverMap.name == status) {
          return isOverMap.value;
        }
      }
      return "";
    },
    isSysFilter(status) {
      for (let i = 0; i < vm.isSysMap.length; i++) {
        var isSysMap = vm.isSysMap[i];
        if (isSysMap.name == status) {
          return isSysMap.value;
        }
      }
      return "";
    },
    isAuthFilter(status) {
      for (let i = 0; i < vm.isAuthMap.length; i++) {
        var isAuthMap = vm.isAuthMap[i];
        if (isAuthMap.name == status) {
          return isAuthMap.value;
        }
      }
      return "";
    }
  },
  data() {
    vm = this;
    return {
      list: null,
      total: 0,
      listLoading: true,
      statusMap: [],
      isExpiredMap: [],
      isLockedMap: [],
      isActMap: [],
      isOverMap: [],
      isSysMap: [],
      isAuthMap: [],
      show: true,
      listQuery: {
        page: 1,
        limit: 10
      }
    };
  },
  created() {
    this.getList();
    this.getDictList("status");
    this.getIsExpiredDictList("isExpired");
    this.getIsLockedDictList("isLocked");
    this.getIsActDictList("isAct");
    this.getIsOverDictList("isOver");
    this.getIsSysDictList("isSys");
    this.getIsAuthDictList("isAuth");
  },
  methods: {
    getDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.statusMap = response.data;
      });
    },
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
    getList() {
      this.listLoading = true;
      findByListSysUser(this.listQuery).then(response => {
        this.list = response.data.items;
        this.total = response.data.total;
        this.listLoading = false;
      });
    },
    handleFilter() {
      this.listQuery.page = 1;
      this.getList();
    },
    deleteSysUser(data) {
      delSysUser({ id: data }).then(response => {
        this.getList();
      });
    },
    pushSysUser(data) {
      pushSysUser({ id: data }).then(response => {
        this.$notify({
          title: "成功",
          message: "更新成功",
          type: "success",
          duration: 2000
        });
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
