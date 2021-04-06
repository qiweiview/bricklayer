<template>
    <el-row style="padding: 10px">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-button @click="query${className}" style="margin-left:15px">查 询</el-button>
            <el-button @click="insert${className}" style="margin-left:15px" type="success">新 增</el-button>
            <el-table :data="hostList">
                <el-table-column label="域名规则字符">
                    <template slot-scope="scope"><span style="text-align: left">{{ scope.row.hostKeyWord }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="路由操作">
                    <template slot-scope="scope"><span
                                style="text-align: left">{{ routeTypeCN(scope.row.routeType)}}</span>
                    </template>
                </el-table-column>
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
    //import request from "@/config/requestConfig";

    export default {
        name: "serviceList",
        data() {
            return {
                recordRows: 10,
                chanelRecordTotal: 0
            }
        },
        methods: {
            query${className}(){
            },
            insert${className}(){

            },
            get${className}(){

            },
            edit${className}(){

            },
            delete${className}(){

            }
        }, mounted() {

        }
    }
</script>

<style scoped>

</style>
