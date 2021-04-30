package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerTableDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_table")
public class BricklayerTableDO {

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private  String createBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private  Timestamp createDate;

    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 模型服务对象
     */
    @TableField(value = "model_service_target")
    private  String modelServiceTarget;

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

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private  String updateBy;

    /**
     * 修改日期
     */
    @TableField(value = "update_date")
    private  Timestamp updateDate;

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
    bricklayerTableDTO.setCreateBy(getCreateBy());
    bricklayerTableDTO.setCreateDate(getCreateDate());
    bricklayerTableDTO.setId(getId());
    bricklayerTableDTO.setModelServiceTarget(getModelServiceTarget());
    bricklayerTableDTO.setOriginalTableName(getOriginalTableName());
    bricklayerTableDTO.setRemark(getRemark());
    bricklayerTableDTO.setSourceDataBase(getSourceDataBase());
    bricklayerTableDTO.setSourceDevice(getSourceDevice());
    bricklayerTableDTO.setUpdateBy(getUpdateBy());
    bricklayerTableDTO.setUpdateDate(getUpdateDate());
        return bricklayerTableDTO;

}


    public static   List<BricklayerTableDTO>  toBricklayerTableDTOList(List<BricklayerTableDO> bricklayerTableDOList){
        List<BricklayerTableDTO> collect = bricklayerTableDOList.stream().map(x -> x.toBricklayerTableDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public String getCreateBy(){
    return createBy;
    }

    public void setCreateBy(String createBy){
    this.createBy=createBy;
    }

    public Timestamp getCreateDate(){
    return createDate;
    }

    public void setCreateDate(Timestamp createDate){
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

    public Timestamp getUpdateDate(){
    return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate){
    this.updateDate=updateDate;
    }
}