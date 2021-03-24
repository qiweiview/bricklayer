package build.data_bases;

import build.ProjectBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataSourceInstance {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/anicert_university?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "wdwdwd";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT table_name, column_name, is_nullable, data_type, column_comment , column_type FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = ( SELECT DATABASE() )");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map> maps = parseResult(resultSet);
            Map<Object, List<Map>> table_name = maps.stream().collect(Collectors.groupingBy(x -> {
                return x.get("TABLE_NAME");
            }));
            List<DBTableModel> dbTableModels = new ArrayList<>();
            table_name.forEach((k, v) -> {
                //todo 表分组
                if ("training_detail".equals(k)) {
                    DBTableModel dbTableModel = new DBTableModel();
                    List<DBColumnModel> dbColumnModelList = new ArrayList();
                    dbTableModel.setName(underLine2CapFirst(k.toString()));
                    dbTableModel.setDbColumnModelList(dbColumnModelList);
                    v.forEach(z -> {
                        //todo 列
                        DBColumnModel dbColumnModel = new DBColumnModel();
                        dbColumnModel.setType(z.getOrDefault("DATA_TYPE","").toString());
                        dbColumnModel.setName(underLine2UnCapFirst(z.getOrDefault("COLUMN_NAME","").toString()));
                        dbColumnModel.setComment(z.getOrDefault("COLUMN_COMMENT","").toString());
                        dbColumnModelList.add(dbColumnModel);

                    });
                    dbTableModels.add(dbTableModel);
                }
            });
            ProjectBuilder projectBuilder = new ProjectBuilder();
            String basePath = "D:\\JAVA_WORK_SPACE\\bricklayer\\src\\main\\java\\";
            try {
                projectBuilder.build(dbTableModels, basePath);
            } catch (Exception e) {
                e.printStackTrace();
            }



        } catch (Exception se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        }


    }

    public static String underLine2UnCapFirst(String underLine){
        StringBuilder stringBuilder=new StringBuilder();
        String[] s = underLine.split("_");
        for (int i=0;i<s.length;i++){
            String s1 = s[i].toLowerCase();
            if (i==0){
                stringBuilder.append(s1);
            }else {
                stringBuilder.append(s1.substring(0,1).toUpperCase()+s1.substring(1));
            }

        }

        return stringBuilder.toString();
    }

    public static String underLine2CapFirst(String underLine){
        StringBuilder stringBuilder=new StringBuilder();
        String[] s = underLine.split("_");
        Stream.of(s).forEach(x->{
            String s1 = x.toLowerCase();
            stringBuilder.append(s1.substring(0,1).toUpperCase()+s1.substring(1));
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
