<template>
    <div class="app-container">
        <div class="filter-container">
            <router-link v-permission="['sys:syssmscode:save']" :to="'/systemResource/SysSmsCodeCreate'">
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
            <el-table-column width="280px" align="center" label="验证码类型1:注册 2:忘记密码 3修改登陆密码 4.修改支付密码">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeType }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="手机号">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeMobile }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="短信验证码">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeCode }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="验证码是否有效">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeState }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="验证码内容">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeText }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="验证码生成时间">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeCreateTime }}</span>
                </template>
            </el-table-column>
                <el-table-column width="280px" align="center" label="验证码过期时间">
                <template slot-scope="scope">
                    <span>{{ scope.row.codeExpirationTime }}</span>
                </template>
            </el-table-column>
                <el-table-column class-name="status-col" label="状态" width="110px">
                <template slot-scope="{row}">
                    <el-tag :type="row.status">{{ row.status| statusFilter}}</el-tag>
                </template>
            </el-table-column>

            <el-table-column fixed="right" align="left" label="操作" width="120px">
                <template slot-scope="scope">
                    <router-link v-permission="['sys:syssmscode:delete']" :to="'/systemResource/SysSmsCodeEdit/'+scope.row.id">
                        <el-tooltip class="item" effect="dark" content="编辑" placement="bottom">
                            <el-button v-permission="['sys:syssmscode:update']" type="primary" size="small" icon="el-icon-edit" />
                        </el-tooltip>
                    </router-link>
                    <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
                        <el-button
                                size="small"
                                type="primary"
                                icon="el-icon-delete"
                                @click="deleteSysSmsCode(scope.row.id)"
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
    import { findByListSysSmsCode, delSysSmsCode } from "@/api/sys/SysSmsCodeApi";
    import { findByTypeListSysDictOption } from "@/api/sys/SysDictOptionApi";
    import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
    let vm = {};
    export default {
        name: "SysSmsCodeList",
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
                findByListSysSmsCode(this.listQuery).then(response => {
                    this.list = response.data.items;
                    this.total = response.data.total;
                    this.listLoading = false;
                });
            },
            handleFilter() {
                this.listQuery.page = 1;
                this.getList();
            },
            deleteSysSmsCode(data) {
                delSysSmsCode({ id: data }).then(response => {
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
