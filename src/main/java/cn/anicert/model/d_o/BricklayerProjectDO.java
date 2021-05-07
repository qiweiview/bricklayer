package cn.anicert.model.d_o;

import cn.anicert.model.dto.BricklayerProjectDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_project")
public class BricklayerProjectDO {

    /**
     * 前端访问前缀
     */
    @TableField(value = "context_path")
    private  String contextPath;

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
     * 基础项目无法删除
     */
    @TableField(value = "fix_project")
    private  Boolean fixProject;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 项目描述
     */
    @TableField(value = "project_description")
    private  String projectDescription;

    /**
     * 项目名
     */
    @TableField(value = "project_name")
    private  String projectName;

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
    public BricklayerProjectDTO toBricklayerProjectDTO() {
        BricklayerProjectDTO bricklayerProjectDTO = new BricklayerProjectDTO();
        bricklayerProjectDTO.setContextPath(getContextPath());
        bricklayerProjectDTO.setCreateBy(getCreateBy());
        bricklayerProjectDTO.setCreateDate(getCreateDate());
        bricklayerProjectDTO.setFixProject(getFixProject());
        bricklayerProjectDTO.setId(getId());
        bricklayerProjectDTO.setProjectDescription(getProjectDescription());
        bricklayerProjectDTO.setProjectName(getProjectName());
        bricklayerProjectDTO.setUpdateBy(getUpdateBy());
        bricklayerProjectDTO.setUpdateDate(getUpdateDate());
        return bricklayerProjectDTO;

}


    public static   List<BricklayerProjectDTO>  toBricklayerProjectDTOList(List<BricklayerProjectDO> bricklayerProjectDOList){
        List<BricklayerProjectDTO> collect = bricklayerProjectDOList.stream().map(x -> x.toBricklayerProjectDTO()).collect(Collectors.toList());
        return collect;
}

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

    public Timestamp getCreateDate(){
    return createDate;
    }

    public void setCreateDate(Timestamp createDate){
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

    public Timestamp getUpdateDate(){
    return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate){
    this.updateDate=updateDate;
    }
}