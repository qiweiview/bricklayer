package com.anicert.model.dto;

import com.anicert.model.d_o.BricklayerDirectDO;
import com.anicert.model.vo.BricklayerDirectVO;

import java.util.List;
import java.util.stream.Collectors;


public class BricklayerDirectDTO {

    // page field
    private int current;

    private int size;


    private  Integer belongProjectId;

    private  String directName;

    private String directFullPath;

    private  Integer id;

    private  Integer parentDirectId;


/*  ------------ data conversion ------------  */
public  BricklayerDirectDO toBricklayerDirectDO(){
        BricklayerDirectDO bricklayerDirectDO =new BricklayerDirectDO();
        bricklayerDirectDO.setBelongProjectId(getBelongProjectId());
    bricklayerDirectDO.setDirectName(getDirectName());
    bricklayerDirectDO.setDirectFullPath(getDirectFullPath());
    bricklayerDirectDO.setId(getId());
        bricklayerDirectDO.setParentDirectId(getParentDirectId());
        return bricklayerDirectDO;

        }

public  BricklayerDirectVO toBricklayerDirectVO(){
        BricklayerDirectVO bricklayerDirectVO =new BricklayerDirectVO();
        bricklayerDirectVO.setBelongProjectId(getBelongProjectId());
    bricklayerDirectVO.setDirectName(getDirectName());
    bricklayerDirectVO.setDirectFullPath(getDirectFullPath());
    bricklayerDirectVO.setId(getId());
        bricklayerDirectVO.setParentDirectId(getParentDirectId());
        return bricklayerDirectVO;

        }



    public static   List<BricklayerDirectVO>  toBricklayerDirectVOList(List<BricklayerDirectDTO> bricklayerDirectDTOList){
        List<BricklayerDirectVO> collect= bricklayerDirectDTOList.stream().map(x->x.toBricklayerDirectVO()).collect(Collectors.toList());
        return collect;
    }

    public static   List<BricklayerDirectDO>  toBricklayerDirectDOList(List<BricklayerDirectDTO> bricklayerDirectDTOList){
        List<BricklayerDirectDO> collect= bricklayerDirectDTOList.stream().map(x->x.toBricklayerDirectDO()).collect(Collectors.toList());
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


    public Integer getBelongProjectId(){
        return belongProjectId;
    }

    public void setBelongProjectId(Integer belongProjectId){
        this.belongProjectId=belongProjectId;
    }

    public String getDirectName() {
        return directName;
    }

    public void setDirectName(String directName) {
        this.directName = directName;
    }

    public String getDirectFullPath() {
        return directFullPath;
    }

    public void setDirectFullPath(String directFullPath) {
        this.directFullPath = directFullPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentDirectId(){
        return parentDirectId;
    }

    public void setParentDirectId(Integer parentDirectId){
        this.parentDirectId=parentDirectId;
    }
}