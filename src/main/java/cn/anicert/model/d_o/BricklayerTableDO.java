package cn.anicert.model.d_o;

import cn.anicert.config.LoginInterceptor;
import cn.anicert.model.dto.BricklayerTableDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_table")
@Data
public class BricklayerTableDO {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 来源表名
     */
    @TableField(value = "original_table_name")
    private String originalTableName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 来源设备
     */
    @TableField(value = "source_device")
    private String sourceDevice;

    /**
     * 来源数据库名
     */
    @TableField(value = "source_data_base")
    private String sourceDataBase;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    private Timestamp createDate;

    /**
     * 更新时间
     */
    @TableField(value = "update_date")
    private Timestamp updateDate;

    /*  ------------ init value ------------  */
    public void doInit() {
        setCreateBy(LoginInterceptor.getCurrentName());
        setCreateDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
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
    public BricklayerTableDTO toBricklayerTableDTO() {
        BricklayerTableDTO bricklayerTableDTO = new BricklayerTableDTO();
        bricklayerTableDTO.setId(getId());
        bricklayerTableDTO.setOriginalTableName(getOriginalTableName());
        bricklayerTableDTO.setRemark(getRemark());
        bricklayerTableDTO.setSourceDevice(getSourceDevice());
        bricklayerTableDTO.setSourceDataBase(getSourceDataBase());
        bricklayerTableDTO.setCreateBy(getCreateBy());
        bricklayerTableDTO.setUpdateBy(getUpdateBy());
        bricklayerTableDTO.setCreateDate(getCreateDate());
        bricklayerTableDTO.setUpdateDate(getUpdateDate());
        return bricklayerTableDTO;

    }


    public static List<BricklayerTableDTO> toBricklayerTableDTOList(List<BricklayerTableDO> bricklayerTableDOList) {
        List<BricklayerTableDTO> collect = bricklayerTableDOList.stream().map(x -> x.toBricklayerTableDTO()).collect(Collectors.toList());
        return collect;
    }

}