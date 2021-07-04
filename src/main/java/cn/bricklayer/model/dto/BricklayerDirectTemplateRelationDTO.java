package cn.bricklayer.model.dto;

import cn.bricklayer.model.d_o.BricklayerDirectTemplateRelationDO;
import cn.bricklayer.model.vo.BricklayerDirectTemplateRelationVO;

import java.util.List;
import java.util.stream.Collectors;


public class BricklayerDirectTemplateRelationDTO {

    // page field
    private int current;

    private int size;

    /**
     * 归属文件夹
     */
    private  Integer belongDirectId;
    /**
     * 主键
     */
    private  Integer id;
    /**
     * 模板id
     */
    private  Integer templateId;
    /**
     * 模板名称
     */
    private  String templateName;


/*  ------------ data conversion ------------  */
public  BricklayerDirectTemplateRelationDO toBricklayerDirectTemplateRelationDO(){
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO =new BricklayerDirectTemplateRelationDO();
        bricklayerDirectTemplateRelationDO.setBelongDirectId(getBelongDirectId());
        bricklayerDirectTemplateRelationDO.setId(getId());
        bricklayerDirectTemplateRelationDO.setTemplateId(getTemplateId());
        bricklayerDirectTemplateRelationDO.setTemplateName(getTemplateName());
        return bricklayerDirectTemplateRelationDO;

        }

    public BricklayerDirectTemplateRelationVO toBricklayerDirectTemplateRelationVO() {
        BricklayerDirectTemplateRelationVO bricklayerDirectTemplateRelationVO = new BricklayerDirectTemplateRelationVO();
        bricklayerDirectTemplateRelationVO.setBelongDirectId(getBelongDirectId());
        bricklayerDirectTemplateRelationVO.setId(getId());
        bricklayerDirectTemplateRelationVO.setTemplateId(getTemplateId());
        bricklayerDirectTemplateRelationVO.setTemplateName(getTemplateName());
        return bricklayerDirectTemplateRelationVO;

    }



    public static   List<BricklayerDirectTemplateRelationVO>  toBricklayerDirectTemplateRelationVOList(List<BricklayerDirectTemplateRelationDTO> bricklayerDirectTemplateRelationDTOList){
        List<BricklayerDirectTemplateRelationVO> collect= bricklayerDirectTemplateRelationDTOList.stream().map(x->x.toBricklayerDirectTemplateRelationVO()).collect(Collectors.toList());
        return collect;
    }

    public static  List<BricklayerDirectTemplateRelationDO>  toBricklayerDirectTemplateRelationDOList(List<BricklayerDirectTemplateRelationDTO> bricklayerDirectTemplateRelationDtOList){
        List<BricklayerDirectTemplateRelationDO> collect= bricklayerDirectTemplateRelationDtOList.stream().map(x->x.toBricklayerDirectTemplateRelationDO()).collect(Collectors.toList());
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


    public Integer getBelongDirectId(){
        return belongDirectId;
    }

    public void setBelongDirectId(Integer belongDirectId){
        this.belongDirectId=belongDirectId;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Integer getTemplateId(){
        return templateId;
    }

    public void setTemplateId(Integer templateId){
        this.templateId=templateId;
    }

    public String getTemplateName(){
        return templateName;
    }

    public void setTemplateName(String templateName){
        this.templateName=templateName;
    }
}