package cn.anicert.model.vo;

import cn.anicert.utils.NativeLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BricklayerLogVO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 创建日期
     */
    @JsonSerialize(using = NativeLocalDateTimeSerializer.class)
    private LocalDateTime createDate;
    /**
     * 修改日期
     */
    @JsonSerialize(using = NativeLocalDateTimeSerializer.class)
    private LocalDateTime updateDate;
    /**
     * ip
     */
    private String ipAddress;
    /**
     * 访问设备
     */
    private String userAgent;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 访问地址
     */
    private String requestUri;


    private Long countValue;
    /*  ------------ data conversion ------------  */


}