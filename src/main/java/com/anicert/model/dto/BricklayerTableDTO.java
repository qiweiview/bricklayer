package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerTableDO;
import com.anicert.model.vo.BricklayerTableVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BricklayerTableDTO {

    // page field
    private int current;

    private int size;

    /**
     * 创建人
     */
    private  String createBy;
    /**
     * 创建日期
     */
    private  java.sql.Timestamp createDate;
    /**
     * 
     */
    private  Integer id;
    /**
     * 模型服务对象
     */
    private  String modelServiceTarget;
    /**
     * 来源表名
     */
    private  String originalTableName;
    /**
     * 备注
     */
    private  String remark;
    /**
     * 来源数据库名
     */
    private  String sourceDataBase;
    /**
     * 来源设备
     */
    private  String sourceDevice;
    /**
     * 修改人
     */
    private  String updateBy;
    /**
     * 修改日期
     */
    private  java.sql.Timestamp updateDate;

    private Integer deviceId;

    private List<BricklayerColumnDTO> bricklayerColumnDTOList =new ArrayList();

    private List<String> selectedTables =new ArrayList();

/*  ------------ data conversion ------------  */
public  BricklayerTableDO toBricklayerTableDO(){
        BricklayerTableDO bricklayerTableDO =new BricklayerTableDO();
        bricklayerTableDO.setCreateBy(getCreateBy());
        bricklayerTableDO.setCreateDate(getCreateDate());
        bricklayerTableDO.setId(getId());
        bricklayerTableDO.setModelServiceTarget(getModelServiceTarget());
        bricklayerTableDO.setOriginalTableName(getOriginalTableName());
        bricklayerTableDO.setRemark(getRemark());
        bricklayerTableDO.setSourceDataBase(getSourceDataBase());
        bricklayerTableDO.setSourceDevice(getSourceDevice());
        bricklayerTableDO.setUpdateBy(getUpdateBy());
        bricklayerTableDO.setUpdateDate(getUpdateDate());
        return bricklayerTableDO;

        }

public  BricklayerTableVO toBricklayerTableVO(){
        BricklayerTableVO bricklayerTableVO =new BricklayerTableVO();
        bricklayerTableVO.setCreateBy(getCreateBy());
        bricklayerTableVO.setCreateDate(getCreateDate());
        bricklayerTableVO.setId(getId());
        bricklayerTableVO.setModelServiceTarget(getModelServiceTarget());
        bricklayerTableVO.setOriginalTableName(getOriginalTableName());
        bricklayerTableVO.setRemark(getRemark());
        bricklayerTableVO.setSourceDataBase(getSourceDataBase());
        bricklayerTableVO.setSourceDevice(getSourceDevice());
        bricklayerTableVO.setUpdateBy(getUpdateBy());
        bricklayerTableVO.setUpdateDate(getUpdateDate());
        return bricklayerTableVO;

        }



    public static   List<BricklayerTableVO>  toBricklayerTableVOList(List<BricklayerTableDTO> bricklayerTableDTOList){
        List<BricklayerTableVO> collect= bricklayerTableDTOList.stream().map(x->x.toBricklayerTableVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerTableDO>  toBricklayerTableDOList(List<BricklayerTableDTO> bricklayerTableDtOList){
        List<BricklayerTableDO> collect= bricklayerTableDtOList.stream().map(x->x.toBricklayerTableDO()).collect(Collectors.toList());
        return collect;
    }

/*  ------------ getter setter ------------  */

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public List<BricklayerColumnDTO> getBricklayerColumnDTOList() {
        return bricklayerColumnDTOList;
    }

    public void setBricklayerColumnDTOList(List<BricklayerColumnDTO> bricklayerColumnDTOList) {
        this.bricklayerColumnDTOList = bricklayerColumnDTOList;
    }

    public List<String> getSelectedTables() {
        return selectedTables;
    }

    public void setSelectedTables(List<String> selectedTables) {
        this.selectedTables = selectedTables;
    }

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


    public String getCreateBy(){
        return createBy;
    }

    public void setCreateBy(String createBy){
        this.createBy=createBy;
    }

    public java.sql.Timestamp getCreateDate(){
        return createDate;
    }

    public void setCreateDate(java.sql.Timestamp createDate){
        this.createDate=createDate;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getModelServiceTarget(){
        return modelServiceTarget;
    }

    public void setModelServiceTarget(String modelServiceTarget){
        this.modelServiceTarget=modelServiceTarget;
    }

    public String getOriginalTableName(){
        return originalTableName;
    }

    public void setOriginalTableName(String originalTableName){
        this.originalTableName=originalTableName;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark=remark;
    }

    public String getSourceDataBase(){
        return sourceDataBase;
    }

    public void setSourceDataBase(String sourceDataBase){
        this.sourceDataBase=sourceDataBase;
    }

    public String getSourceDevice(){
        return sourceDevice;
    }

    public void setSourceDevice(String sourceDevice){
        this.sourceDevice=sourceDevice;
    }

    public String getUpdateBy(){
        return updateBy;
    }

    public void setUpdateBy(String updateBy){
        this.updateBy=updateBy;
    }

    public java.sql.Timestamp getUpdateDate(){
        return updateDate;
    }

    public void setUpdateDate(java.sql.Timestamp updateDate){
        this.updateDate=updateDate;
    }
}