package cn.bricklayer.controller;

import cn.bricklayer.model.dto.BricklayerTableDTO;
import cn.bricklayer.model.dto.BricklayerTemplateDTO;
import cn.bricklayer.model.dto.SimulatedRenderDTO;
import cn.bricklayer.model.vo.BricklayerTableVO;
import cn.bricklayer.model.vo.BricklayerTemplateVO;
import cn.bricklayer.model.vo.SimulatedRenderVO;
import cn.bricklayer.serviceI.BricklayerDbServiceI;
import cn.bricklayer.serviceI.BricklayerTemplateServiceI;
import cn.bricklayer.utils.FreemarkerTemplateBuilder;
import cn.bricklayer.utils.MessageRuntimeException;
import cn.bricklayer.utils.ResponseVo;
import cn.build_support.java_bean.JavaBeanModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

/**
 * create by view
 */
@RestController
@RequestMapping("/bricklayerTemplate")
@RequiredArgsConstructor
public class BricklayerTemplateController {

    private final BricklayerTemplateServiceI bricklayerTemplateServiceI;

    private final BricklayerDbServiceI bricklayerDbServiceI;

    /**
     * 创建BricklayerTemplate
     */
    //@ApiOperation(value = "创建BricklayerTemplate", httpMethod = "POST")
    @RequestMapping("/save")
    public ResponseVo<BricklayerTemplateVO> saveBricklayerTemplate(@RequestBody(required = false) BricklayerTemplateDTO bricklayerTemplateDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerTemplateDTO = bricklayerTemplateServiceI.saveBricklayerTemplate(bricklayerTemplateDTO);
        BricklayerTemplateVO bricklayerTemplateVO = bricklayerTemplateDTO.toBricklayerTemplateVO();
        responseVo.setData(bricklayerTemplateVO);
        return responseVo;
    }

    /**
     * 更新BricklayerTemplate
     */
    //@ApiOperation(value = "更新BricklayerTemplate", httpMethod = "POST")
    @RequestMapping("/update")
    public ResponseVo<BricklayerTemplateVO> updateBricklayerTemplate(@RequestBody(required = false) BricklayerTemplateDTO bricklayerTemplateDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerTemplateServiceI.updateBricklayerTemplate(bricklayerTemplateDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerTemplate
     */
    //@ApiOperation(value = "删除BricklayerTemplate", httpMethod = "POST")
    @RequestMapping("/delete")
    public ResponseVo<BricklayerTemplateVO> deleteBricklayerTemplate(@RequestBody(required = false) BricklayerTemplateDTO bricklayerTemplateDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerTemplateDTO = bricklayerTemplateServiceI.deleteBricklayerTemplate(bricklayerTemplateDTO);
        BricklayerTemplateVO bricklayerTemplateVO = bricklayerTemplateDTO.toBricklayerTemplateVO();
        responseVo.setData(bricklayerTemplateVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerTemplate
     */
    //@ApiOperation(value = "分页查询BricklayerTemplate", httpMethod = "POST")
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerTemplateVO> listBricklayerTemplatePage(@RequestBody(required = false) BricklayerTemplateDTO bricklayerTemplateDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerTemplateDTO> bricklayerTemplateDTOIPage = bricklayerTemplateServiceI.listBricklayerTemplatePage(bricklayerTemplateDTO);
        IPage<BricklayerTemplateVO> rs = new Page(bricklayerTemplateDTOIPage.getCurrent(), bricklayerTemplateDTOIPage.getSize(), bricklayerTemplateDTOIPage.getTotal());
        rs.setRecords(BricklayerTemplateDTO.toBricklayerTemplateVOList(bricklayerTemplateDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerTemplate
     */
    //@ApiOperation(value = "根据主键获取BricklayerTemplate", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<BricklayerTemplateVO> getBricklayerTemplateById(@RequestBody(required = false) BricklayerTemplateDTO bricklayerTemplateDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerTemplateDTO = bricklayerTemplateServiceI.getBricklayerTemplateById(bricklayerTemplateDTO);
        BricklayerTemplateVO bricklayerTemplateVO = bricklayerTemplateDTO.toBricklayerTemplateVO();
        responseVo.setData(bricklayerTemplateVO);
        return responseVo;
    }


    @RequestMapping("/performSimulatedRendering")
    public ResponseVo<BricklayerTemplateVO> performSimulatedRendering(@RequestBody(required = false) SimulatedRenderDTO simulatedRenderDTO) {
        ResponseVo responseVo = new ResponseVo();
        BricklayerTableDTO dbTableModels = bricklayerDbServiceI.getBricklayerTableById(simulatedRenderDTO.getModelId());
        JavaBeanModel of = JavaBeanModel.of(dbTableModels, simulatedRenderDTO.getBasePath(), simulatedRenderDTO.getContextPath());
        Template templateByString = FreemarkerTemplateBuilder.getTemplateByString(simulatedRenderDTO.getTemplateContent());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        try {
            templateByString.process(of, outputStreamWriter);
            SimulatedRenderVO simulatedRenderVO = new SimulatedRenderVO();
            simulatedRenderVO.setRenderResult(new String(byteArrayOutputStream.toByteArray()));
            responseVo.setData(simulatedRenderVO);
            return responseVo;
        } catch (Exception e) {
            throw new MessageRuntimeException("渲染异常：\n\r" + e.getMessage());
        }


    }

    /**
     * 批量删除BricklayerTable
     */
    @RequestMapping("/deleteBatch")
    public ResponseVo<BricklayerTableVO> deleteBricklayerTableBatch(@RequestBody(required = false) BricklayerTemplateDTO bricklayerTemplateDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbServiceI.deleteBricklayerTableBatch(bricklayerTemplateDTO);
        responseVo.setMsg("操作成功（自动跳过非当前用户创建的模板）");
        return responseVo;
    }


}