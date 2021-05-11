package cn.anicert.model.d_o;

import cn.anicert.model.dto.BricklayerLogDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@TableName("bricklayer_log")
@Data
public class BricklayerLogDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private java.lang.Integer id;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private java.lang.String createBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private java.lang.String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private java.sql.Timestamp createDate;

    /**
     * 修改日期
     */
    @TableField(value = "update_date")
    private java.sql.Timestamp updateDate;

    /**
     * 用户名
     */
    @TableField(value = "nick_name")
    private java.lang.String nickName;

    /**
     * 访问地址
     */
    @TableField(value = "access_path")
    private java.lang.String accessPath;

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
    public BricklayerLogDTO toBricklayerLogDTO() {
        BricklayerLogDTO bricklayerLogDTO = new BricklayerLogDTO();
        bricklayerLogDTO.setId(getId());
        bricklayerLogDTO.setCreateBy(getCreateBy());
        bricklayerLogDTO.setUpdateBy(getUpdateBy());
        bricklayerLogDTO.setCreateDate(getCreateDate());
        bricklayerLogDTO.setUpdateDate(getUpdateDate());
        bricklayerLogDTO.setNickName(getNickName());
        bricklayerLogDTO.setAccessPath(getAccessPath());
        return bricklayerLogDTO;

    }


    public static List<BricklayerLogDTO> toBricklayerLogDTOList(List<BricklayerLogDO> bricklayerLogDOList) {
        List<BricklayerLogDTO> collect = bricklayerLogDOList.stream().map(x -> x.toBricklayerLogDTO()).collect(Collectors.toList());
        return collect;
    }

}