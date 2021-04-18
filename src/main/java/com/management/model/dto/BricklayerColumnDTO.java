package com.management.model.dto;

import com.management.model.d_o.BricklayerColumnDO;
import com.management.model.vo.BricklayerColumnVO;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* create by view
*/
public class BricklayerColumnDTO {

    // page field
    private int current;

    private int size;


    private  Integer id;

    private  String originalColumnName;

    private  String simpleColumnType;

    private  String columnType;

    private  String columnKey;

    private  String extra;

    private  String comment;

    private  Integer tableId;


/*  ------------ data conversion ------------  */
public  BricklayerColumnDO toBricklayerColumnDO(){
        BricklayerColumnDO bricklayerColumnDO =new BricklayerColumnDO();
        bricklayerColumnDO.setId(getId());
        bricklayerColumnDO.setOriginalColumnName(getOriginalColumnName());
        bricklayerColumnDO.setSimpleColumnType(getSimpleColumnType());
        bricklayerColumnDO.setColumnType(getColumnType());
        bricklayerColumnDO.setColumnKey(getColumnKey());
        bricklayerColumnDO.setExtra(getExtra());
        bricklayerColumnDO.setComment(getComment());
        bricklayerColumnDO.setTableId(getTableId());
        return bricklayerColumnDO;

        }

public  BricklayerColumnVO toBricklayerColumnVO(){
        BricklayerColumnVO bricklayerColumnVO =new BricklayerColumnVO();
        bricklayerColumnVO.setId(getId());
        bricklayerColumnVO.setOriginalColumnName(getOriginalColumnName());
        bricklayerColumnVO.setSimpleColumnType(getSimpleColumnType());
        bricklayerColumnVO.setColumnType(getColumnType());
        bricklayerColumnVO.setColumnKey(getColumnKey());
        bricklayerColumnVO.setExtra(getExtra());
        bricklayerColumnVO.setComment(getComment());
        bricklayerColumnVO.setTableId(getTableId());
        return bricklayerColumnVO;

        }



    public static   List<BricklayerColumnVO>  toBricklayerColumnVOList(List<BricklayerColumnDTO> bricklayerColumnDTOList){
        List<BricklayerColumnVO> collect= bricklayerColumnDTOList.stream().map(x->x.toBricklayerColumnVO()).collect(Collectors.toList());
        return collect;
    }

    public static   List<BricklayerColumnDO>  toBricklayerColumnDOList(List<BricklayerColumnDTO> bricklayerColumnDTOList){
        List<BricklayerColumnDO> collect= bricklayerColumnDTOList.stream().map(x->x.toBricklayerColumnDO()).collect(Collectors.toList());
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


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getOriginalColumnName(){
        return originalColumnName;
    }

    public void setOriginalColumnName(String originalColumnName){
        this.originalColumnName=originalColumnName;
    }

    public String getSimpleColumnType(){
        return simpleColumnType;
    }

    public void setSimpleColumnType(String simpleColumnType){
        this.simpleColumnType=simpleColumnType;
    }

    public String getColumnType(){
        return columnType;
    }

    public void setColumnType(String columnType){
        this.columnType=columnType;
    }

    public String getColumnKey(){
        return columnKey;
    }

    public void setColumnKey(String columnKey){
        this.columnKey=columnKey;
    }

    public String getExtra(){
        return extra;
    }

    public void setExtra(String extra){
        this.extra=extra;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment=comment;
    }

    public Integer getTableId(){
        return tableId;
    }

    public void setTableId(Integer tableId){
        this.tableId=tableId;
    }
}