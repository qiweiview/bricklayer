package com.anicert.model.vo;





public class BricklayerProjectVO {
    /**
     * 前端访问前缀
     */
    private  String contextPath;
    /**
     * 创建人
     */
    private  String createBy;
    /**
     * 创建日期
     */
    private  java.sql.Timestamp createDate;
    /**
     * 基础项目无法删除
     */
    private  Boolean fixProject;
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 项目描述
     */
    private  String projectDescription;
    /**
     * 项目名
     */
    private  String projectName;
    /**
     * 修改人
     */
    private  String updateBy;
    /**
     * 修改日期
     */
    private  java.sql.Timestamp updateDate;


/*  ------------ data conversion ------------  */


/*  ------------ getter setter ------------  */

    public String getContextPath(){
        return contextPath;
        }

    public void setContextPath(String contextPath){
    this.contextPath=contextPath;
    }

    public String getCreateBy(){
        return createBy;
        }

    public void setCreateBy(String createBy){
    this.createBy=createBy;
    }

    public java.sql.Timestamp getCreateDate(){
        return createDate;
        }

    public void setCreateDate(java.sql.Timestamp createDate){
    this.createDate=createDate;
    }

    public Boolean getFixProject(){
        return fixProject;
        }

    public void setFixProject(Boolean fixProject){
    this.fixProject=fixProject;
    }

    public Integer getId(){
        return id;
        }

    public void setId(Integer id){
    this.id=id;
    }

    public String getProjectDescription(){
        return projectDescription;
        }

    public void setProjectDescription(String projectDescription){
    this.projectDescription=projectDescription;
    }

    public String getProjectName(){
        return projectName;
        }

    public void setProjectName(String projectName){
    this.projectName=projectName;
    }

    public String getUpdateBy(){
        return updateBy;
        }

    public void setUpdateBy(String updateBy){
    this.updateBy=updateBy;
    }

    public java.sql.Timestamp getUpdateDate(){
        return updateDate;
        }

    public void setUpdateDate(java.sql.Timestamp updateDate){
    this.updateDate=updateDate;
    }
}