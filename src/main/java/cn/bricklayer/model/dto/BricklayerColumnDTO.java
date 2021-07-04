package cn.bricklayer.model.dto;

import cn.bricklayer.model.d_o.BricklayerColumnDO;
import cn.bricklayer.model.vo.BricklayerColumnVO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BricklayerColumnDTO {

    // page field
    private int current;

    private int size;

    private List<Integer> ids;

    /**
     *
     */
    private Integer id;
    /**
     * 原始列名
     */
    private String originalColumnName;
    /**
     * 简单列类型
     */
    private String simpleColumnType;
    /**
     * 列类型（完整）
     */
    private String columnType;
    /**
     * 是否主键
     */
    private String columnKey;
    /**
     * 是否自增
     */
    private String extra;
    /**
     * 列注释
     */
    private String comment;
    /**
     * 所属模型
     */
    private Integer belongTableId;
    /**
     * 列排序
     */
    private Integer columnOrder;


/*  ------------ data conversion ------------  */
public  BricklayerColumnDO toBricklayerColumnDO(){
    BricklayerColumnDO bricklayerColumnDO = new BricklayerColumnDO();
    bricklayerColumnDO.setId(getId());
    bricklayerColumnDO.setOriginalColumnName(getOriginalColumnName());
    bricklayerColumnDO.setSimpleColumnType(getSimpleColumnType());
    bricklayerColumnDO.setColumnType(getColumnType());
    bricklayerColumnDO.setColumnKey(getColumnKey());
    bricklayerColumnDO.setExtra(getExtra());
    bricklayerColumnDO.setComment(getComment());
    bricklayerColumnDO.setBelongTableId(getBelongTableId());
    bricklayerColumnDO.setColumnOrder(getColumnOrder());
    return bricklayerColumnDO;

}

    public  BricklayerColumnVO toBricklayerColumnVO(){
        BricklayerColumnVO bricklayerColumnVO = new BricklayerColumnVO();
        bricklayerColumnVO.setId(getId());
        bricklayerColumnVO.setOriginalColumnName(getOriginalColumnName());
        bricklayerColumnVO.setSimpleColumnType(getSimpleColumnType());
        bricklayerColumnVO.setColumnType(getColumnType());
        bricklayerColumnVO.setColumnKey(getColumnKey());
        bricklayerColumnVO.setExtra(getExtra());
        bricklayerColumnVO.setComment(getComment());
        bricklayerColumnVO.setBelongTableId(getBelongTableId());
        bricklayerColumnVO.setColumnOrder(getColumnOrder());
        return bricklayerColumnVO;

    }


    public static   List<BricklayerColumnVO>  toBricklayerColumnVOList(List<BricklayerColumnDTO> bricklayerColumnDTOList){
        List<BricklayerColumnVO> collect= bricklayerColumnDTOList.stream().map(x->x.toBricklayerColumnVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerColumnDO>  toBricklayerColumnDOList(List<BricklayerColumnDTO> bricklayerColumnDtOList){
        List<BricklayerColumnDO> collect= bricklayerColumnDtOList.stream().map(x->x.toBricklayerColumnDO()).collect(Collectors.toList());
        return collect;
    }

}