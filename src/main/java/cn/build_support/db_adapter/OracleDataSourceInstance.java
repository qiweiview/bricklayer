package cn.build_support.db_adapter;


import cn.anicert.model.dto.BricklayerColumnDTO;
import cn.anicert.model.dto.BricklayerTableDTO;

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
    public List<BricklayerTableDTO> getDBTableModelsByDataSource(String dbName) {
        List<Map> maps = doQuery("SELECT\n" +
                "\ta.COLUMN_NAME,\n" +
                "\ta.DATA_TYPE,\n" +
                "\tNVL( b.comments, '1' ) \n" +
                "FROM\n" +
                "\tuser_tab_columns a,\n" +
                "\tuser_col_comments b \n" +
                "WHERE\n" +
                "\t1 = 1 \n" +
                "\tAND a.table_Name = b.table_Name \n" +
                "\tAND a.COLUMN_NAME = b.COLUMN_NAME \n" +
                "\tAND a.table_Name IN ( SELECT table_name FROM all_tables WHERE owner = ? ) \n" +
                "ORDER BY\n" +
                "\tcolumn_name", dbName);
        Map<Object, List<Map>> table_name = maps.stream().collect(Collectors.groupingBy(x -> {
            return x.get("TABLE_NAME");
        }));
        List<BricklayerTableDTO> dbTableModels = new ArrayList<>();
        table_name.forEach((k, v) -> {
            //todo 表分组
            BricklayerTableDTO dbTableModel = new BricklayerTableDTO();
            List<BricklayerColumnDTO> dbColumnModelList = new ArrayList();
            dbTableModel.setOriginalTableName(k.toString());
            dbTableModel.setBricklayerColumnDTOList(dbColumnModelList);
            v.forEach(z -> {

                //todo 列
                BricklayerColumnDTO dbColumnModel = new BricklayerColumnDTO();
                dbColumnModel.setExtra(z.getOrDefault("EXTRA", "").toString());
                dbColumnModel.setColumnKey(z.getOrDefault("COLUMN_KEY", "").toString());
                dbColumnModel.setOriginalColumnName(z.getOrDefault("COLUMN_NAME", "").toString());
                dbColumnModel.setSimpleColumnType(z.getOrDefault("DATA_TYPE", "").toString());
                dbColumnModel.setColumnType(z.getOrDefault("DATA_TYPE", "").toString());
                dbColumnModel.setComment(z.getOrDefault("COLUMN_COMMENT", "").toString());

                if (targetColumn.contains(dbColumnModel.getOriginalColumnName()) || (targetColumn.size() == 1 && targetColumn.contains("*"))) {
                    dbColumnModelList.add(dbColumnModel);
                }
            });
            if (targetTable.contains(dbTableModel.getOriginalTableName()) || (targetTable.size() == 1 && targetTable.contains("*"))) {
                dbTableModels.add(dbTableModel);
            }
        });
        return dbTableModels;
    }

    @Override
    public List<BricklayerTableDTO> getDBTableModelsByTables(List<String> tables) {
        return null;
    }

    @Override
    public BricklayerTableDTO getDBTableModelByName(String taleName) {
        List<Map> maps = doQuery("SELECT\n" +
                "\ta.COLUMN_NAME COLUMN_NAME ,\n" +
                "\ta.DATA_TYPE DATA_TYPE,\n" +
                "\tNVL(b.comments , '-')  as COLUMN_COMMENT \n" +
                "FROM\n" +
                "\tuser_tab_columns a,\n" +
                "\tuser_col_comments b \n" +
                "WHERE\n" +
                "\t1 = 1 \n" +
                "\tAND a.table_Name = b.table_Name \n" +
                "\tAND a.COLUMN_NAME = b.COLUMN_NAME \n" +
                "\tAND a.table_Name = ? \n" +
                "ORDER BY\n" +
                "\tcolumn_name", taleName);
        //todo 表分组
        BricklayerTableDTO dbTableModel = new BricklayerTableDTO();
        List<BricklayerColumnDTO> dbColumnModelList = new ArrayList();
        dbTableModel.setOriginalTableName(taleName);
        dbTableModel.setBricklayerColumnDTOList(dbColumnModelList);
        maps.forEach(z -> {

            //todo 列
            BricklayerColumnDTO dbColumnModel = new BricklayerColumnDTO();
            dbColumnModel.setExtra(z.getOrDefault("EXTRA", "").toString());
            dbColumnModel.setColumnKey(z.getOrDefault("COLUMN_KEY", "").toString());
            dbColumnModel.setOriginalColumnName(z.getOrDefault("COLUMN_NAME", "").toString());
            dbColumnModel.setSimpleColumnType(z.getOrDefault("DATA_TYPE", "").toString());
            dbColumnModel.setColumnType(z.getOrDefault("DATA_TYPE", "").toString());
            dbColumnModel.setComment(z.getOrDefault("COLUMN_COMMENT", "").toString());
            dbColumnModelList.add(dbColumnModel);

        });
        return dbTableModel;
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
