package cn.bricklayer.model.d_o;

import cn.bricklayer.model.dto.BricklayerDictionaryDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_dictionary")
public class BricklayerDictionaryDO {

    /**
     * 字典编码
     */
    @TableField(value = "dictionary_code")
    private  String dictionaryCode;

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
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

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
    public BricklayerDictionaryDTO toBricklayerDictionaryDTO() {
        BricklayerDictionaryDTO bricklayerDictionaryDTO = new BricklayerDictionaryDTO();
        bricklayerDictionaryDTO.setDictionaryCode(getDictionaryCode());
        bricklayerDictionaryDTO.setDictionaryName(getDictionaryName());
        bricklayerDictionaryDTO.setDictionaryValue(getDictionaryValue());
        bricklayerDictionaryDTO.setId(getId());
        bricklayerDictionaryDTO.setRemark(getRemark());
        return bricklayerDictionaryDTO;

    }


    public static   List<BricklayerDictionaryDTO>  toBricklayerDictionaryDTOList(List<BricklayerDictionaryDO> bricklayerDictionaryDOList){
        List<BricklayerDictionaryDTO> collect = bricklayerDictionaryDOList.stream().map(x -> x.toBricklayerDictionaryDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public String getDictionaryCode(){
    return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode){
    this.dictionaryCode=dictionaryCode;
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

    public Integer getId(){
    return id;
    }

    public void setId(Integer id){
    this.id=id;
    }

    public String getRemark(){
    return remark;
    }

    public void setRemark(String remark){
    this.remark=remark;
    }
}