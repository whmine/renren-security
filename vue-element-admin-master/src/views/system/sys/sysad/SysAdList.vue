<template>
  <div class="app-container">
    <div class="filter-container">
      <router-link v-permission="['sys:sysad:save']" :to="'/systemResource/SysAdCreate'">
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
      <el-table-column width="280px" align="center" label="广告名字">
        <template slot-scope="scope">
          <span>{{ scope.row.adName }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column width="280px" align="center" label="链接地址">
        <template slot-scope="scope">
          <span>{{ scope.row.adLink }}</span>
        </template>
      </el-table-column>-->
      <el-table-column width="280px" align="center" label="图片地址">
        <template slot-scope="scope">
          <ElTableImg v-model="scope.row.adCode"></ElTableImg>
          <!-- <img :src="pathUrl+scope.row.adCode" min-width="70" height="70" />
          <span>{{ scope.row.adCode }}</span>-->
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="类型">
        <template slot-scope="scope">
          <span>{{ scope.row.type|sysAdTypeFilter }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column width="280px" align="center" label="添加人手机号">
        <template slot-scope="scope">
          <span>{{ scope.row.linkPhone }}</span>
        </template>
      </el-table-column>-->
      <el-table-column width="280px" align="center" label="开始时间">
        <template slot-scope="scope">
          <span>{{ scope.row.startTime }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="结束时间">
        <template slot-scope="scope">
          <span>{{ scope.row.endTime }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="点击量">
        <template slot-scope="scope">
          <span>{{ scope.row.clickCount }}</span>
        </template>
      </el-table-column>
      <el-table-column width="280px" align="center" label="是否显示">
        <template slot-scope="scope">
          <span>{{ scope.row.enabled|sysAdShowFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110px">
        <template slot-scope="{row}">
          <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column fixed="right" align="left" label="操作" width="120px">
        <template slot-scope="scope">
          <router-link
            v-permission="['sys:sysad:delete']"
            :to="'/systemResource/SysAdEdit/'+scope.row.id"
          >
            <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
              <el-button
                v-permission="['sys:sysad:update']"
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
              @click="deleteSysAd(scope.row.id)"
            />
          </el-tooltip>
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
import { findByListSysAd, delSysAd } from "@/api/sys/SysAdApi";
import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
import ElTableImg from "@/components/ElTableImg/index";
let vm = {};
export default {
  name: "SysAdList",
  components: { Pagination, ElTableImg },
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
    sysAdTypeFilter(status) {
      for (let i = 0; i < vm.sysAdTypeListOptions.length; i++) {
        var sysAdTypeListOption = vm.sysAdTypeListOptions[i];
        if (sysAdTypeListOption.name == status) {
          return sysAdTypeListOption.value;
        }
      }
      return "";
    },
    sysAdShowFilter(status) {
      for (let i = 0; i < vm.sysAdShowListOptions.length; i++) {
        var sysAdShowListOption = vm.sysAdShowListOptions[i];
        if (sysAdShowListOption.name == status) {
          return sysAdShowListOption.value;
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
      pathUrl: "",
      statusMap: [],
      sysAdTypeListOptions: [],
      sysAdShowListOptions: [],
      listQuery: {
        page: 1,
        limit: 10
      }
    };
  },
  created() {
    this.getList();
    this.getDictList("status");
    this.getSysAdTypeDictList("SysAdType");
    this.getSysAdShowDictList("SysAdShow");
  },
  methods: {
    getSysAdTypeDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.sysAdTypeListOptions = response.data;
      });
    },
    getSysAdShowDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.sysAdShowListOptions = response.data;
      });
    },
    getDictList(type) {
      findByTypeListSysDictOption({ type: type }).then(response => {
        this.statusMap = response.data;
      });
    },
    getList() {
      this.listLoading = true;
      findByListSysAd(this.listQuery).then(response => {
        this.list = response.data.items;
        this.total = response.data.total;
        this.listLoading = false;
      });
    },
    handleFilter() {
      this.listQuery.page = 1;
      this.getList();
    },
    deleteSysAd(data) {
      delSysAd({ id: data }).then(response => {
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
