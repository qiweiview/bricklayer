package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerColumnDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_column")
public class BricklayerColumnDO {

    /**
     * 所属模型主键
     */
    @TableField(value = "belong_table_id")
    private  Integer belongTableId;

    /**
     * 列主键标识
     */
    @TableField(value = "column_key")
    private  String columnKey;

    /**
     * 列类型（完整）
     */
    @TableField(value = "column_type")
    private  String columnType;

    /**
     * 列注解
     */
    @TableField(value = "comment")
    private  String comment;

    /**
     * 自增标识
     */
    @TableField(value = "extra")
    private  String extra;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 数据库列名
     */
    @TableField(value = "original_column_name")
    private  String originalColumnName;

    /**
     * 列类型（简写）
     */
    @TableField(value = "simple_column_type")
    private  String simpleColumnType;


    @TableField(exist = false)
    private String belongTableName;

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
    bricklayerColumnDTO.setBelongTableId(getBelongTableId());
    bricklayerColumnDTO.setColumnKey(getColumnKey());
    bricklayerColumnDTO.setColumnType(getColumnType());
    bricklayerColumnDTO.setComment(getComment());
    bricklayerColumnDTO.setExtra(getExtra());
    bricklayerColumnDTO.setId(getId());
    bricklayerColumnDTO.setOriginalColumnName(getOriginalColumnName());
    bricklayerColumnDTO.setSimpleColumnType(getSimpleColumnType());
        return bricklayerColumnDTO;

}


    public static   List<BricklayerColumnDTO>  toBricklayerColumnDTOList(List<BricklayerColumnDO> bricklayerColumnDOList){
        List<BricklayerColumnDTO> collect = bricklayerColumnDOList.stream().map(x -> x.toBricklayerColumnDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public String getBelongTableName() {
        return belongTableName;
    }

    public void setBelongTableName(String belongTableName) {
        this.belongTableName = belongTableName;
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