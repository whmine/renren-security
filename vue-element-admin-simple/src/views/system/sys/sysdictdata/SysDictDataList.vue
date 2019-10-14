<template>
  <div class="app-container">
    <div class="filter-container">
      <router-link
        v-permission="['sys:sysdictdata:save']"
        :to="'/systemResource/SysDictDataCreate'"
      >
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
      <!-- <el-table-column width="280px" align="center" label="字典标签">
        <template slot-scope="scope">
          <span>{{ scope.row.dictLabel }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="字典键值">
        <template slot-scope="scope">
          <span>{{ scope.row.dictValue }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="字典类型">
        <template slot-scope="scope">
          <span>{{ scope.row.dictType }}</span>
        </template>
      </el-table-column> -->
      <el-table-column class-name="status-col" label="状态" width="110px">
        <template slot-scope="{row}">
          <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column fixed="right" align="left" label="操作" width="180px">
        <template slot-scope="scope">
          <router-link
            v-permission="['sys:sysdictdata:delete']"
            :to="'/systemResource/SysDictDataEdit/'+scope.row.id"
          >
            <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
              <el-button
                v-permission="['sys:sysdictdata:update']"
                type="primary"
                size="small"
                icon="el-icon-edit"
              />
            </el-tooltip>
          </router-link>
          <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
            <el-button
              size="small"
              type="primary"
              icon="el-icon-delete"
              @click="deleteSysDictData(scope.row.id)"
            />
          </el-tooltip>
          <router-link
            v-permission="['sys:sysdictdata:delete']"
            :to="'/systemResource/SysDictOptionList/'+scope.row.name"
          >
            <el-tooltip class="item" effect="dark" content="属性" placement="bottom">
              <el-button
                v-permission="['sys:sysdictoption:list']"
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
  findByListSysDictData,
  delSysDictData
} from "@/api/sys/SysDictDataApi";
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
let vm = {};
export default {
  name: "SysDictDataList",
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
    }
  },
  data() {
    vm = this;
    return {
      list: null,
      total: 0,
      listLoading: true,
      statusMap: [],
      listQuery: {
        page: 1,
        limit: 10
      }
    };
  },
  created() {
    this.getList();
    this.getDictList("status");
  },
  methods: {
    getDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.statusMap = response.data;
      });
    },
    getList() {
      this.listLoading = true;
      findByListSysDictData(this.listQuery).then(response => {
        this.list = response.data.items;
        this.total = response.data.total;
        this.listLoading = false;
      });
    },
    handleFilter() {
      this.listQuery.page = 1;
      this.getList();
    },
    deleteSysDictData(data) {
      delSysDictData({ id: data }).then(response => {
        this.getList();
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
