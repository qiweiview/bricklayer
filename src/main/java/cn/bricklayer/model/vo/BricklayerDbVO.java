package cn.bricklayer.model.vo;


import lombok.Data;

@Data
public class BricklayerDbVO {
    /**
     * 数据库驱动
     */
    private String dbDriverClass;
    /**
     * 数据库ip
     */
    private String dbIp;

    /**
     * 数据库ip
     */
    private String serviceName;
    /**
     * 密码
     */
    private String dbPassword;
    /**
     * 数据库端口
     */
    private Integer dbPort;
    /**
     * 数据库类型
     */
    private  String dbType;
    /**
     * 用户名
     */
    private  String dbUser;
    /**
     * 
     */
    private  Integer id;


}