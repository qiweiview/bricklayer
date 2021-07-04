package cn.build_support.db_adapter;


import cn.bricklayer.model.dto.BricklayerColumnDTO;
import cn.bricklayer.model.dto.BricklayerTableDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OracleDataSourceInstance extends AbstractDataSourceInstance {


    public OracleDataSourceInstance(String dbUrl, String userName, String passWord) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.passWord = passWord;
    }


    @Override
    public String getDriverClassName() {
        String s = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<String> getDatabases() {
        List<Map> maps = doQuery("SELECT USERNAME FROM ALL_USERS ORDER BY USERNAME");
        List<String> collect = maps.stream().map(x -> {
            //todo 获取名称
            String dbName = "SYS";
            Object USERNAME = x.get("USERNAME");
            if (USERNAME != null) {
                dbName = USERNAME.toString();
            }


            return dbName;
        }).collect(Collectors.toList());
        return collect;
    }


    @Override
    public List<BricklayerTableDTO> getDBTableModelsByTables(String dataSourceName, List<String> tables) {
        String tablesStr = tables.stream().map(x -> {
            return "'" + x + "'";
        }).collect(Collectors.joining(","));
        List<Map> maps = doQuery("SELECT a.* , decode(b.COLUMN_NAME, NULL, '-', 'primary') AS COLUMN_KEY FROM ( SELECT a.table_Name, a.COLUMN_NAME AS COLUMN_NAME, a.DATA_TYPE AS DATA_TYPE , NVL(b.comments, '-') AS COLUMN_COMMENT FROM user_tab_columns a, user_col_comments b WHERE 1 = 1 AND a.table_Name = b.table_Name AND a.COLUMN_NAME = b.COLUMN_NAME AND a.table_Name IN (" + tablesStr + ") ) a LEFT JOIN ( SELECT a.column_name FROM user_cons_columns a, user_constraints b WHERE a.constraint_name = b.constraint_name AND b.constraint_type = 'P' AND a.table_name in (" + tablesStr + " )) b ON a.COLUMN_NAME = b.COLUMN_NAME", null);

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
                //oracle 没有自增
                //dbColumnModel.setExtra(z.getOrDefault("EXTRA", "").toString());

                dbColumnModel.setColumnType(z.getOrDefault("DATA_TYPE", "").toString());
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


    @Override
    public List<String> getTables(String dbName) {
        List<Map> maps = doQuery("SELECT\n" +
                "\ttable_name \n" +
                "FROM\n" +
                "\tall_tables \n" +
                "WHERE\n" +
                "\towner=? order by TABLE_NAME", dbName);
        List<String> collect = maps.stream().map(x -> x.get("TABLE_NAME").toString()).collect(Collectors.toList());
        return collect;
    }


}
