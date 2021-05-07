package cn.buildSupport.db_adapter;


import cn.anicert.model.dto.BricklayerColumnDTO;
import cn.anicert.model.dto.BricklayerTableDTO;
import cn.buildSupport.utils.JDBCResultUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MysqlAbstractDataSourceInstance extends AbstractDataSourceInstance {


    public MysqlAbstractDataSourceInstance(String dbUrl, String userName, String passWord) {
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
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, userName, passWord);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("connect to datasource fail");
        }
        return conn;
    }

    /**
     * 注入风险需要修复
     *
     * @param sql
     * @return
     */
    public List<Map> doQuery(String sql, Object... objects) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            //数据拼接
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            resultSet = preparedStatement.executeQuery();
            List<Map> maps = JDBCResultUtils.parseResult(resultSet);

            return maps;
        } catch (Exception se) {
            throw new RuntimeException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                throw new RuntimeException("close fail");
            }

        }
    }

    @Override
    public List<BricklayerTableDTO> getDBTableModelsByDataSource(String dbName) {
        List<Map> maps = doQuery("SELECT extra,table_name, column_name, is_nullable, data_type, column_comment , column_type ,column_key FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = ?", dbName);
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
                dbColumnModel.setColumnType(z.getOrDefault("COLUMN_TYPE", "").toString());
                dbColumnModel.setColumnKey(z.getOrDefault("COLUMN_KEY", "").toString());
                dbColumnModel.setOriginalColumnName(z.getOrDefault("COLUMN_NAME", "").toString());
                dbColumnModel.setSimpleColumnType(z.getOrDefault("DATA_TYPE", "").toString());
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
    public BricklayerTableDTO getDBTableModelByName(String taleName) {
        List<Map> maps = doQuery("SELECT extra,table_name, column_name, is_nullable, data_type, column_comment , column_type ,column_key FROM information_schema.COLUMNS WHERE table_name = ?", taleName);
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
            dbColumnModel.setColumnType(z.getOrDefault("COLUMN_TYPE", "").toString());
            dbColumnModel.setComment(z.getOrDefault("COLUMN_COMMENT", "").toString());
            dbColumnModelList.add(dbColumnModel);

        });
        return dbTableModel;

    }


}
