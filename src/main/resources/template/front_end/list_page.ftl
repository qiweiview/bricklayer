<template>
    <el-row style="padding: 10px">

        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-input @change="query${className}" clearable v-model="queryKey" placeholder="输入查询主键"
                      style="width: 20%"></el-input>
            <el-button size="mini" @click="query${className}" style="margin-left:15px">查 询</el-button>
            <el-button size="mini" style="margin-left: 15px;" @click="openDBConnectDialog">新 增</el-button>
            <el-table :data="tableList">
                <#list fieldList as field>
                    <el-table-column label="${field.columnComment}">
                        <template slot-scope="scope"><span
                                    style="text-align: left">{{ scope.row.${field.beanName} }}</span>
                        </template>
                    </el-table-column>
                </#list>
                <el-table-column label="操作" width="300px">
                    <template slot-scope="scope">
                        <el-button size="mini" type="success" @click="openViewDialog(scope.row)">查 看</el-button>
                        <el-button size="mini" type="primary" @click="openEditDialog(scope.row)">编 辑</el-button>
                        <el-button size="mini" type="danger" @click="delete${className}(scope.row)">删 除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="sizeChange"
                    @current-change="pageChange"
        background
        :page-sizes="[5, 10, 20, 50,100]"
        :page-size="recordRows"
        layout="sizes, prev, pager, next"
        :total="total">
            </el-pagination>
        </el-col>
        <el-dialog top="3vh" :close-on-press-escape="false" :close-on-click-modal="false" title="新增数据"
        :visible.sync="dbConnectionDialog" width="40%">
            <el-form label-position="top">
        <#list fieldList as field>
                <el-form-item label="${field.columnComment}">
            <#if field.javaType?contains("Integer")>
        <el-input-number  style="width: 80%" v-model="${className?uncap_first}Insert.${field.beanName}"></el-input-number>
            <#else >
        <el-input   style="width: 80%" v-model="${className?uncap_first}Insert.${field.beanName}"></el-input>
            </#if>
                </el-form-item>
        </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeDBConnectDialog">取 消</el-button>
                <el-button type="primary" @click="insert${className}">保 存</el-button>
            </div>
        </el-dialog>
        <el-dialog top="3vh" :close-on-press-escape="false" :close-on-click-modal="false" title="编辑数据"
        :visible.sync="editDialog" width="30%">
            <el-form label-position="top">
        <#list fieldList as field>
        <el-form-item label="${field.columnComment}">
        <#if field.javaType?contains("Integer")>
        <el-input-number :disabled="editEnable"  style="width: 80%" v-model="${className?uncap_first}Edit.${field.beanName}"></el-input-number>
            <#else >
        <el-input :disabled="editEnable"  style="width: 80%" v-model="${className?uncap_first}Edit.${field.beanName}"></el-input>
        </#if>
        </el-form-item>
        </#list>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeEditDialog">取 消</el-button>
                <el-button v-show="!editEnable" type="primary" @click="update${className}">更 新</el-button>
            </div>
        </el-dialog>
    </el-row>
</template>

<script>
    import request from "@/config/requestConfig";

    export default {
        name: "serviceList",
        data() {
            return {
                queryKey: '',
                editEnable: false,
                editDialog: false,
                dbConnectionDialog: false,
                tableList: [],
                recordRows: 5,//单页条数
                recordCurrentPage: 1,//当前页
                total: 0,//总计条数
                ${className?uncap_first}Insert: {
            <#list fieldList as field>
            ${field.beanName}:
            '',
            </#list>
        },
            ${className?uncap_first}Edit: {
            }
        ,
            ${className?uncap_first}Query: {
                <#list fieldList as field>
                ${field.beanName}:
                '',
                </#list>
            }
        }
        },
        methods: {
            clearInsertForm() {
                this.${className?uncap_first}Insert = {
                <#list fieldList as field>
            ${field.beanName}: '',
        </#list>
        }
        },
        delete${className}(row) {
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
        }).then(() => {
        request({
        url: '/${contextModel.contextPath}/${className?uncap_first}/delete',
        method: 'post',
        data: {id: row.id}
        }).then(() => {
        this.query${className}()
        }).catch(() => {
        })
        }).catch(() => {

        });
        },
        closeEditDialog() {
        this.editDialog = false;
        },
        openViewDialog(row) {
        this.${className?uncap_first}Edit = row
        this.editDialog = true
        this.editEnable = true
        },
        openEditDialog(row) {
        let one = JSON.parse(JSON.stringify(row))
        this.${className?uncap_first}Edit = one
        this.editDialog = true
        this.editEnable = false
        },
        closeDBConnectDialog() {
        this.dbConnectionDialog = false;
        },
        openDBConnectDialog() {
        this.dbConnectionDialog = true;
        },
        pageChange(page) {
        this.recordCurrentPage = page
        this.query${className}()
        },
        sizeChange(size) {
        this.recordRows = size
        this.query${className}()
        },
        query${className}() {
        //赋值条数
        this.${className?uncap_first}Query.size = this.recordRows
        this.${className?uncap_first}Query.current = this.recordCurrentPage

        const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
        });
        request({
        url: '/${contextModel.contextPath}/${className?uncap_first}/listPage',
        method: 'post',
        data: this.${className?uncap_first}Query
        }).then(response => {
        this.total = response.data.total
        this.tableList = response.data.records
        }).catch(() => {
        }).finally(() => {
        loading.close()
        })
        },
        update${className}() {
        request({
        url: '/${contextModel.contextPath}/${className?uncap_first}/update',
        method: 'post',
        data: this.${className?uncap_first}Edit
        }).then(() => {
        this.closeEditDialog()
        this.query${className}()
        }).catch(() => {
        })
        },
        insert${className}() {
        request({
        url: '/${contextModel.contextPath}/${className?uncap_first}/save',
        method: 'post',
        data: this.${className?uncap_first}Insert
        }).then(() => {
        this.closeDBConnectDialog()
        this.query${className}()
        this.clearInsertForm()
        }).catch(() => {
        })
        }
        }, mounted() {
        this.query${className}()
        }
        }
</script>

<style scoped>

</style>
