package cn.anicert.model.dto;

import cn.anicert.model.d_o.BricklayerDirectDO;
import cn.anicert.model.vo.BricklayerDirectVO;

import java.util.List;
import java.util.stream.Collectors;


public class BricklayerDirectDTO {

    // page field
    private int current;

    private int size;

    /**
     * 所属项目主键
     */
    private  Integer belongProjectId;
    /**
     * 目录全路径
     */
    private  String directFullPath;
    /**
     * 文件夹名称
     */
    private  String directName;
    /**
     * 
     */
    private  Integer id;
    /**
     * 父级文件夹主键
     */
    private  Integer parentDirectId;


/*  ------------ data conversion ------------  */
public  BricklayerDirectDO toBricklayerDirectDO(){
        BricklayerDirectDO bricklayerDirectDO =new BricklayerDirectDO();
        bricklayerDirectDO.setBelongProjectId(getBelongProjectId());
        bricklayerDirectDO.setDirectFullPath(getDirectFullPath());
        bricklayerDirectDO.setDirectName(getDirectName());
        bricklayerDirectDO.setId(getId());
        bricklayerDirectDO.setParentDirectId(getParentDirectId());
        return bricklayerDirectDO;

        }

public  BricklayerDirectVO toBricklayerDirectVO(){
        BricklayerDirectVO bricklayerDirectVO =new BricklayerDirectVO();
        bricklayerDirectVO.setBelongProjectId(getBelongProjectId());
        bricklayerDirectVO.setDirectFullPath(getDirectFullPath());
        bricklayerDirectVO.setDirectName(getDirectName());
        bricklayerDirectVO.setId(getId());
        bricklayerDirectVO.setParentDirectId(getParentDirectId());
        return bricklayerDirectVO;

        }



    public static   List<BricklayerDirectVO>  toBricklayerDirectVOList(List<BricklayerDirectDTO> bricklayerDirectDTOList){
        List<BricklayerDirectVO> collect= bricklayerDirectDTOList.stream().map(x->x.toBricklayerDirectVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerDirectDO>  toBricklayerDirectDOList(List<BricklayerDirectDTO> bricklayerDirectDtOList){
        List<BricklayerDirectDO> collect= bricklayerDirectDtOList.stream().map(x->x.toBricklayerDirectDO()).collect(Collectors.toList());
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

    public String getDirectFullPath(){
        return directFullPath;
    }

    public void setDirectFullPath(String directFullPath){
        this.directFullPath=directFullPath;
    }

    public String getDirectName(){
        return directName;
    }

    public void setDirectName(String directName){
        this.directName=directName;
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