package com.anicert.controller;

import com.anicert.model.dto.BricklayerTableDTO;
import com.anicert.model.vo.BricklayerTableVO;
import com.anicert.serviceI.BricklayerTableServiceI;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.anicert.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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