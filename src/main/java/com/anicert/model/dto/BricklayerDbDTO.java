package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerDbDO;
import com.anicert.model.vo.BricklayerDbVO;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* create by view
*/
public class BricklayerDbDTO {

    // page field
    private int current;

    private int size;


    private  String dbDriverClass;

    private  String dbIp;

    private  String dbPassword;

    private  Integer dbPort;

    private  String dbType;

    private  String dbUser;

    private  Integer id;


/*  ------------ data conversion ------------  */
public  BricklayerDbDO toBricklayerDbDO(){
        BricklayerDbDO bricklayerDbDO =new BricklayerDbDO();
        bricklayerDbDO.setDbDriverClass(getDbDriverClass());
        bricklayerDbDO.setDbIp(getDbIp());
        bricklayerDbDO.setDbPassword(getDbPassword());
        bricklayerDbDO.setDbPort(getDbPort());
        bricklayerDbDO.setDbType(getDbType());
        bricklayerDbDO.setDbUser(getDbUser());
        bricklayerDbDO.setId(getId());
        return bricklayerDbDO;

        }

public  BricklayerDbVO toBricklayerDbVO(){
        BricklayerDbVO bricklayerDbVO =new BricklayerDbVO();
        bricklayerDbVO.setDbDriverClass(getDbDriverClass());
        bricklayerDbVO.setDbIp(getDbIp());
        bricklayerDbVO.setDbPassword(getDbPassword());
        bricklayerDbVO.setDbPort(getDbPort());
        bricklayerDbVO.setDbType(getDbType());
        bricklayerDbVO.setDbUser(getDbUser());
        bricklayerDbVO.setId(getId());
        return bricklayerDbVO;

        }



    public static   List<BricklayerDbVO>  toBricklayerDbVOList(List<BricklayerDbDTO> bricklayerDbDTOList){
        List<BricklayerDbVO> collect= bricklayerDbDTOList.stream().map(x->x.toBricklayerDbVO()).collect(Collectors.toList());
        return collect;
    }

    public static   List<BricklayerDbDO>  toBricklayerDbDOList(List<BricklayerDbDTO> bricklayerDbDTOList){
        List<BricklayerDbDO> collect= bricklayerDbDTOList.stream().map(x->x.toBricklayerDbDO()).collect(Collectors.toList());
        return collect;
    }

/*  ------------ getter setter ------------  */
    public int getCurrent(){
        return current;
    }

    public void setCurrent(int current){
        this.current=current;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }


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

    public String getUniqueKey() {
        return getDbDriverClass()+getDbIp()+":"+getDbPort();
    }
}