package cn.anicert.model.vo;





public class BricklayerDbVO {
    /**
     * 数据库驱动
     */
    private  String dbDriverClass;
    /**
     * 数据库ip
     */
    private  String dbIp;
    /**
     * 密码
     */
    private  String dbPassword;
    /**
     * 数据库端口
     */
    private  Integer dbPort;
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


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public String getDbDriverClass(){
        return dbDriverClass;
        }

    public void setDbDriverClass(String dbDriverClass){
    this.dbDriverClass=dbDriverClass;
    }

    public String getDbIp(){
        return dbIp;
        }

    public void setDbIp(String dbIp){
    this.dbIp=dbIp;
    }

    public String getDbPassword(){
        return dbPassword;
        }

    public void setDbPassword(String dbPassword){
    this.dbPassword=dbPassword;
    }

    public Integer getDbPort(){
        return dbPort;
        }

    public void setDbPort(Integer dbPort){
    this.dbPort=dbPort;
    }

    public String getDbType(){
        return dbType;
        }

    public void setDbType(String dbType){
    this.dbType=dbType;
    }

    public String getDbUser(){
        return dbUser;
        }

    public void setDbUser(String dbUser){
    this.dbUser=dbUser;
    }

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }
}