package com.management.model.d_o;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.management.model.dto.BricklayerProjectDTO;

import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_project")
public class BricklayerProjectDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名
     */
    @TableField(value = "project_name")
    private String projectName;


    @TableField(value = "fix_project")
    private Boolean fixProject;

    /**
     * 项目描述
     */
    @TableField(value = "project_description")
    private String projectDescription;

    /**
     * 前端访问前缀
     */
    @TableField(value = "context_path")
    private String contextPath;

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
        bricklayerProjectDTO.setId(getId());
        bricklayerProjectDTO.setFixProject(getFixProject());
        bricklayerProjectDTO.setProjectName(getProjectName());
        bricklayerProjectDTO.setProjectDescription(getProjectDescription());
        bricklayerProjectDTO.setContextPath(getContextPath());
        return bricklayerProjectDTO;

    }


    public static List<BricklayerProjectDTO> toBricklayerProjectDTOList(List<BricklayerProjectDO> bricklayerProjectDOList) {
        List<BricklayerProjectDTO> collect = bricklayerProjectDOList.stream().map(x -> x.toBricklayerProjectDTO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

    public Boolean getFixProject() {
        return fixProject;
    }

    public void setFixProject(Boolean fixProject) {
        this.fixProject = fixProject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}