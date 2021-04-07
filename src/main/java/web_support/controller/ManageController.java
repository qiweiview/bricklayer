package web_support.controller;

import build.build_task.BricklayerBuilder;
import build.db_adapter.MysqlAbstractDataSourceInstance;
import build.db_model.DBTableModel;
import build.java_bean.JavaBeanModel;
import org.springframework.web.bind.annotation.*;
import web_support.model.DBInfo;
import web_support.utils.UnifiedResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ManageController {



    @RequestMapping("/createConnection")
    public UnifiedResponse createConnection(@RequestBody DBInfo dbInfo) {
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(dbInfo.getConnectionPath(), dbInfo.getDbUser(), dbInfo.getDbPassWord());
        List<String> databases = mysqlAbstractDataSourceInstance.getDatabases();

        return UnifiedResponse.success(databases);

    }

    @RequestMapping("/getTables")
    public UnifiedResponse getTables(@RequestBody DBInfo dbInfo) {
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(dbInfo.getConnectionPath(), dbInfo.getDbUser(), dbInfo.getDbPassWord());
        List<String> tables = mysqlAbstractDataSourceInstance.getTables(dbInfo.getDbName());
        return UnifiedResponse.success(tables);
    }

    @RequestMapping("/generateCode")
    public void generateCode(@RequestBody DBInfo dbInfo, HttpServletResponse httpServletResponse) {
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(dbInfo.getConnectionPath(), dbInfo.getDbUser(), dbInfo.getDbPassWord());
        List<DBTableModel> dbTableModels = mysqlAbstractDataSourceInstance.getDBTableModels(dbInfo.getDbName());
        String basePath="cn\\anicert\\university\\training\\";
        List<JavaBeanModel> collect = dbTableModels.stream().map(x -> JavaBeanModel.of(x,basePath)).collect(Collectors.toList());
        String outPutPath="D:\\JAVA_WORK_SPACE\\lite_spring_template\\src\\main\\java\\";
        byte[] build = BricklayerBuilder.build(collect, outPutPath);
        httpServletResponse.reset();
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + "generate.zip" + "\"");
        httpServletResponse.setHeader("Set-Cookie", "fileDownload=true; path=/");
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");

        try {
            ServletOutputStream out = httpServletResponse.getOutputStream();
            out.write(build);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ExceptionHandler
    public UnifiedResponse exp(Exception ex) {
        return UnifiedResponse.error(ex.getMessage());
    }
}
