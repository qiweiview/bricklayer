package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerDbDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_db")
public class BricklayerDbDO {

    /**
     * 数据库驱动
     */
    @TableField(value = "db_driver_class")
    private  String dbDriverClass;

    /**
     * 数据库ip
     */
    @TableField(value = "db_ip")
    private  String dbIp;

    /**
     * 密码
     */
    @TableField(value = "db_password")
    private  String dbPassword;

    /**
     * 数据库端口
     */
    @TableField(value = "db_port")
    private  Integer dbPort;

    /**
     * 数据库类型
     */
    @TableField(value = "db_type")
    private  String dbType;

    /**
     * 用户名
     */
    @TableField(value = "db_user")
    private  String dbUser;

    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

/*  ------------ init value ------------  */
    public void doInit() {
        //setDelFlag(false);
        //setCreateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doUpdate() {
        //setUpdateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doDelete() {
        //setDelFlag(true);
        //doUpdate();
        }


/*  ------------ data conversion by model ------------  */
    public  BricklayerDbDTO toBricklayerDbDTO(){
    BricklayerDbDTO bricklayerDbDTO =new BricklayerDbDTO();
    bricklayerDbDTO.setDbDriverClass(getDbDriverClass());
    bricklayerDbDTO.setDbIp(getDbIp());
    bricklayerDbDTO.setDbPassword(getDbPassword());
    bricklayerDbDTO.setDbPort(getDbPort());
    bricklayerDbDTO.setDbType(getDbType());
    bricklayerDbDTO.setDbUser(getDbUser());
    bricklayerDbDTO.setId(getId());
        return bricklayerDbDTO;

}


    public static   List<BricklayerDbDTO>  toBricklayerDbDTOList(List<BricklayerDbDO> bricklayerDbDOList){
        List<BricklayerDbDTO> collect = bricklayerDbDOList.stream().map(x -> x.toBricklayerDbDTO()).collect(Collectors.toList());
        return collect;
}

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