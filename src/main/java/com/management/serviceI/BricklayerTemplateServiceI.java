package com.management.serviceI;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.management.model.dto.BricklayerTemplateDTO;

import java.util.List;

/**
 * create by view
 */
public interface BricklayerTemplateServiceI {

    public BricklayerTemplateDTO saveBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO);

    public BricklayerTemplateDTO updateBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO);

    public BricklayerTemplateDTO deleteBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO);

    public IPage<BricklayerTemplateDTO> listBricklayerTemplatePage(BricklayerTemplateDTO bricklayerTemplateDTO);

    public List<BricklayerTemplateDTO> listBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO);

    public BricklayerTemplateDTO getBricklayerTemplateById(BricklayerTemplateDTO bricklayerTemplateDTO);
}