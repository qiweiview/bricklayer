package web_support.model;

import lombok.Data;

@Data
public class DBInfo {


    private String dbType;

    private String dbName="mysql";

    private String dbHost;

    private String dbPort;

    private String dbUser;

    private String dbPassWord;


    public String getConnectionPath(){
        return "jdbc:mysql://localhost/"+dbName+"?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
    }
}
