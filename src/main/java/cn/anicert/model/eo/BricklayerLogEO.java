package cn.anicert.model.eo;


import cn.anicert.utils.exporter.ColumnDescription;
import lombok.Data;

@Data
public class BricklayerLogEO {


    /**
     * 主键
     */
    @ColumnDescription(columnName = "主键")
    private java.lang.Integer id;

    /**
     * 创建人
     */
    @ColumnDescription(columnName = "创建人")
    private java.lang.String createBy;

    /**
     * 修改人
     */
    @ColumnDescription(columnName = "修改人")
    private java.lang.String updateBy;

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
     * 用户名
     */
    @ColumnDescription(columnName = "用户名")
    private java.lang.String nickName;

    /**
     * 访问地址
     */
    @ColumnDescription(columnName = "访问地址")
    private java.lang.String accessPath;


}