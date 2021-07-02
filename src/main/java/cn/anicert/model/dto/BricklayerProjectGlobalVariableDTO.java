package cn.anicert.model.dto;

import cn.anicert.model.d_o.BricklayerProjectGlobalVariableDO;
import cn.anicert.model.vo.BricklayerProjectGlobalVariableVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BricklayerProjectGlobalVariableDTO {

    // page field
    private int current;

    private int size;

    private List<Integer> ids;


    /**
     * 主键
     */
    private Integer id;

    /**
     * 全局变量键
     */
    private String variableKey;

    /**
     * 全局变量值
     */
    private String variableValue;

    /**
     *
     */
    private Integer belongProject;


    /*  ------------ data conversion ------------  */
    public BricklayerProjectGlobalVariableDO toBricklayerProjectGlobalVariableDO() {
        BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO = new BricklayerProjectGlobalVariableDO();
        bricklayerProjectGlobalVariableDO.setId(getId());
        bricklayerProjectGlobalVariableDO.setVariableKey(getVariableKey());
        bricklayerProjectGlobalVariableDO.setVariableValue(getVariableValue());
        bricklayerProjectGlobalVariableDO.setBelongProject(getBelongProject());
        return bricklayerProjectGlobalVariableDO;

    }

    public BricklayerProjectGlobalVariableVO toBricklayerProjectGlobalVariableVO() {
        BricklayerProjectGlobalVariableVO bricklayerProjectGlobalVariableVO = new BricklayerProjectGlobalVariableVO();
        bricklayerProjectGlobalVariableVO.setId(getId());
        bricklayerProjectGlobalVariableVO.setVariableKey(getVariableKey());
        bricklayerProjectGlobalVariableVO.setVariableValue(getVariableValue());
        bricklayerProjectGlobalVariableVO.setBelongProject(getBelongProject());
        return bricklayerProjectGlobalVariableVO;

    }


    public static List<BricklayerProjectGlobalVariableVO> toBricklayerProjectGlobalVariableVOList(List<BricklayerProjectGlobalVariableDTO> bricklayerProjectGlobalVariableDTOList) {
        List<BricklayerProjectGlobalVariableVO> collect = bricklayerProjectGlobalVariableDTOList.stream().map(x -> x.toBricklayerProjectGlobalVariableVO()).collect(Collectors.toList());
        return collect;
    }

    public static List<BricklayerProjectGlobalVariableDO> toBricklayerProjectGlobalVariableDOList(List<BricklayerProjectGlobalVariableDTO> bricklayerProjectGlobalVariableDtOList) {
        List<BricklayerProjectGlobalVariableDO> collect = bricklayerProjectGlobalVariableDtOList.stream().map(x -> x.toBricklayerProjectGlobalVariableDO()).collect(Collectors.toList());
        return collect;
    }

}