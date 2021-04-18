package com.management.model.d_o;

import com.management.model.dto.BricklayerTableDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
*
* create by view
*/
@TableName("bricklayer_table")
public class BricklayerTableDO {

    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 
     */
    @TableField(value = "original_table_name")
    private  String originalTableName;

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
    public  BricklayerTableDTO toBricklayerTableDTO(){
    BricklayerTableDTO bricklayerTableDTO =new BricklayerTableDTO();
    bricklayerTableDTO.setId(getId());
    bricklayerTableDTO.setOriginalTableName(getOriginalTableName());
        return bricklayerTableDTO;

}


    public static   List<BricklayerTableDTO>  toBricklayerTableDTOList(List<BricklayerTableDO> bricklayerTableDOList){
        List<BricklayerTableDTO> collect = bricklayerTableDOList.stream().map(x -> x.toBricklayerTableDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public Integer getId(){
    return id;
    }

    public void setId(Integer id){
    this.id=id;
    }

    public String getOriginalTableName(){
    return originalTableName;
    }

    public void setOriginalTableName(String originalTableName){
    this.originalTableName=originalTableName;
    }
}