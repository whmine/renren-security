<template>
    <div class="app-container">
        <div class="filter-container">
            <router-link v-permission="['agent:agentshopbus:save']" :to="'/agentResource/AgentShopBusCreate'">
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
            <el-table-column width="280px" align="center" label="业务员编码">
                <template slot-scope="scope">
                    <span>{{ scope.row.busSn }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="业务员名称">
                <template slot-scope="scope">
                    <span>{{ scope.row.busName }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="业务员手机">
                <template slot-scope="scope">
                    <span>{{ scope.row.busPhone }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="所属代理商">
                <template slot-scope="scope">
                    <span>{{ scope.row.busParent }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="佣金百分比">
                <template slot-scope="scope">
                    <span>{{ scope.row.busRatio }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="可打开所属机柜">
                <template slot-scope="scope">
                    <span>{{ scope.row.busOpen }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="客户编码">
                <template slot-scope="scope">
                    <span>{{ scope.row.customerId }}</span>
                </template>
            </el-table-column>
                <el-table-column class-name="status-col" label="状态" width="110px">
                <template slot-scope="{row}">
                    <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
                </template>
            </el-table-column>

            <el-table-column fixed="right" align="left" label="操作" width="120px">
                <template slot-scope="scope">
                    <router-link v-permission="['agent:agentshopbus:update']" :to="'/agentResource/AgentShopBusEdit/'+scope.row.id">
                        <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
                            <el-button  type="primary" size="small" icon="el-icon-edit" />
                        </el-tooltip>
                    </router-link>
                    <el-tooltip class="item" v-permission="['agent:agentshopbus:delete']" effect="dark" content="删除" placement="bottom">
                        <el-button
                                size="small"
                                type="primary"
                                icon="el-icon-delete"
                                @click="deleteAgentShopBus(scope.row.id)"
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
    import { findByListAgentShopBus, delAgentShopBus } from "@/api/agent/AgentShopBusApi";
    import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
    import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
    let vm = {};
    export default {
        name: "AgentShopBusList",
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
                findByListAgentShopBus(this.listQuery).then(response => {
                    this.list = response.data.items;
                    this.total = response.data.total;
                    this.listLoading = false;
                });
            },
            handleFilter() {
                this.listQuery.page = 1;
                this.getList();
            },
            deleteAgentShopBus(data) {
                delAgentShopBus({ id: data }).then(response => {
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
