package cn.anicert.model.dto;

import cn.anicert.model.d_o.BricklayerTableDO;
import cn.anicert.model.vo.BricklayerTableVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BricklayerTableDTO {

    // page field
    private int current;

    private int size;

    private List<Integer> ids;

    /**
     *
     */
    private Integer id;

    /**
     * 来源表名
     */
    private String originalTableName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 来源设备
     */
    private String sourceDevice;

    /**
     * 来源数据库名
     */
    private String sourceDataBase;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private java.sql.Timestamp createDate;

    /**
     * 更新时间
     */
    private java.sql.Timestamp updateDate;

    private Integer deviceId;

    private List<BricklayerColumnDTO> bricklayerColumnDTOList = new ArrayList();

    private List<String> selectedTables = new ArrayList();


    private Boolean onlyMine;

    private String dataSourceName;


    /*  ------------ data conversion ------------  */
    public BricklayerTableDO toBricklayerTableDO() {
        BricklayerTableDO bricklayerTableDO = new BricklayerTableDO();
        bricklayerTableDO.setId(getId());
        bricklayerTableDO.setOriginalTableName(getOriginalTableName());
        bricklayerTableDO.setRemark(getRemark());
        bricklayerTableDO.setSourceDevice(getSourceDevice());
        bricklayerTableDO.setSourceDataBase(getSourceDataBase());
        bricklayerTableDO.setCreateBy(getCreateBy());
        bricklayerTableDO.setUpdateBy(getUpdateBy());
        bricklayerTableDO.setCreateDate(getCreateDate());
        bricklayerTableDO.setUpdateDate(getUpdateDate());
        return bricklayerTableDO;

    }

    public BricklayerTableVO toBricklayerTableVO() {
        BricklayerTableVO bricklayerTableVO = new BricklayerTableVO();
        bricklayerTableVO.setId(getId());
        bricklayerTableVO.setOriginalTableName(getOriginalTableName());
        bricklayerTableVO.setRemark(getRemark());
        bricklayerTableVO.setSourceDevice(getSourceDevice());
        bricklayerTableVO.setSourceDataBase(getSourceDataBase());
        bricklayerTableVO.setCreateBy(getCreateBy());
        bricklayerTableVO.setUpdateBy(getUpdateBy());
        bricklayerTableVO.setCreateDate(getCreateDate());
        bricklayerTableVO.setUpdateDate(getUpdateDate());
        return bricklayerTableVO;

    }


    public static List<BricklayerTableVO> toBricklayerTableVOList(List<BricklayerTableDTO> bricklayerTableDTOList) {
        List<BricklayerTableVO> collect = bricklayerTableDTOList.stream().map(x -> x.toBricklayerTableVO()).collect(Collectors.toList());
        return collect;
    }

    public static List<BricklayerTableDO> toBricklayerTableDOList(List<BricklayerTableDTO> bricklayerTableDtOList) {
        List<BricklayerTableDO> collect = bricklayerTableDtOList.stream().map(x -> x.toBricklayerTableDO()).collect(Collectors.toList());
        return collect;
    }

}