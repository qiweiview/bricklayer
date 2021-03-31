package build.db_adapter;

import build.db_model.DBColumnModel;
import build.db_model.DBTableModel;
import build.utils.JDBCResultUtils;

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
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public List<DBTableModel> getDBTableModels() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(jdbcDriver);

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

                    if (targetColumn.contains(dbColumnModel.getOriginalColumnName()) || (targetColumn.size() == 1 && targetColumn.contains("*"))) {
                        dbColumnModelList.add(dbColumnModel);
                    }
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
