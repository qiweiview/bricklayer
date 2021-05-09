package cn.anicert.model.d_o;

import cn.anicert.model.dto.BricklayerColumnDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_column")
@Data
public class BricklayerColumnDO {

    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 原始列名
     */
    @TableField(value = "original_column_name")
    private String originalColumnName;

    /**
     * 简单列类型
     */
    @TableField(value = "simple_column_type")
    private String simpleColumnType;

    /**
     * 列类型（完整）
     */
    @TableField(value = "column_type")
    private String columnType;

    /**
     * 是否主键
     */
    @TableField(value = "column_key")
    private String columnKey;

    /**
     * 是否自增
     */
    @TableField(value = "extra")
    private String extra;

    /**
     * 列注释
     */
    @TableField(value = "comment")
    private String comment;

    /**
     * 所属模型
     */
    @TableField(value = "belong_table_id")
    private Integer belongTableId;

    /**
     * 列排序
     */
    @TableField(value = "column_order")
    private Integer columnOrder;

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
public BricklayerColumnDTO toBricklayerColumnDTO() {
    BricklayerColumnDTO bricklayerColumnDTO = new BricklayerColumnDTO();
    bricklayerColumnDTO.setId(getId());
    bricklayerColumnDTO.setOriginalColumnName(getOriginalColumnName());
    bricklayerColumnDTO.setSimpleColumnType(getSimpleColumnType());
    bricklayerColumnDTO.setColumnType(getColumnType());
    bricklayerColumnDTO.setColumnKey(getColumnKey());
    bricklayerColumnDTO.setExtra(getExtra());
    bricklayerColumnDTO.setComment(getComment());
    bricklayerColumnDTO.setBelongTableId(getBelongTableId());
    bricklayerColumnDTO.setColumnOrder(getColumnOrder());
    return bricklayerColumnDTO;

}


    public static   List<BricklayerColumnDTO>  toBricklayerColumnDTOList(List<BricklayerColumnDO> bricklayerColumnDOList){
        List<BricklayerColumnDTO> collect = bricklayerColumnDOList.stream().map(x -> x.toBricklayerColumnDTO()).collect(Collectors.toList());
        return collect;
}

}