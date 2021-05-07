package cn.anicert.model.vo;





public class BricklayerDirectVO {
    /**
     * 所属项目主键
     */
    private  Integer belongProjectId;
    /**
     * 目录全路径
     */
    private  String directFullPath;
    /**
     * 文件夹名称
     */
    private  String directName;
    /**
     * 
     */
    private  Integer id;
    /**
     * 父级文件夹主键
     */
    private  Integer parentDirectId;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public Integer getBelongProjectId(){
        return belongProjectId;
        }

    public void setBelongProjectId(Integer belongProjectId){
    this.belongProjectId=belongProjectId;
    }

    public String getDirectFullPath(){
        return directFullPath;
        }

    public void setDirectFullPath(String directFullPath){
    this.directFullPath=directFullPath;
    }

    public String getDirectName(){
        return directName;
        }

    public void setDirectName(String directName){
    this.directName=directName;
    }

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }

    public Integer getParentDirectId(){
        return parentDirectId;
        }

    public void setParentDirectId(Integer parentDirectId){
    this.parentDirectId=parentDirectId;
    }
}