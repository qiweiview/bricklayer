package com.management.controller;

import com.management.model.dto.BricklayerColumnDTO;
import com.management.model.vo.BricklayerColumnVO;
import com.management.serviceI.BricklayerColumnServiceI;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.management.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;
//import cn.anicert.university.constant.ErrorEnum;
//import cn.anicert.university.common.entity.dto.ResponseVo;

/**
*
* create by view
*/
@RestController
@RequestMapping("/bricklayerColumn")
public class BricklayerColumnController {

    private final BricklayerColumnServiceI bricklayerColumnServiceI;

    @Autowired
    public BricklayerColumnController(BricklayerColumnServiceI bricklayerColumnServiceI) {
    this.bricklayerColumnServiceI = bricklayerColumnServiceI;
    }

    /**
     * 创建BricklayerColumn
     */
    //@ApiOperation(value = "创建BricklayerColumn", httpMethod = "POST")
    @RequestMapping("/save")
    public ResponseVo<BricklayerColumnVO> saveBricklayerColumn(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerColumnDTO=bricklayerColumnServiceI.saveBricklayerColumn(bricklayerColumnDTO);
        BricklayerColumnVO bricklayerColumnVO=bricklayerColumnDTO.toBricklayerColumnVO();
        responseVo.setData(bricklayerColumnVO);
        return responseVo;
    }

    /**
     * 更新BricklayerColumn
     */
    //@ApiOperation(value = "更新BricklayerColumn", httpMethod = "POST")
    @RequestMapping("/update")
    public ResponseVo<BricklayerColumnVO> updateBricklayerColumn(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerColumnServiceI.updateBricklayerColumn(bricklayerColumnDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerColumn
     */
    //@ApiOperation(value = "删除BricklayerColumn", httpMethod = "POST")
    @RequestMapping("/delete")
    public ResponseVo<BricklayerColumnVO> deleteBricklayerColumn(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerColumnDTO=bricklayerColumnServiceI.deleteBricklayerColumn(bricklayerColumnDTO);
        BricklayerColumnVO bricklayerColumnVO=bricklayerColumnDTO.toBricklayerColumnVO();
        responseVo.setData(bricklayerColumnVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerColumn
     */
    //@ApiOperation(value = "分页查询BricklayerColumn", httpMethod = "POST")
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerColumnVO> listBricklayerColumnPage(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO) {
        ResponseVo responseVo=new ResponseVo();
        IPage<BricklayerColumnDTO> bricklayerColumnDTOIPage = bricklayerColumnServiceI.listBricklayerColumnPage(bricklayerColumnDTO);
        IPage<BricklayerColumnVO> rs = new Page(bricklayerColumnDTOIPage.getCurrent(), bricklayerColumnDTOIPage.getSize(), bricklayerColumnDTOIPage.getTotal());
        rs.setRecords(BricklayerColumnDTO.toBricklayerColumnVOList(bricklayerColumnDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }



    /**
     * 导出BricklayerColumn
     */
    //@ApiOperation(value = "导出BricklayerColumn", httpMethod = "POST")
    /* @RequestMapping("/export")
    public ResponseVo<BricklayerColumnVO> listBricklayerColumnPage(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO, HttpServletResponse httpServletResponse) {
        ResponseVo responseVo=new ResponseVo();
        List<BricklayerColumnDTO> bricklayerColumnDTOList = bricklayerColumnServiceI.listBricklayerColumn(bricklayerColumnDTO);
        List<BricklayerColumnVO> bricklayerColumnVO = BricklayerColumnDTO.toBricklayerColumnVOList(bricklayerColumnDTOList);

        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.addSheet(trainingDetailVOS, BricklayerColumnVO.class);
        byte[] bytes = excelExporter.list2Excel();


        httpServletResponse.reset();
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + "export.xlsx" + "\"");
        httpServletResponse.setHeader("Set-Cookie", "fileDownload=true; path=/");
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");

        try {
        ServletOutputStream out = httpServletResponse.getOutputStream();
        out.write(bytes);
        out.close();
        } catch (IOException e) {
        e.printStackTrace();
        }

        return responseVo;
        }*/


    /**
     * 根据主键获取BricklayerColumn
     */
    //@ApiOperation(value = "根据主键获取BricklayerColumn", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<BricklayerColumnVO> getBricklayerColumnById(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerColumnDTO=bricklayerColumnServiceI.getBricklayerColumnById(bricklayerColumnDTO);
        BricklayerColumnVO bricklayerColumnVO=bricklayerColumnDTO.toBricklayerColumnVO();
        responseVo.setData(bricklayerColumnVO);
        return responseVo;
        }




}