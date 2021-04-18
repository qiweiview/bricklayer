package com.management.model.dto;

import com.management.model.d_o.BricklayerTableDO;
import com.management.model.vo.BricklayerTableVO;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* create by view
*/
public class BricklayerTableDTO {

    // page field
    private int current;

    private int size;


    private  Integer id;

    private  String originalTableName;


/*  ------------ data conversion ------------  */
public  BricklayerTableDO toBricklayerTableDO(){
        BricklayerTableDO bricklayerTableDO =new BricklayerTableDO();
        bricklayerTableDO.setId(getId());
        bricklayerTableDO.setOriginalTableName(getOriginalTableName());
        return bricklayerTableDO;

        }

public  BricklayerTableVO toBricklayerTableVO(){
        BricklayerTableVO bricklayerTableVO =new BricklayerTableVO();
        bricklayerTableVO.setId(getId());
        bricklayerTableVO.setOriginalTableName(getOriginalTableName());
        return bricklayerTableVO;

        }



    public static   List<BricklayerTableVO>  toBricklayerTableVOList(List<BricklayerTableDTO> bricklayerTableDTOList){
        List<BricklayerTableVO> collect= bricklayerTableDTOList.stream().map(x->x.toBricklayerTableVO()).collect(Collectors.toList());
        return collect;
    }

    public static   List<BricklayerTableDO>  toBricklayerTableDOList(List<BricklayerTableDTO> bricklayerTableDTOList){
        List<BricklayerTableDO> collect= bricklayerTableDTOList.stream().map(x->x.toBricklayerTableDO()).collect(Collectors.toList());
        return collect;
    }

/*  ------------ getter setter ------------  */
    public int getCurrent(){
        return current;
    }

    public void setCurrent(int current){
        this.current=current;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }


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