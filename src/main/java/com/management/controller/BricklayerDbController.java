package com.management.controller;

import com.management.model.dto.BricklayerDbDTO;
import com.management.model.vo.BricklayerDbVO;
import com.management.serviceI.BricklayerDbServiceI;
import com.webSupport.utils.UnifiedResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.management.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletResponse;


/**
*
* create by view
*/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bricklayerDb")
public class BricklayerDbController {

    private final BricklayerDbServiceI bricklayerDbServiceI;

    @Autowired
    public BricklayerDbController(BricklayerDbServiceI bricklayerDbServiceI) {
    this.bricklayerDbServiceI = bricklayerDbServiceI;
    }

    /**
     * 创建BricklayerDb
     */
    //@ApiOperation(value = "创建BricklayerDb", httpMethod = "POST")
    @RequestMapping("/save")
    public ResponseVo<BricklayerDbVO> saveBricklayerDb(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDbDTO=bricklayerDbServiceI.saveBricklayerDb(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO=bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
    }

    /**
     * 更新BricklayerDb
     */
    //@ApiOperation(value = "更新BricklayerDb", httpMethod = "POST")
    @RequestMapping("/update")
    public ResponseVo<BricklayerDbVO> updateBricklayerDb(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDbDTO=bricklayerDbServiceI.updateBricklayerDb(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO=bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
    }

    /**
     * 删除BricklayerDb
     */
    //@ApiOperation(value = "删除BricklayerDb", httpMethod = "POST")
    @RequestMapping("/delete")
    public ResponseVo<BricklayerDbVO> deleteBricklayerDb(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDbDTO=bricklayerDbServiceI.deleteBricklayerDb(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO=bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerDb
     */
    //@ApiOperation(value = "分页查询BricklayerDb", httpMethod = "POST")
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerDbVO> listBricklayerDbPage(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo=new ResponseVo();
        IPage<BricklayerDbDTO> bricklayerDbDTOIPage = bricklayerDbServiceI.listBricklayerDbPage(bricklayerDbDTO);
        IPage<BricklayerDbVO> rs = new Page(bricklayerDbDTOIPage.getCurrent(), bricklayerDbDTOIPage.getSize(), bricklayerDbDTOIPage.getTotal());
        rs.setRecords(BricklayerDbDTO.toBricklayerDbVOList(bricklayerDbDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }



    /**
     * 导出BricklayerDb
     */
    //@ApiOperation(value = "导出BricklayerDb", httpMethod = "POST")
    /* @RequestMapping("/export")
    public ResponseVo<BricklayerDbVO> listBricklayerDbPage(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO, HttpServletResponse httpServletResponse) {
        ResponseVo responseVo=new ResponseVo();
        List<BricklayerDbDTO> bricklayerDbDTOList = bricklayerDbServiceI.listBricklayerDb(bricklayerDbDTO);
        List<BricklayerDbVO> bricklayerDbVO = BricklayerDbDTO.toBricklayerDbVOList(bricklayerDbDTOList);

        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.addSheet(trainingDetailVOS, BricklayerDbVO.class);
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
     * 根据主键获取BricklayerDb
     */
    //@ApiOperation(value = "根据主键获取BricklayerDb", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<BricklayerDbVO> getBricklayerDbById(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDbDTO=bricklayerDbServiceI.getBricklayerDbById(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO=bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
        }

    @ExceptionHandler
    public ResponseVo exp(Exception ex, HttpServletResponse httpServletResponse) {
        ex.printStackTrace();
        httpServletResponse.setStatus(500);
        return ResponseVo.error(ex.getMessage());
    }

}