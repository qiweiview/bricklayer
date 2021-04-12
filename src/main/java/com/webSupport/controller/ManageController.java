package com.webSupport.controller;

import com.buildSupport.build_task.BricklayerBuilder;
import com.buildSupport.db_adapter.MysqlAbstractDataSourceInstance;
import com.buildSupport.db_model.DBTableModel;
import com.buildSupport.java_bean.JavaBeanModel;
import org.springframework.web.bind.annotation.*;
import com.webSupport.model.DBInfo;
import com.webSupport.utils.UnifiedResponse;

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
        dbInfo.getSelectedTables().forEach(x->{
            mysqlAbstractDataSourceInstance.addTargetTableName(x);
        });
        List<DBTableModel> dbTableModels = mysqlAbstractDataSourceInstance.getDBTableModels(dbInfo.getDbName());
        String basePath= "com/management";
        List<JavaBeanModel> collect = dbTableModels.stream().map(x -> JavaBeanModel.of(x,basePath)).collect(Collectors.toList());
        byte[] build = BricklayerBuilder.build(collect);
//        httpServletResponse.reset();
//        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + "generate.zip" + "\"");
//        httpServletResponse.setHeader("Set-Cookie", "fileDownload=true; path=/");
        httpServletResponse.setContentType("charset=utf-8");

        try {
            ServletOutputStream out = httpServletResponse.getOutputStream();
            out.write(build);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ExceptionHandler
    public UnifiedResponse exp(Exception ex,HttpServletResponse httpServletResponse) {
        ex.printStackTrace();
        httpServletResponse.setStatus(500);
        return UnifiedResponse.error(ex.getMessage());
    }
}
