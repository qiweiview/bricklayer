package com.buildSupport.db_adapter;

import com.buildSupport.db_model.DBColumnModel;
import com.buildSupport.db_model.DBTableModel;
import com.buildSupport.utils.JDBCResultUtils;

import java.sql.*;
import java.util.*;
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
        List<String> collect = maps.stream().map(x -> x.get("Database").toString()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<String> getTables(String dbName) {
        List<Map> maps = doQuery("SELECT TABLE_NAME  \n" +
                "FROM INFORMATION_SCHEMA.TABLES \n" +
                "WHERE TABLE_SCHEMA = '"+dbName+"' order by TABLE_NAME");
        List<String> collect = maps.stream().map(x -> x.get("TABLE_NAME").toString()).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, userName, passWord);
        } catch (SQLException throwables) {
           throw new RuntimeException("连接数据库失败");
        }
        return conn;
    }

    public List<Map> doQuery(String sql) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Map> maps = JDBCResultUtils.parseResult(resultSet);

            return maps;
        } catch (Exception se) {
            throw new RuntimeException("get meta data fail,cause " + se);
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
    public List<DBTableModel> getDBTableModels(String dbName) {
        List<Map> maps = doQuery("SELECT extra,table_name, column_name, is_nullable, data_type, column_comment , column_type ,column_key FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = '"+dbName+"'");
        Map<Object, List<Map>> table_name = maps.stream().collect(Collectors.groupingBy(x -> {
            return x.get("TABLE_NAME");
        }));
        List<DBTableModel> dbTableModels = new ArrayList<>();
        table_name.forEach((k, v) -> {
            //todo 表分组
            DBTableModel dbTableModel = new DBTableModel();
            List<DBColumnModel> dbColumnModelList = new ArrayList();
            dbTableModel.setOriginalTableName(k.toString());
            dbTableModel.setDbColumnModelList(dbColumnModelList);
            v.forEach(z -> {

                //todo 列
                DBColumnModel dbColumnModel = new DBColumnModel();
                dbColumnModel.setExtra(z.getOrDefault("EXTRA", "").toString());
                dbColumnModel.setColumnKey(z.getOrDefault("COLUMN_KEY", "").toString());
                dbColumnModel.setOriginalColumnName(z.getOrDefault("COLUMN_NAME", "").toString());
                dbColumnModel.setType(z.getOrDefault("DATA_TYPE", "").toString());
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


}
