package cn.bricklayer.model.dto;

import cn.bricklayer.model.d_o.BricklayerDbDO;
import cn.bricklayer.model.vo.BricklayerDbVO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BricklayerDbDTO {

    // page field
    private int current;

    private int size;

    /**
     * 数据库驱动
     */
    private String dbDriverClass;
    /**
     * 数据库ip
     */
    private String dbIp;

    /**
     * 数据库ip
     */
    private String serviceName;
    /**
     * 密码
     */
    private String dbPassword;
    /**
     * 数据库端口
     */
    private Integer dbPort;
    /**
     * 数据库类型
     */
    private  String dbType;
    /**
     * 用户名
     */
    private  String dbUser;
    /**
     * 
     */
    private  Integer id;


    /*  ------------ data conversion ------------  */
    public BricklayerDbDO toBricklayerDbDO() {
        BricklayerDbDO bricklayerDbDO = new BricklayerDbDO();
        bricklayerDbDO.setDbDriverClass(getDbDriverClass());
        bricklayerDbDO.setDbIp(getDbIp());
        bricklayerDbDO.setDbPassword(getDbPassword());
        bricklayerDbDO.setDbPort(getDbPort());
        bricklayerDbDO.setDbType(getDbType());
        bricklayerDbDO.setDbUser(getDbUser());
        bricklayerDbDO.setServiceName(getServiceName());
        bricklayerDbDO.setId(getId());
        return bricklayerDbDO;

        }

    public BricklayerDbVO toBricklayerDbVO() {
        BricklayerDbVO bricklayerDbVO = new BricklayerDbVO();
        bricklayerDbVO.setDbDriverClass(getDbDriverClass());
        bricklayerDbVO.setDbIp(getDbIp());
        bricklayerDbVO.setDbPassword(getDbPassword());
        bricklayerDbVO.setDbPort(getDbPort());
        bricklayerDbVO.setDbType(getDbType());
        bricklayerDbVO.setDbUser(getDbUser());
        bricklayerDbVO.setId(getId());
        bricklayerDbVO.setServiceName(getServiceName());
        return bricklayerDbVO;

        }



    public static   List<BricklayerDbVO>  toBricklayerDbVOList(List<BricklayerDbDTO> bricklayerDbDTOList){
        List<BricklayerDbVO> collect= bricklayerDbDTOList.stream().map(x->x.toBricklayerDbVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerDbDO>  toBricklayerDbDOList(List<BricklayerDbDTO> bricklayerDbDtOList){
        List<BricklayerDbDO> collect= bricklayerDbDtOList.stream().map(x->x.toBricklayerDbDO()).collect(Collectors.toList());
        return collect;
    }

}