package cn.build_support.db_adapter;


import cn.bricklayer.model.dto.BricklayerColumnDTO;
import cn.bricklayer.model.dto.BricklayerTableDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MysqlDataSourceInstance extends AbstractDataSourceInstance {


    public MysqlDataSourceInstance(String dbUrl, String userName, String passWord) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.passWord = passWord;
    }


    @Override
    public String getDriverClassName() {
        String s = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<String> getDatabases() {
        List<Map> maps = doQuery("show databases;");
        List<String> collect = maps.stream().map(x -> {
            //todo 获取名称
            String dbName = "mysql";
            Object DBName = x.get("DBName");
            if (DBName != null) {
                dbName = DBName.toString();
            }

            Object Database = x.get("Database");
            if (Database != null) {
                dbName = Database.toString();
            }


            Object SCHEMA_NAME = x.get("SCHEMA_NAME");
            if (SCHEMA_NAME != null) {
                dbName = SCHEMA_NAME.toString();
            }

            return dbName;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<String> getTables(String dbName) {
        List<Map> maps = doQuery("SELECT TABLE_NAME  \n" +
                "FROM INFORMATION_SCHEMA.TABLES \n" +
                "WHERE TABLE_SCHEMA = ? order by TABLE_NAME", dbName);
        List<String> collect = maps.stream().map(x -> x.get("TABLE_NAME").toString()).collect(Collectors.toList());
        return collect;
    }



    @Override
    public List<BricklayerTableDTO> getDBTableModelsByTables(String dataSourceName, List<String> tables) {
        String tablesStr = tables.stream().map(x -> {
            return "'" + x + "'";
        }).collect(Collectors.joining(","));
        List<Map> maps = doQuery("SELECT extra,table_name, column_name, is_nullable, data_type, column_comment , column_type ,column_key FROM information_schema.COLUMNS WHERE  table_schema =? and table_name in (" + tablesStr + ") ", dataSourceName);

        //todo 表分组
        Map<Object, List<Map>> table_name = maps.stream().collect(Collectors.groupingBy(x -> {
            return x.get("TABLE_NAME");
        }));

        List<BricklayerTableDTO> dbTableModels = new ArrayList<>();
        table_name.forEach((k, v) -> {
            //todo 循环表
            BricklayerTableDTO dbTableModel = new BricklayerTableDTO();
            List<BricklayerColumnDTO> dbColumnModelList = new ArrayList();
            dbTableModel.setOriginalTableName(k.toString());
            dbTableModel.setBricklayerColumnDTOList(dbColumnModelList);
            v.forEach(z -> {
                //todo 循环列
                BricklayerColumnDTO dbColumnModel = new BricklayerColumnDTO();
                dbColumnModel.setExtra(z.getOrDefault("EXTRA", "").toString());
                dbColumnModel.setColumnType(z.getOrDefault("COLUMN_TYPE", "").toString());
                dbColumnModel.setColumnKey(z.getOrDefault("COLUMN_KEY", "").toString());
                dbColumnModel.setOriginalColumnName(z.getOrDefault("COLUMN_NAME", "").toString());
                dbColumnModel.setSimpleColumnType(z.getOrDefault("DATA_TYPE", "").toString());
                dbColumnModel.setComment(z.getOrDefault("COLUMN_COMMENT", "").toString());

                dbColumnModelList.add(dbColumnModel);
            });
            dbTableModels.add(dbTableModel);
        });
        return dbTableModels;
    }




}
