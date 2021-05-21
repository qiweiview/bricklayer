package cn.build_support.db_adapter;

import cn.anicert.model.dto.BricklayerTableDTO;
import cn.anicert.utils.JDBCResultUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public abstract List<BricklayerTableDTO> getDBTableModelsByDataSource(String dbName);

    public abstract List<BricklayerTableDTO> getDBTableModelsByTables(List<String> tables);

    public abstract BricklayerTableDTO getDBTableModelByName(String taleName);

    public abstract List<String> getTables(String dbName);


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
            se.printStackTrace();
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


}
