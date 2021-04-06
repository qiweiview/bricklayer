package web_support.controller;

import build.db_adapter.MysqlAbstractDataSourceInstance;
import build.db_model.DBTableModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web_support.model.DBInfo;

import java.util.List;

@RestController
public class Test {

    public Test() {
        System.out.println("controller");
    }

    @RequestMapping("/createConnection")
    public Object createConnection(@RequestBody DBInfo dbInfo){
        MysqlAbstractDataSourceInstance mysqlAbstractDataSourceInstance = new MysqlAbstractDataSourceInstance(dbInfo.getConnectionPath(), dbInfo.getDbUser(), dbInfo.getDbPassWord());
        List<String> tables = mysqlAbstractDataSourceInstance.getTables();

        return tables;
    }


    @ExceptionHandler
    public String exp( Exception ex) {
        ex.printStackTrace();
       return "err";
    }
}
