package com.anicert.serviceI;

import com.anicert.model.dto.BricklayerDirectTemplateRelationDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;



public interface BricklayerDirectTemplateRelationServiceI {

    public  BricklayerDirectTemplateRelationDTO   saveBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO);

    public  BricklayerDirectTemplateRelationDTO   updateBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO);

    public  BricklayerDirectTemplateRelationDTO   deleteBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO);

    public  IPage<BricklayerDirectTemplateRelationDTO>   listBricklayerDirectTemplateRelationPage(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO);

    public  List<BricklayerDirectTemplateRelationDTO>   listBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO);

    public  BricklayerDirectTemplateRelationDTO   getBricklayerDirectTemplateRelationById(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO);
}