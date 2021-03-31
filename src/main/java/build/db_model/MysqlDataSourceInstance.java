package build.db_model;

import build.db_adapter.DataSourceInstance;
import build.utils.JDBCResultUtils;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class MysqlDataSourceInstance implements DataSourceInstance {
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private String dbUrl;
    private String userName;
    private String passWord;
    private Set<String> targetTable = new HashSet<>();

    public MysqlDataSourceInstance(String dbUrl, String userName, String passWord) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.passWord = passWord;
    }

    public void addTargetTableName(String tableName) {
        targetTable.add(tableName);
    }

    @Override
    public List<DBTableModel> getDBTableModels() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(jdbcDriver);

            // 打开链接
            conn = DriverManager.getConnection(dbUrl, userName, passWord);
            preparedStatement = conn.prepareStatement("SELECT extra,table_name, column_name, is_nullable, data_type, column_comment , column_type ,column_key FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = ( SELECT DATABASE() )");
            resultSet = preparedStatement.executeQuery();
            List<Map> maps = JDBCResultUtils.parseResult(resultSet);
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
                    dbColumnModelList.add(dbColumnModel);

                });


                if (targetTable.contains(dbTableModel.getOriginalTableName()) || (targetTable.size() == 1 && targetTable.contains("*"))) {
                    dbTableModels.add(dbTableModel);
                }

            });
            return dbTableModels;
        } catch (Exception se) {
            throw new RuntimeException("get meta data fail");
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
