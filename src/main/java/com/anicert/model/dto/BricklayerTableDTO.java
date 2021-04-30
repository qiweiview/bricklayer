package com.anicert.model.dto;


import com.anicert.model.d_o.BricklayerTableDO;
import com.anicert.model.vo.BricklayerTableVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* create by view
*/
public class BricklayerTableDTO {

    // page field
    private int current;

    private int size;


    private  Integer id;

    private  String originalTableName;

    private  String remark;


    private  String sourceDataBase;

    private  String sourceDevice;

    private Integer deviceId;

    private List<BricklayerColumnDTO> bricklayerColumnDTOList =new ArrayList();

    private List<String> selectedTables =new ArrayList();

/*  ------------ data conversion ------------  */
public  BricklayerTableDO toBricklayerTableDO(){
        BricklayerTableDO bricklayerTableDO =new BricklayerTableDO();
        bricklayerTableDO.setId(getId());
        bricklayerTableDO.setOriginalTableName(getOriginalTableName());
        bricklayerTableDO.setRemark(getRemark());
        bricklayerTableDO.setSourceDataBase(getSourceDataBase());
        bricklayerTableDO.setSourceDevice(getSourceDevice());
        return bricklayerTableDO;

        }

public  BricklayerTableVO toBricklayerTableVO(){
        BricklayerTableVO bricklayerTableVO =new BricklayerTableVO();
        bricklayerTableVO.setId(getId());
        bricklayerTableVO.setOriginalTableName(getOriginalTableName());
        bricklayerTableVO.setRemark(getRemark());
        bricklayerTableVO.setSourceDataBase(getSourceDataBase());
        bricklayerTableVO.setSourceDevice(getSourceDevice());
        return bricklayerTableVO;

        }



    public static   List<BricklayerTableVO>  toBricklayerTableVOList(List<BricklayerTableDTO> bricklayerTableDTOList){
        List<BricklayerTableVO> collect= bricklayerTableDTOList.stream().map(x->x.toBricklayerTableVO()).collect(Collectors.toList());
        return collect;
    }

    public static   List<BricklayerTableDO>  toBricklayerTableDOList(List<BricklayerTableDTO> bricklayerTableDTOList){
        List<BricklayerTableDO> collect= bricklayerTableDTOList.stream().map(x->x.toBricklayerTableDO()).collect(Collectors.toList());
        return collect;
    }

/*  ------------ getter setter ------------  */

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public List<String> getSelectedTables() {
        return selectedTables;
    }

    public void setSelectedTables(List<String> selectedTables) {
        this.selectedTables = selectedTables;
    }

    public List<BricklayerColumnDTO> getBricklayerColumnDTOList() {
        return bricklayerColumnDTOList;
    }

    public void setBricklayerColumnDTOList(List<BricklayerColumnDTO> bricklayerColumnDTOList) {
        this.bricklayerColumnDTOList = bricklayerColumnDTOList;
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


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
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
        this.sourceDevice = sourceDevice;
    }
}