package com.anicert.model.d_o;

import com.anicert.model.dto.BricklayerDictionaryDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_dictionary")
public class BricklayerDictionaryDO {

    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 字典名称
     */
    @TableField(value = "dictionary_name")
    private  String dictionaryName;

    /**
     * 字典值
     */
    @TableField(value = "dictionary_value")
    private  String dictionaryValue;

    /**
     * 字典编码
     */
    @TableField(value = "dictionary_code")
    private  String dictionaryCode;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private  String remark;

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
    public  BricklayerDictionaryDTO toBricklayerDictionaryDTO(){
    BricklayerDictionaryDTO bricklayerDictionaryDTO =new BricklayerDictionaryDTO();
    bricklayerDictionaryDTO.setId(getId());
    bricklayerDictionaryDTO.setDictionaryName(getDictionaryName());
    bricklayerDictionaryDTO.setDictionaryValue(getDictionaryValue());
    bricklayerDictionaryDTO.setDictionaryCode(getDictionaryCode());
    bricklayerDictionaryDTO.setRemark(getRemark());
        return bricklayerDictionaryDTO;

}


    public static   List<BricklayerDictionaryDTO>  toBricklayerDictionaryDTOList(List<BricklayerDictionaryDO> bricklayerDictionaryDOList){
        List<BricklayerDictionaryDTO> collect = bricklayerDictionaryDOList.stream().map(x -> x.toBricklayerDictionaryDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public Integer getId(){
    return id;
    }

    public void setId(Integer id){
    this.id=id;
    }

    public String getDictionaryName(){
    return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName){
    this.dictionaryName=dictionaryName;
    }

    public String getDictionaryValue(){
    return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue){
    this.dictionaryValue=dictionaryValue;
    }

    public String getDictionaryCode(){
    return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode){
    this.dictionaryCode=dictionaryCode;
    }

    public String getRemark(){
    return remark;
    }

    public void setRemark(String remark){
    this.remark=remark;
    }
}