package cn.anicert.model.d_o;

import cn.anicert.model.dto.BricklayerDbDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
@TableName("bricklayer_db")
public class BricklayerDbDO {

    /**
     * 数据库驱动
     */
    @TableField(value = "db_driver_class")
    private String dbDriverClass;

    /**
     * 数据库ip
     */
    @TableField(value = "db_ip")
    private String dbIp;


    /**
     * 服务名称
     */
    @TableField(value = "service_name")
    private String serviceName;


    /**
     * 密码
     */
    @TableField(value = "db_password")
    private String dbPassword;

    /**
     * 数据库端口
     */
    @TableField(value = "db_port")
    private  Integer dbPort;

    /**
     * 数据库类型
     */
    @TableField(value = "db_type")
    private  String dbType;

    /**
     * 用户名
     */
    @TableField(value = "db_user")
    private  String dbUser;

    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

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
    public  BricklayerDbDTO toBricklayerDbDTO(){
        BricklayerDbDTO bricklayerDbDTO =new BricklayerDbDTO();
        bricklayerDbDTO.setDbDriverClass(getDbDriverClass());
        bricklayerDbDTO.setDbIp(getDbIp());
        bricklayerDbDTO.setServiceName(getServiceName());
        bricklayerDbDTO.setDbPassword(getDbPassword());
        bricklayerDbDTO.setDbPort(getDbPort());
        bricklayerDbDTO.setDbType(getDbType());
        bricklayerDbDTO.setDbUser(getDbUser());
        bricklayerDbDTO.setId(getId());
        return bricklayerDbDTO;

    }


    public static   List<BricklayerDbDTO>  toBricklayerDbDTOList(List<BricklayerDbDO> bricklayerDbDOList){
        List<BricklayerDbDTO> collect = bricklayerDbDOList.stream().map(x -> x.toBricklayerDbDTO()).collect(Collectors.toList());
        return collect;
}
}