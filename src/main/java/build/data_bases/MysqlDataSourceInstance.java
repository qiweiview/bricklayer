package build.data_bases;

import build.ProjectBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MysqlDataSourceInstance {
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private String dbUrl;
    private String userName;
    private String passWord;

    public MysqlDataSourceInstance(String dbUrl, String userName, String passWord) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.passWord = passWord;
    }

    public static void main(String[] args) throws Exception {
        MysqlDataSourceInstance mysqlDataSourceInstance = new MysqlDataSourceInstance("jdbc:mysql://localhost:3306/seata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "123");
        ProjectBuilder projectBuilder = new ProjectBuilder();
        // String basePath = "D:\\JAVA_WORK_SPACE\\bricklayer\\src\\main\\java\\";
        String basePath = "D:\\NewWorkSpace\\bricklayer\\src\\main\\java";
        projectBuilder.build(mysqlDataSourceInstance.getDBTableModels(), basePath);

    }

    public List<DBTableModel> getDBTableModels() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(jdbcDriver);

            // 打开链接
            conn = DriverManager.getConnection(dbUrl, userName, passWord);
            preparedStatement = conn.prepareStatement("SELECT table_name, column_name, is_nullable, data_type, column_comment , column_type FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = ( SELECT DATABASE() )");
            resultSet = preparedStatement.executeQuery();
            List<Map> maps = parseResult(resultSet);
            Map<Object, List<Map>> table_name = maps.stream().collect(Collectors.groupingBy(x -> {
                return x.get("TABLE_NAME");
            }));
            List<DBTableModel> dbTableModels = new ArrayList<>();
            table_name.forEach((k, v) -> {
                //todo 表分组
                DBTableModel dbTableModel = new DBTableModel();
                List<DBColumnModel> dbColumnModelList = new ArrayList();
                dbTableModel.setName(underLine2CapFirst(k.toString()));
                dbTableModel.setDbColumnModelList(dbColumnModelList);
                v.forEach(z -> {
                    //todo 列
                    DBColumnModel dbColumnModel = new DBColumnModel();
                    dbColumnModel.setType(z.getOrDefault("DATA_TYPE", "").toString());
                    dbColumnModel.setName(underLine2UnCapFirst(z.getOrDefault("COLUMN_NAME", "").toString()));
                    dbColumnModel.setComment(z.getOrDefault("COLUMN_COMMENT", "").toString());
                    dbColumnModelList.add(dbColumnModel);

                });
                dbTableModels.add(dbTableModel);
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


    public static String underLine2UnCapFirst(String underLine) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] s = underLine.split("_");
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i].toLowerCase();
            if (i == 0) {
                stringBuilder.append(s1);
            } else {
                stringBuilder.append(s1.substring(0, 1).toUpperCase() + s1.substring(1));
            }

        }

        return stringBuilder.toString();
    }

    public static String underLine2CapFirst(String underLine) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] s = underLine.split("_");
        Stream.of(s).forEach(x -> {
            String s1 = x.toLowerCase();
            stringBuilder.append(s1.substring(0, 1).toUpperCase() + s1.substring(1));
        });
        return stringBuilder.toString();
    }

    private static List<Map> parseResult(ResultSet rs) throws SQLException {
        List<Map> list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名

        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }
}
