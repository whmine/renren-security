<template>
    <div class="app-container">
        <div class="filter-container">
            <router-link v-permission="['agent:agentshoppos:save']" :to="'/agentResource/AgentShopPosCreate'">
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
            <el-table-column width="280px" align="center" label="商户编码">
                <template slot-scope="scope">
                    <span>{{ scope.row.posSn }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="商户名称">
                <template slot-scope="scope">
                    <span>{{ scope.row.posName }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="商户管理员">
                <template slot-scope="scope">
                    <span>{{ scope.row.posAdmin }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="所属业务员">
                <template slot-scope="scope">
                    <span>{{ scope.row.posBus }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="业务员佣金百分比">
                <template slot-scope="scope">
                    <span>{{ scope.row.posBusRatio }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="商铺佣金百分比">
                <template slot-scope="scope">
                    <span>{{ scope.row.posRatio }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="商铺LOGO">
                <template slot-scope="scope">
                    <span>{{ scope.row.posLogo }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="商铺图片">
                <template slot-scope="scope">
                    <span>{{ scope.row.posImg }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="省">
                <template slot-scope="scope">
                    <span>{{ scope.row.province }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="市">
                <template slot-scope="scope">
                    <span>{{ scope.row.city }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="区">
                <template slot-scope="scope">
                    <span>{{ scope.row.district }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="详细地址">
                <template slot-scope="scope">
                    <span>{{ scope.row.address }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="经度">
                <template slot-scope="scope">
                    <span>{{ scope.row.warp }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="纬度">
                <template slot-scope="scope">
                    <span>{{ scope.row.weft }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="计费方式">
                <template slot-scope="scope">
                    <span>{{ scope.row.chargingType }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="计费单位">
                <template slot-scope="scope">
                    <span>{{ scope.row.chargingCompany }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="计费标准">
                <template slot-scope="scope">
                    <span>{{ scope.row.chargingStandard }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="免费时长">
                <template slot-scope="scope">
                    <span>{{ scope.row.freeTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="每日最高消费">
                <template slot-scope="scope">
                    <span>{{ scope.row.consume }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="密码线">
                <template slot-scope="scope">
                    <span>{{ scope.row.cipherLine }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="店铺电话">
                <template slot-scope="scope">
                    <span>{{ scope.row.shopPhone }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="营业时间开始">
                <template slot-scope="scope">
                    <span>{{ scope.row.startTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="营业时间结束">
                <template slot-scope="scope">
                    <span>{{ scope.row.endTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="人均消费">
                <template slot-scope="scope">
                    <span>{{ scope.row.money }}</span>
                </template>
            </el-table-column>
                <el-table-column class-name="status-col" label="状态" width="110px">
                <template slot-scope="{row}">
                    <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
                </template>
            </el-table-column>

            <el-table-column fixed="right" align="left" label="操作" width="120px">
                <template slot-scope="scope">
                    <router-link v-permission="['agent:agentshoppos:update']" :to="'/agentResource/AgentShopPosEdit/'+scope.row.id">
                        <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
                            <el-button  type="primary" size="small" icon="el-icon-edit" />
                        </el-tooltip>
                    </router-link>
                    <el-tooltip class="item" v-permission="['agent:agentshoppos:delete']" effect="dark" content="删除" placement="bottom">
                        <el-button
                                size="small"
                                type="primary"
                                icon="el-icon-delete"
                                @click="deleteAgentShopPos(scope.row.id)"
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
    import { findByListAgentShopPos, delAgentShopPos } from "@/api/agent/AgentShopPosApi";
    import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
    import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
    let vm = {};
    export default {
        name: "AgentShopPosList",
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
                findByListAgentShopPos(this.listQuery).then(response => {
                    this.list = response.data.items;
                    this.total = response.data.total;
                    this.listLoading = false;
                });
            },
            handleFilter() {
                this.listQuery.page = 1;
                this.getList();
            },
            deleteAgentShopPos(data) {
                delAgentShopPos({ id: data }).then(response => {
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
