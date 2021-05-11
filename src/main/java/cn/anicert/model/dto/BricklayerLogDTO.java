package cn.anicert.model.dto;

import cn.anicert.model.d_o.BricklayerLogDO;
import cn.anicert.model.vo.BricklayerLogVO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BricklayerLogDTO {

    // page field
    private int current;

    private int size;

    private List<Integer> ids;

    /**
     * 主键
     */
    private java.lang.Integer id;
    /**
     * 创建人
     */
    private java.lang.String createBy;
    /**
     * 修改人
     */
    private java.lang.String updateBy;
    /**
     * 创建日期
     */
    private java.sql.Timestamp createDate;
    /**
     * 修改日期
     */
    private java.sql.Timestamp updateDate;
    /**
     * 用户名
     */
    private java.lang.String nickName;
    /**
     * 访问地址
     */
    private java.lang.String accessPath;


    /*  ------------ data conversion ------------  */
    public BricklayerLogDO toBricklayerLogDO() {
        BricklayerLogDO bricklayerLogDO = new BricklayerLogDO();
        bricklayerLogDO.setId(getId());
        bricklayerLogDO.setCreateBy(getCreateBy());
        bricklayerLogDO.setUpdateBy(getUpdateBy());
        bricklayerLogDO.setCreateDate(getCreateDate());
        bricklayerLogDO.setUpdateDate(getUpdateDate());
        bricklayerLogDO.setNickName(getNickName());
        bricklayerLogDO.setAccessPath(getAccessPath());
        return bricklayerLogDO;

    }

    public BricklayerLogVO toBricklayerLogVO() {
        BricklayerLogVO bricklayerLogVO = new BricklayerLogVO();
        bricklayerLogVO.setId(getId());
        bricklayerLogVO.setCreateBy(getCreateBy());
        bricklayerLogVO.setUpdateBy(getUpdateBy());
        bricklayerLogVO.setCreateDate(getCreateDate());
        bricklayerLogVO.setUpdateDate(getUpdateDate());
        bricklayerLogVO.setNickName(getNickName());
        bricklayerLogVO.setAccessPath(getAccessPath());
        return bricklayerLogVO;

    }


    public static List<BricklayerLogVO> toBricklayerLogVOList(List<BricklayerLogDTO> bricklayerLogDTOList) {
        List<BricklayerLogVO> collect = bricklayerLogDTOList.stream().map(x -> x.toBricklayerLogVO()).collect(Collectors.toList());
        return collect;
    }

    public static List<BricklayerLogDO> toBricklayerLogDOList(List<BricklayerLogDTO> bricklayerLogDtOList) {
        List<BricklayerLogDO> collect = bricklayerLogDtOList.stream().map(x -> x.toBricklayerLogDO()).collect(Collectors.toList());
        return collect;
    }

}