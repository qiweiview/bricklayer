package com.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buildSupport.java_bean.JavaBeanModel;
import com.buildSupport.utils.FreemarkerTemplateBuilder;
import com.management.model.dto.BricklayerTableDTO;
import com.management.model.dto.BricklayerTemplateDTO;
import com.management.model.dto.SimulatedRenderDTO;
import com.management.model.vo.BricklayerTemplateVO;
import com.management.model.vo.SimulatedRenderVO;
import com.management.serviceI.BricklayerDbServiceI;
import com.management.serviceI.BricklayerTemplateServiceI;
import com.management.utils.MessageRuntimeException;
import com.management.utils.ResponseVo;
import freemarker.template.Template;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.ByteArrayOutputStream;

import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

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
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, Charset.forName("utf-8"));
        try {
            templateByString.process(of, outputStreamWriter);
            SimulatedRenderVO simulatedRenderVO = new SimulatedRenderVO();
            simulatedRenderVO.setRenderResult(new String(byteArrayOutputStream.toByteArray()));
            responseVo.setData(simulatedRenderVO);
            return responseVo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageRuntimeException("渲染异常");
        }


    }




}