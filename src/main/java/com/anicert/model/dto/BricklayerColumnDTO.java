package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerColumnDO;
import com.anicert.model.vo.BricklayerColumnVO;
import java.util.List;
import java.util.stream.Collectors;


public class BricklayerColumnDTO {

    // page field
    private int current;

    private int size;

    /**
     * 所属模型主键
     */
    private  Integer belongTableId;
    /**
     * 列主键标识
     */
    private  String columnKey;
    /**
     * 列类型（完整）
     */
    private  String columnType;
    /**
     * 列注解
     */
    private  String comment;
    /**
     * 自增标识
     */
    private  String extra;
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 数据库列名
     */
    private  String originalColumnName;
    /**
     * 列类型（简写）
     */
    private  String simpleColumnType;


/*  ------------ data conversion ------------  */
public  BricklayerColumnDO toBricklayerColumnDO(){
        BricklayerColumnDO bricklayerColumnDO =new BricklayerColumnDO();
        bricklayerColumnDO.setBelongTableId(getBelongTableId());
        bricklayerColumnDO.setColumnKey(getColumnKey());
        bricklayerColumnDO.setColumnType(getColumnType());
        bricklayerColumnDO.setComment(getComment());
        bricklayerColumnDO.setExtra(getExtra());
        bricklayerColumnDO.setId(getId());
        bricklayerColumnDO.setOriginalColumnName(getOriginalColumnName());
        bricklayerColumnDO.setSimpleColumnType(getSimpleColumnType());
        return bricklayerColumnDO;

        }

public  BricklayerColumnVO toBricklayerColumnVO(){
        BricklayerColumnVO bricklayerColumnVO =new BricklayerColumnVO();
        bricklayerColumnVO.setBelongTableId(getBelongTableId());
        bricklayerColumnVO.setColumnKey(getColumnKey());
        bricklayerColumnVO.setColumnType(getColumnType());
        bricklayerColumnVO.setComment(getComment());
        bricklayerColumnVO.setExtra(getExtra());
        bricklayerColumnVO.setId(getId());
        bricklayerColumnVO.setOriginalColumnName(getOriginalColumnName());
        bricklayerColumnVO.setSimpleColumnType(getSimpleColumnType());
        return bricklayerColumnVO;

        }



    public static   List<BricklayerColumnVO>  toBricklayerColumnVOList(List<BricklayerColumnDTO> bricklayerColumnDTOList){
        List<BricklayerColumnVO> collect= bricklayerColumnDTOList.stream().map(x->x.toBricklayerColumnVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerColumnDO>  toBricklayerColumnDOList(List<BricklayerColumnDTO> bricklayerColumnDtOList){
        List<BricklayerColumnDO> collect= bricklayerColumnDtOList.stream().map(x->x.toBricklayerColumnDO()).collect(Collectors.toList());
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


    public Integer getBelongTableId(){
        return belongTableId;
    }

    public void setBelongTableId(Integer belongTableId){
        this.belongTableId=belongTableId;
    }

    public String getColumnKey(){
        return columnKey;
    }

    public void setColumnKey(String columnKey){
        this.columnKey=columnKey;
    }

    public String getColumnType(){
        return columnType;
    }

    public void setColumnType(String columnType){
        this.columnType=columnType;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment=comment;
    }

    public String getExtra(){
        return extra;
    }

    public void setExtra(String extra){
        this.extra=extra;
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
}