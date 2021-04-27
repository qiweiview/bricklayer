package com.anicert.model.vo;





public class BricklayerDictionaryVO {
    /**
     * 
     */
    private  Integer id;
    /**
     * 字典名称
     */
    private  String dictionaryName;
    /**
     * 字典值
     */
    private  String dictionaryValue;
    /**
     * 字典编码
     */
    private  String dictionaryCode;
    /**
     * 备注
     */
    private  String remark;


/*  ------------ data conversion ------------  */


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