package cn.anicert.serviceI;

import cn.anicert.model.dto.BricklayerTemplateDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

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