package build.data_bases;

import build.ProjectBuilder;
import build.utils.JDBCResultUtils;
import build.utils.StringUtils4V;

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
        MysqlDataSourceInstance mysqlDataSourceInstance = new MysqlDataSourceInstance("jdbc:mysql://localhost:3306/anicert_university?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "wdwdwd");
        ProjectBuilder projectBuilder = new ProjectBuilder();
         String basePath = "D:\\JAVA_WORK_SPACE\\bricklayer\\src\\main\\java\\";
        //String basePath = "D:\\NewWorkSpace\\bricklayer\\src\\main\\java";
        projectBuilder.setBefore("test");
        List<DBTableModel> dbTableModels = mysqlDataSourceInstance.getDBTableModels();
        dbTableModels.forEach(x->{
            System.out.println(x.getOriginalName());
        });
       // projectBuilder.build(dbTableModels, basePath);

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
            List<Map> maps = JDBCResultUtils.parseResult(resultSet);
            Map<Object, List<Map>> table_name = maps.stream().collect(Collectors.groupingBy(x -> {
                return x.get("TABLE_NAME");
            }));
            List<DBTableModel> dbTableModels = new ArrayList<>();
            table_name.forEach((k, v) -> {
                //todo 表分组
                DBTableModel dbTableModel = new DBTableModel();
                List<DBColumnModel> dbColumnModelList = new ArrayList();
                dbTableModel.setOriginalName(k.toString());
                dbTableModel.setName(StringUtils4V.underLine2UnCapFirst(k.toString(),false));
                dbTableModel.setDbColumnModelList(dbColumnModelList);
                v.forEach(z -> {
                    //todo 列
                    DBColumnModel dbColumnModel = new DBColumnModel();
                    dbColumnModel.setOriginalName(z.getOrDefault("COLUMN_NAME", "").toString());
                    dbColumnModel.setType(z.getOrDefault("DATA_TYPE", "").toString());
                    dbColumnModel.setName(StringUtils4V.underLine2UnCapFirst(z.getOrDefault("COLUMN_NAME", "").toString(),true));
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




}
