package com.management.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.management.model.dto.BricklayerTemplateDTO;
import com.management.model.vo.BricklayerTemplateVO;
import com.management.serviceI.BricklayerTemplateServiceI;
import com.management.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
//import cn.anicert.university.constant.ErrorEnum;
//import cn.anicert.university.common.entity.dto.ResponseVo;

/**
 * create by view
 */
@RestController
@RequestMapping("/bricklayerTemplate")
public class BricklayerTemplateController {

    private final BricklayerTemplateServiceI bricklayerTemplateServiceI;

    @Autowired
    public BricklayerTemplateController(BricklayerTemplateServiceI bricklayerTemplateServiceI) {
        this.bricklayerTemplateServiceI = bricklayerTemplateServiceI;
    }

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

    @ExceptionHandler
    public ResponseVo exp(Exception ex, HttpServletResponse httpServletResponse) {
        ex.printStackTrace();
        httpServletResponse.setStatus(500);
        return ResponseVo.error("服务器异常");
    }


}