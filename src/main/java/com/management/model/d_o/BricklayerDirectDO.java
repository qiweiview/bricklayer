package com.management.model.d_o;

import com.management.model.dto.BricklayerDirectDTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;



@TableName("bricklayer_direct")
public class BricklayerDirectDO {

    /**
     * 所属项目主键
     */
    @TableField(value = "belong_project_id")
    private  Integer belongProjectId;

    /**
     * 文件夹名称
     */
    @TableField(value = "direct_name")
    private  String directName;

    /**
     * 文件夹类型
     */
    @TableField(value = "direct_type")
    private  String directType;

    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    /**
     * 父级文件夹主键
     */
    @TableField(value = "parent_direct_id")
    private  Integer parentDirectId;

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
    public  BricklayerDirectDTO toBricklayerDirectDTO(){
    BricklayerDirectDTO bricklayerDirectDTO =new BricklayerDirectDTO();
    bricklayerDirectDTO.setBelongProjectId(getBelongProjectId());
    bricklayerDirectDTO.setDirectName(getDirectName());
    bricklayerDirectDTO.setDirectType(getDirectType());
    bricklayerDirectDTO.setId(getId());
    bricklayerDirectDTO.setParentDirectId(getParentDirectId());
        return bricklayerDirectDTO;

}


    public static   List<BricklayerDirectDTO>  toBricklayerDirectDTOList(List<BricklayerDirectDO> bricklayerDirectDOList){
        List<BricklayerDirectDTO> collect = bricklayerDirectDOList.stream().map(x -> x.toBricklayerDirectDTO()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */

    public Integer getBelongProjectId(){
    return belongProjectId;
    }

    public void setBelongProjectId(Integer belongProjectId){
    this.belongProjectId=belongProjectId;
    }

    public String getDirectName(){
    return directName;
    }

    public void setDirectName(String directName){
    this.directName=directName;
    }

    public String getDirectType(){
    return directType;
    }

    public void setDirectType(String directType){
    this.directType=directType;
    }

    public Integer getId(){
    return id;
    }

    public void setId(Integer id){
    this.id=id;
    }

    public Integer getParentDirectId(){
    return parentDirectId;
    }

    public void setParentDirectId(Integer parentDirectId){
    this.parentDirectId=parentDirectId;
    }
}