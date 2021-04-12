package com.buildSupport.db_adapter;

import com.buildSupport.db_model.DBTableModel;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractDataSourceInstance {
     String jdbcDriver ;
     String dbUrl;
     String userName;
     String passWord;
     Set<String> targetTable = new HashSet<>();
     Set<String> targetColumn = new HashSet<>();

    public AbstractDataSourceInstance() {
        jdbcDriver = getDriverClassName();
        targetTable.add("*");
        targetColumn.add("*");
    }




    public abstract String getDriverClassName();

    public void addTargetTableName(String tableName) {
        targetTable.add(tableName);
    }

    public void addTargetColumn(String tableColumn) {
        targetColumn.add(tableColumn);
    }

    public abstract List<String> getDatabases();

    public abstract List<DBTableModel> getDBTableModels(String dbName);

    public abstract List<String> getTables(String dbName);

    public abstract Connection getConnection();
}
