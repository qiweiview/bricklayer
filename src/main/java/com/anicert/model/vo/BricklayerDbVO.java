package com.anicert.model.vo;



/**
*
* create by view
*/
public class BricklayerDbVO {

    private  String dbDriverClass;

    private  String dbIp;

    private  String dbPassword;

    private  Integer dbPort;

    private  String dbType;

    private  String dbUser;

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