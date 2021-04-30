package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerTableDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;

/**
*
* create by view
*/
@TableName("bricklayer_table")
public class BricklayerTableDO {

    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 来源表名
     */
    @TableField(value = "original_table_name")
    private  String originalTableName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private  String remark;

    /**
     * 来源数据库名
     */
    @TableField(value = "source_data_base")
    private  String sourceDataBase;

    /**
     * 来源设备
     */
    @TableField(value = "source_device")
    private  String sourceDevice;

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
    public  BricklayerTableDTO toBricklayerTableDTO(){
    BricklayerTableDTO bricklayerTableDTO =new BricklayerTableDTO();
    bricklayerTableDTO.setId(getId());
    bricklayerTableDTO.setOriginalTableName(getOriginalTableName());
    bricklayerTableDTO.setRemark(getRemark());
    bricklayerTableDTO.setSourceDataBase(getSourceDataBase());
    bricklayerTableDTO.setSourceDevice(getSourceDevice());
        return bricklayerTableDTO;

}


    public static   List<BricklayerTableDTO>  toBricklayerTableDTOList(List<BricklayerTableDO> bricklayerTableDOList){
        List<BricklayerTableDTO> collect = bricklayerTableDOList.stream().map(x -> x.toBricklayerTableDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

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