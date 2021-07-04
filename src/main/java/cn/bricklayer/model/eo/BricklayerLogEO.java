package cn.bricklayer.model.eo;


import cn.bricklayer.utils.exporter.ColumnDescription;
import lombok.Data;

@Data
public class BricklayerLogEO {


    /**
     * 主键
     */
    @ColumnDescription(columnName = "主键")
    private Integer id;

    /**
     * 创建人
     */
    @ColumnDescription(columnName = "创建人")
    private String createBy;

    /**
     * 修改人
     */
    @ColumnDescription(columnName = "修改人")
    private String updateBy;

    /**
     * 创建日期
     */
    @ColumnDescription(columnName = "创建日期")
    private java.sql.Timestamp createDate;

    /**
     * 修改日期
     */
    @ColumnDescription(columnName = "修改日期")
    private java.sql.Timestamp updateDate;

    /**
     * ip
     */
    @ColumnDescription(columnName = "ip")
    private String ipAddress;

    /**
     * 访问设备
     */
    @ColumnDescription(columnName = "访问设备")
    private String userAgent;

    /**
     * 用户名
     */
    @ColumnDescription(columnName = "用户名")
    private String userName;

    /**
     * 访问地址
     */
    @ColumnDescription(columnName = "访问时间")
    private String requestUri;


}