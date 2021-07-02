package cn.anicert.model.d_o;

import cn.anicert.model.dto.BricklayerProjectGlobalVariableDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_project_global_variable")
@Data
public class BricklayerProjectGlobalVariableDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 全局变量键
     */
    @TableField(value = "variable_key")
    private String variableKey;

    /**
     * 全局变量值
     */
    @TableField(value = "variable_value")
    private String variableValue;

    /**
     *
     */
    @TableField(value = "belong_project")
    private Integer belongProject;

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
        //setDeleteTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
    }


    /*  ------------ data conversion by model ------------  */
    public BricklayerProjectGlobalVariableDTO toBricklayerProjectGlobalVariableDTO() {
        BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO = new BricklayerProjectGlobalVariableDTO();
        bricklayerProjectGlobalVariableDTO.setId(getId());
        bricklayerProjectGlobalVariableDTO.setVariableKey(getVariableKey());
        bricklayerProjectGlobalVariableDTO.setVariableValue(getVariableValue());
        bricklayerProjectGlobalVariableDTO.setBelongProject(getBelongProject());
        return bricklayerProjectGlobalVariableDTO;

    }


    public static List<BricklayerProjectGlobalVariableDTO> toBricklayerProjectGlobalVariableDTOList(List<BricklayerProjectGlobalVariableDO> bricklayerProjectGlobalVariableDOList) {
        List<BricklayerProjectGlobalVariableDTO> collect = bricklayerProjectGlobalVariableDOList.stream().map(x -> x.toBricklayerProjectGlobalVariableDTO()).collect(Collectors.toList());
        return collect;
    }

}