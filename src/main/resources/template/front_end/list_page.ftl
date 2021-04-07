<template>
    <el-row style="padding: 10px">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-button @click="query${className}" style="margin-left:15px">查 询</el-button>
            <el-button @click="insert${className}" style="margin-left:15px" type="success">新 增</el-button>
            <el-table :data="tableList">
                <#list fieldList as field>
                    <el-table-column label="${field.columnComment}">
                        <template slot-scope="scope"><span style="text-align: left">{{ scope.row.${field.originalColumnName} }}</span>
                        </template>
                    </el-table-column>
                </#list>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" type="success" @click="get${className}(scope.row.id)">查 看</el-button>
                        <el-button size="mini" type="info" @click="edit${className}(scope.row.id)">编 辑</el-button>
                        <el-button size="mini" type="danger" @click="delete${className}(scope.row.id)">删 除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="sizeChange"
                    @current-change="pageChange"
                    background
                    :page-sizes="[10, 15, 30, 100]"
                    :page-size="recordRows"
                    layout="sizes, prev, pager, next"
                    :total="chanelRecordTotal">
            </el-pagination>
        </el-col>


    </el-row>
</template>

<script>
    import request from "@/config/requestConfig";

    export default {
        name: "serviceList",
        data() {
            return {
                tableList:[],
                recordRows: 10,
                recordCurrentPage: 1,
                chanelRecordTotal: 0,
                ${className?uncap_first}Insert:{
                <#list fieldList as field>
                    ${field.originalColumnName}: '',
                </#list>
                                        },
            ${className?uncap_first}Query:{
                <#list fieldList as field>
                ${field.originalColumnName}: '',
                </#list>
            }
        }
    },
        methods: {
            pageChange(page) {
                this.recordCurrentPage = page
                this.query${className}()
            },
            sizeChange(size) {
                this.recordRows = size
                this.query${className}()
            },
            query${className}(){
                request({
                    url: '/query${className}',
                    method: 'post',
                    data: this.${className?uncap_first}Query
                }).then(response => {
                    this.tableList = response
                }).catch(() => {
                })
            },
            insert${className}(){
                request({
                    url: '/insert${className}',
                    method: 'post',
                    data: this.${className?uncap_first}Insert
                }).then(response => {
                    this.tableList = response
                }).catch(() => {
                })
            },
            get${className}(){
                request({
                    url: '/get${className}',
                    method: 'post',
                    data: this.${className?uncap_first}Query
                }).then(response => {
                    this.tableList = response
                }).catch(() => {
                })
            },
            edit${className}(){
                request({
                    url: '/edit${className}',
                    method: 'post',
                    data: this.${className?uncap_first}Insert
                }).then(response => {
                    this.tableList = response
                }).catch(() => {
                })
            },
            delete${className}(){
                request({
                    url: '/delete${className}',
                    method: 'post',
                    data: this.${className?uncap_first}Insert
                }).then(response => {
                    this.tableList = response
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
