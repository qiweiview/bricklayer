package com.management.model.d_o;

import com.management.model.dto.BricklayerColumnDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
*
* create by view
*/
@TableName("bricklayer_column")
public class BricklayerColumnDO {

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 原字段名称
     */
    @TableField(value = "original_column_name")
    private  String originalColumnName;

    /**
     * 字段类型
     */
    @TableField(value = "simple_column_type")
    private  String simpleColumnType;

    /**
     * 字段类型(含长度)
     */
    @TableField(value = "column_type")
    private  String columnType;

    /**
     * 主键
     */
    @TableField(value = "column_key")
    private  String columnKey;

    /**
     * 自增标识
     */
    @TableField(value = "extra")
    private  String extra;

    /**
     * 备注
     */
    @TableField(value = "comment")
    private  String comment;

    /**
     * 所属吧表格
     */
    @TableField(value = "table_id")
    private  Integer tableId;

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
    public  BricklayerColumnDTO toBricklayerColumnDTO(){
    BricklayerColumnDTO bricklayerColumnDTO =new BricklayerColumnDTO();
    bricklayerColumnDTO.setId(getId());
    bricklayerColumnDTO.setOriginalColumnName(getOriginalColumnName());
    bricklayerColumnDTO.setSimpleColumnType(getSimpleColumnType());
    bricklayerColumnDTO.setColumnType(getColumnType());
    bricklayerColumnDTO.setColumnKey(getColumnKey());
    bricklayerColumnDTO.setExtra(getExtra());
    bricklayerColumnDTO.setComment(getComment());
    bricklayerColumnDTO.setTableId(getTableId());
        return bricklayerColumnDTO;

}


    public static   List<BricklayerColumnDTO>  toBricklayerColumnDTOList(List<BricklayerColumnDO> bricklayerColumnDOList){
        List<BricklayerColumnDTO> collect = bricklayerColumnDOList.stream().map(x -> x.toBricklayerColumnDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

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