package build.test;

//import build.FreemarkerTemplateBuilder;
//import build.ProjectBuilder;
import build.build_task.BricklayerBuilder;
import build.db_model.DBTableModel;
import build.db_adapter.MysqlAbstractDataSourceInstance;
import build.java_bean.JavaBeanModel;

import java.util.List;
import java.util.stream.Collectors;

public class BuildTest {


    public static void main(String[] args) throws Exception {
        MysqlAbstractDataSourceInstance mysqlDataSourceInstance = new MysqlAbstractDataSourceInstance("jdbc:mysql://localhost:3306/anicert_university?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "wdwdwd");
//        MysqlDataSourceInstance mysqlDataSourceInstance = new MysqlDataSourceInstance("jdbc:mysql://localhost:3306/seata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
//                "root", "123");
        mysqlDataSourceInstance.addTargetTableName("training_plan");
        mysqlDataSourceInstance.addTargetColumn("id");

        String basePath="cn\\anicert\\university\\training\\";
        String outPutPath="D:\\JAVA_WORK_SPACE\\lite_spring_template\\src\\main\\java\\";
        List<DBTableModel> dbTableModels = mysqlDataSourceInstance.getDBTableModels();
        List<JavaBeanModel> collect = dbTableModels.stream().map(x -> JavaBeanModel.of(x,basePath)).collect(Collectors.toList());

        BricklayerBuilder.build(collect,outPutPath);

//        ProjectBuilder projectBuilder = new ProjectBuilder();
//        String basePath = "D:\\JAVA_WORK_SPACE\\lite_spring_template\\src\\main\\java\\com";
//        //String basePath = "D:\\NewWorkSpace\\bricklayer\\src\\main\\java";
//        projectBuilder.setDbFrameType(ProjectBuilder.JPA_FRAME);
//        projectBuilder.setBefore("cn.anicert.university.training_manage");
//        FreemarkerTemplateBuilder.suffixManager.setDaoSuffix("Repository");

//        List<DBTableModel> collect = dbTableModels.stream().filter(x -> {
//            return x.getOriginalName().indexOf("training") != -1 || x.getOriginalName().indexOf("organisation") != -1;
//        }).collect(Collectors.toList());
//        projectBuilder.build(dbTableModels, basePath);

    }

}
