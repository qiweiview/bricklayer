package cn.bricklayer.model.vo;





public class BricklayerDictionaryVO {
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