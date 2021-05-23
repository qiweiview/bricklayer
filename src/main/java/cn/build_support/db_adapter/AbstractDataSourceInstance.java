package cn.build_support.db_adapter;

import cn.anicert.model.dto.BricklayerTableDTO;
import cn.anicert.utils.JDBCResultUtils;
import cn.anicert.utils.MessageRuntimeException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractDataSourceInstance {
    String jdbcDriver;
    String dbUrl;
    String userName;
    String passWord;


    public AbstractDataSourceInstance() {
        jdbcDriver = getDriverClassName();
    }


    /**
     * 获取驱动类
     *
     * @return
     */
    public abstract String getDriverClassName();

    /**
     * 获取数据库
     *
     * @return
     */
    public abstract List<String> getDatabases();


    /**
     * 通过标名集合获取多个表模型
     *
     * @param tables
     * @return
     */
    public abstract List<BricklayerTableDTO> getDBTableModelsByTables(String dataSourceName, List<String> tables);


    public BricklayerTableDTO getDBTableModelByName(String dataSourceName, String taleName) {
        List<String> collect = Stream.of(new String[]{taleName}).collect(Collectors.toList());
        List<BricklayerTableDTO> dbTableModelsByTables = getDBTableModelsByTables(dataSourceName, collect);
        if (dbTableModelsByTables.size() < 1) {
            throw new MessageRuntimeException("无法找到匹配数据");
        }
        return dbTableModelsByTables.get(0);

    }


    /**
     * 通过数据库名称获取表名集合
     *
     * @param dbName
     * @return
     */
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
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i + 1, objects[i]);
                }
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
