package cn.anicert.model.vo;





public class BricklayerColumnVO {
    /**
     * 所属模型主键
     */
    private  Integer belongTableId;
    /**
     * 列主键标识
     */
    private  String columnKey;
    /**
     * 列类型（完整）
     */
    private  String columnType;
    /**
     * 列注解
     */
    private  String comment;
    /**
     * 自增标识
     */
    private  String extra;
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 数据库列名
     */
    private  String originalColumnName;
    /**
     * 列类型（简写）
     */
    private  String simpleColumnType;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

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