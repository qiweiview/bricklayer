package com.management.controller;

import com.management.model.dto.BricklayerTableDTO;
import com.management.model.vo.BricklayerTableVO;
import com.management.serviceI.BricklayerTableServiceI;
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
@RequestMapping("/bricklayerTable")
public class BricklayerTableController {

    private final BricklayerTableServiceI bricklayerTableServiceI;

    @Autowired
    public BricklayerTableController(BricklayerTableServiceI bricklayerTableServiceI) {
    this.bricklayerTableServiceI = bricklayerTableServiceI;
    }

    /**
     * 创建BricklayerTable
     */
    //@ApiOperation(value = "创建BricklayerTable", httpMethod = "POST")
    @RequestMapping("/save")
    public ResponseVo<BricklayerTableVO> saveBricklayerTable(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerTableDTO=bricklayerTableServiceI.saveBricklayerTable(bricklayerTableDTO);
        BricklayerTableVO bricklayerTableVO=bricklayerTableDTO.toBricklayerTableVO();
        responseVo.setData(bricklayerTableVO);
        return responseVo;
    }

    /**
     * 更新BricklayerTable
     */
    //@ApiOperation(value = "更新BricklayerTable", httpMethod = "POST")
    @RequestMapping("/update")
    public ResponseVo<BricklayerTableVO> updateBricklayerTable(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerTableServiceI.updateBricklayerTable(bricklayerTableDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerTable
     */
    //@ApiOperation(value = "删除BricklayerTable", httpMethod = "POST")
    @RequestMapping("/delete")
    public ResponseVo<BricklayerTableVO> deleteBricklayerTable(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerTableDTO=bricklayerTableServiceI.deleteBricklayerTable(bricklayerTableDTO);
        BricklayerTableVO bricklayerTableVO=bricklayerTableDTO.toBricklayerTableVO();
        responseVo.setData(bricklayerTableVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerTable
     */
    //@ApiOperation(value = "分页查询BricklayerTable", httpMethod = "POST")
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerTableVO> listBricklayerTablePage(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo=new ResponseVo();
        IPage<BricklayerTableDTO> bricklayerTableDTOIPage = bricklayerTableServiceI.listBricklayerTablePage(bricklayerTableDTO);
        IPage<BricklayerTableVO> rs = new Page(bricklayerTableDTOIPage.getCurrent(), bricklayerTableDTOIPage.getSize(), bricklayerTableDTOIPage.getTotal());
        rs.setRecords(BricklayerTableDTO.toBricklayerTableVOList(bricklayerTableDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }



    /**
     * 导出BricklayerTable
     */
    //@ApiOperation(value = "导出BricklayerTable", httpMethod = "POST")
    /* @RequestMapping("/export")
    public ResponseVo<BricklayerTableVO> listBricklayerTablePage(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO, HttpServletResponse httpServletResponse) {
        ResponseVo responseVo=new ResponseVo();
        List<BricklayerTableDTO> bricklayerTableDTOList = bricklayerTableServiceI.listBricklayerTable(bricklayerTableDTO);
        List<BricklayerTableVO> bricklayerTableVO = BricklayerTableDTO.toBricklayerTableVOList(bricklayerTableDTOList);

        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.addSheet(trainingDetailVOS, BricklayerTableVO.class);
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
     * 根据主键获取BricklayerTable
     */
    //@ApiOperation(value = "根据主键获取BricklayerTable", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<BricklayerTableVO> getBricklayerTableById(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerTableDTO=bricklayerTableServiceI.getBricklayerTableById(bricklayerTableDTO);
        BricklayerTableVO bricklayerTableVO=bricklayerTableDTO.toBricklayerTableVO();
        responseVo.setData(bricklayerTableVO);
        return responseVo;
        }




}