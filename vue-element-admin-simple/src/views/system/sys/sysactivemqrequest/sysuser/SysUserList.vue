<template>
    <div class="app-container">
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
                <el-table-column width="280px" align="center" label="密码">
                <template slot-scope="scope">
                    <span>{{ scope.row.sysPasswd }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="是否实名认证">
                <template slot-scope="scope">
                    <span>{{ scope.row.isAuth }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="是否系统管理员">
                <template slot-scope="scope">
                    <span>{{ scope.row.isSys }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="是否超期">
                <template slot-scope="scope">
                    <span>{{ scope.row.isOver }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="超期日期">
                <template slot-scope="scope">
                    <span>{{ scope.row.overTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="是否激活">
                <template slot-scope="scope">
                    <span>{{ scope.row.isAct }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="激活日期">
                <template slot-scope="scope">
                    <span>{{ scope.row.actTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="是否锁定">
                <template slot-scope="scope">
                    <span>{{ scope.row.isLocked }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="锁定日期">
                <template slot-scope="scope">
                    <span>{{ scope.row.lockedTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="是否过期凭证">
                <template slot-scope="scope">
                    <span>{{ scope.row.isExpired }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="过期凭证时间">
                <template slot-scope="scope">
                    <span>{{ scope.row.expiredTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="所属员工">
                <template slot-scope="scope">
                    <span>{{ scope.row.staffId }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="服务区">
                <template slot-scope="scope">
                    <span>{{ scope.row.areaId }}</span>
                </template>
            </el-table-column>
                <el-table-column class-name="status-col" label="状态" width="110px">
                <template slot-scope="{row}">
                    <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
                </template>
            </el-table-column>

            <el-table-column fixed="right" align="left" label="操作" width="120px">
                <template slot-scope="scope">
                    <router-link v-permission="['sys:sysuser:delete']" :to="'/systemAuth/SysUserEdit/'+scope.row.id">
                        <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
                            <el-button v-permission="['sys:sysuser:update']" type="primary" size="small" icon="el-icon-edit" />
                        </el-tooltip>
                    </router-link>
                    <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
                        <el-button
                                size="small"
                                type="primary"
                                icon="el-icon-delete"
                                @click="deleteSysUser(scope.row.id)"
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
    import { findByListSysUser, delSysUser } from "@/api/sys/SysUserApi";
    import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
    import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
    let vm = {};
    export default {
        name: "SysUserList",
        components: { Pagination },
        directives: { permission },
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
                findByListSysUser(this.listQuery).then(response => {
                    this.list = response.data.items;
                    this.total = response.data.total;
                    this.listLoading = false;
                });
            },
            deleteSysUser(data) {
                delSysUser({ id: data }).then(response => {
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
