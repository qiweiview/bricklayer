package cn.bricklayer.model.d_o;

import cn.bricklayer.model.dto.BricklayerLogDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@TableName("bricklayer_log")
@Data
public class BricklayerLogDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private LocalDateTime createDate;

    /**
     * 修改日期
     */
    @TableField(value = "update_date")
    private LocalDateTime updateDate;

    /**
     * ip
     */
    @TableField(value = "ip_address")
    private String ipAddress;

    /**
     * 访问设备
     */
    @TableField(value = "user_agent")
    private String userAgent;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 访问地址
     */
    @TableField(value = "request_uri")
    private String requestUri;

    @TableField(exist = false)
    private Long countValue;

    /*  ------------ init value ------------  */
    public void doInit() {
        //setDelFlag(false);
        setCreateDate(LocalDateTime.now());
    }

    public void doUpdate() {
        setUpdateDate(LocalDateTime.now());
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
        bricklayerLogDTO.setCountValue(getCountValue());
        bricklayerLogDTO.setCreateDate(getCreateDate());
        bricklayerLogDTO.setUpdateDate(getUpdateDate());
        bricklayerLogDTO.setIpAddress(getIpAddress());
        bricklayerLogDTO.setUserAgent(getUserAgent());
        bricklayerLogDTO.setUserName(getUserName());
        bricklayerLogDTO.setRequestUri(getRequestUri());
        return bricklayerLogDTO;

    }


    public static List<BricklayerLogDTO> toBricklayerLogDTOList(List<BricklayerLogDO> bricklayerLogDOList) {
        List<BricklayerLogDTO> collect = bricklayerLogDOList.stream().map(x -> x.toBricklayerLogDTO()).collect(Collectors.toList());
        return collect;
    }

}