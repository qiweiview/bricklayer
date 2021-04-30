package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerDictionaryDO;
import com.anicert.model.vo.BricklayerDictionaryVO;
import java.util.List;
import java.util.stream.Collectors;


public class BricklayerDictionaryDTO {

    // page field
    private int current;

    private int size;

    /**
     * 字典编码
     */
    private  String dictionaryCode;
    /**
     * 字典名称
     */
    private  String dictionaryName;
    /**
     * 字典值
     */
    private  String dictionaryValue;
    /**
     * 
     */
    private  Integer id;
    /**
     * 备注
     */
    private  String remark;


/*  ------------ data conversion ------------  */
public  BricklayerDictionaryDO toBricklayerDictionaryDO(){
        BricklayerDictionaryDO bricklayerDictionaryDO =new BricklayerDictionaryDO();
        bricklayerDictionaryDO.setDictionaryCode(getDictionaryCode());
        bricklayerDictionaryDO.setDictionaryName(getDictionaryName());
        bricklayerDictionaryDO.setDictionaryValue(getDictionaryValue());
        bricklayerDictionaryDO.setId(getId());
        bricklayerDictionaryDO.setRemark(getRemark());
        return bricklayerDictionaryDO;

        }

public  BricklayerDictionaryVO toBricklayerDictionaryVO(){
        BricklayerDictionaryVO bricklayerDictionaryVO =new BricklayerDictionaryVO();
        bricklayerDictionaryVO.setDictionaryCode(getDictionaryCode());
        bricklayerDictionaryVO.setDictionaryName(getDictionaryName());
        bricklayerDictionaryVO.setDictionaryValue(getDictionaryValue());
        bricklayerDictionaryVO.setId(getId());
        bricklayerDictionaryVO.setRemark(getRemark());
        return bricklayerDictionaryVO;

        }



    public static   List<BricklayerDictionaryVO>  toBricklayerDictionaryVOList(List<BricklayerDictionaryDTO> bricklayerDictionaryDTOList){
        List<BricklayerDictionaryVO> collect= bricklayerDictionaryDTOList.stream().map(x->x.toBricklayerDictionaryVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerDictionaryDO>  toBricklayerDictionaryDOList(List<BricklayerDictionaryDTO> bricklayerDictionaryDtOList){
        List<BricklayerDictionaryDO> collect= bricklayerDictionaryDtOList.stream().map(x->x.toBricklayerDictionaryDO()).collect(Collectors.toList());
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