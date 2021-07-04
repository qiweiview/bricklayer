package cn.bricklayer.model.vo;

import lombok.Data;


@Data
public class BricklayerProjectGlobalVariableVO {

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


}