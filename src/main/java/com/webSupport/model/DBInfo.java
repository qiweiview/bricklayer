package com.webSupport.model;

import lombok.Data;

import java.util.List;

@Data
public class DBInfo {


    private String dbType;

    private String dbName="mysql";

    private String dbHost;

    private String dbPort;

    private String dbUser;

    private String dbPassWord;

    private List<String> selectedTables;


    public String getConnectionPath(){
        setDbName("".equals(getDbName()) ? "mysql" : getDbName());
        return "jdbc:mysql://"+getDbHost()+":"+getDbPort()+"/"+getDbName()+"?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
    }
}
