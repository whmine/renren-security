<template>
    <div class="app-container">
        <div class="filter-container">
            <router-link v-permission="['device:devicecabinetlog:save']" :to="'/deviceResource/DeviceCabinetLogCreate'">
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
            <el-table-column width="280px" align="center" label="机柜编码">
                <template slot-scope="scope">
                    <span>{{ scope.row.cabinetId }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="二维码编号">
                <template slot-scope="scope">
                    <span>{{ scope.row.qrCode }}</span>
                </template>
            </el-table-column>
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
                <el-table-column class-name="status-col" label="状态" width="110px">
                <template slot-scope="{row}">
                    <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
                </template>
            </el-table-column>

            <el-table-column fixed="right" align="left" label="操作" width="120px">
                <template slot-scope="scope">
                    <router-link v-permission="['device:devicecabinetlog:update']" :to="'/deviceResource/DeviceCabinetLogEdit/'+scope.row.id">
                        <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
                            <el-button  type="primary" size="small" icon="el-icon-edit" />
                        </el-tooltip>
                    </router-link>
                    <el-tooltip class="item" v-permission="['device:devicecabinetlog:delete']" effect="dark" content="删除" placement="bottom">
                        <el-button
                                size="small"
                                type="primary"
                                icon="el-icon-delete"
                                @click="deleteDeviceCabinetLog(scope.row.id)"
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
    import permission from '@/directive/permission/index.js' // 权限判断指令
    import checkPermission from '@/utils/permission' // 权限判断函数
    import waves from "@/directive/waves";
    import { findByListDeviceCabinetLog, delDeviceCabinetLog } from "@/api/device/DeviceCabinetLogApi";
    import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
    import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
    let vm = {};
    export default {
        name: "DeviceCabinetLogList",
        components: { Pagination },
        directives: { permission , waves },
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
                findByListDeviceCabinetLog(this.listQuery).then(response => {
                    this.list = response.data.items;
                    this.total = response.data.total;
                    this.listLoading = false;
                });
            },
            handleFilter() {
                this.listQuery.page = 1;
                this.getList();
            },
            deleteDeviceCabinetLog(data) {
                delDeviceCabinetLog({ id: data }).then(response => {
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
