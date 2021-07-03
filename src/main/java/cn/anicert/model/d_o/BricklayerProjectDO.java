package cn.anicert.model.d_o;

import cn.anicert.config.LoginInterceptor;
import cn.anicert.model.dto.BricklayerProjectDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@TableName("bricklayer_project")
public class BricklayerProjectDO {

    /**
     * 前端访问前缀
     */
    @TableField(value = "context_path")
    private String contextPath;


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目描述
     */
    @TableField(value = "project_description")
    private String projectDescription;

    /**
     * 项目名
     */
    @TableField(value = "project_name")
    private String projectName;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private Timestamp createDate;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 修改日期
     */
    @TableField(value = "update_date")
    private Timestamp updateDate;

    @TableField(exist = false)
    private List<BricklayerProjectGlobalVariableDO> globalVariables;

    /*  ------------ init value ------------  */
    public void doInit() {
        //setDelFlag(false);
        setCreateBy(LoginInterceptor.getCurrentName());
        setUpdateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        doUpdate();
    }

    public void doUpdate() {
        setUpdateBy(LoginInterceptor.getCurrentName());
        setUpdateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
    }

    public void doDelete() {
        //setDelFlag(true);
        doUpdate();
    }


    /*  ------------ data conversion by model ------------  */
    public BricklayerProjectDTO toBricklayerProjectDTO() {
        BricklayerProjectDTO bricklayerProjectDTO = new BricklayerProjectDTO();
        bricklayerProjectDTO.setContextPath(getContextPath());
        bricklayerProjectDTO.setCreateBy(getCreateBy());
        bricklayerProjectDTO.setCreateDate(getCreateDate());
        bricklayerProjectDTO.setId(getId());
        bricklayerProjectDTO.setProjectDescription(getProjectDescription());
        bricklayerProjectDTO.setProjectName(getProjectName());
        bricklayerProjectDTO.setUpdateBy(getUpdateBy());
        bricklayerProjectDTO.setUpdateDate(getUpdateDate());
        return bricklayerProjectDTO;

    }


    public static List<BricklayerProjectDTO> toBricklayerProjectDTOList(List<BricklayerProjectDO> bricklayerProjectDOList) {
        List<BricklayerProjectDTO> collect = bricklayerProjectDOList.stream().map(x -> x.toBricklayerProjectDTO()).collect(Collectors.toList());
        return collect;
    }

    /*  ------------ getter setter ------------  */

}