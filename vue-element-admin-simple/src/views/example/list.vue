<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="280">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="220px" align="left" label="中文名称">
        <template slot-scope="scope">
          <span>{{ scope.row.cnName }}</span>
        </template>
      </el-table-column>
      <el-table-column width="260px" align="left" label="英文名称">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column width="260px" align="left" label="权限编码">
        <template slot-scope="scope">
          <span>{{ scope.row.authorities }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Status" width="110px">
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions" width="120">
        <template slot-scope="scope">
          <router-link :to="'/example/edit/'+scope.row.id">
            <el-button type="primary" size="small" icon="el-icon-edit">
              Edit
            </el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import { fetchList } from '@/api/article'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
let vm = {}
export default {
  name: 'ArticleList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        A: '正常',
        P: '删除'
      }
      return statusMap[status]
    }
  },
  data() {
    vm = this
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        this.listLoading = false
      })
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
