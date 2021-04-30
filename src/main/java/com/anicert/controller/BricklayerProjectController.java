package com.anicert.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.anicert.model.dto.BricklayerProjectDTO;
import com.anicert.model.vo.BricklayerProjectVO;
import com.anicert.serviceI.BricklayerProjectServiceI;
import com.anicert.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bricklayerProject")
public class BricklayerProjectController {

    private final BricklayerProjectServiceI bricklayerProjectServiceI;

    @Autowired
    public BricklayerProjectController(BricklayerProjectServiceI bricklayerProjectServiceI) {
        this.bricklayerProjectServiceI = bricklayerProjectServiceI;
    }

    /**
     * 创建BricklayerProject
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerProjectVO> saveBricklayerProject(@RequestBody(required = false) BricklayerProjectDTO bricklayerProjectDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectDTO = bricklayerProjectServiceI.saveBricklayerProject(bricklayerProjectDTO);
        BricklayerProjectVO bricklayerProjectVO = bricklayerProjectDTO.toBricklayerProjectVO();
        responseVo.setData(bricklayerProjectVO);
        return responseVo;
    }

    /**
     * 更新BricklayerProject
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerProjectVO> updateBricklayerProject(@RequestBody(required = false) BricklayerProjectDTO bricklayerProjectDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectServiceI.updateBricklayerProject(bricklayerProjectDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerProject
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerProjectVO> deleteBricklayerProject(@RequestBody(required = false) BricklayerProjectDTO bricklayerProjectDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectDTO = bricklayerProjectServiceI.deleteBricklayerProject(bricklayerProjectDTO);
        BricklayerProjectVO bricklayerProjectVO = bricklayerProjectDTO.toBricklayerProjectVO();
        responseVo.setData(bricklayerProjectVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerProject
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerProjectVO> listBricklayerProjectPage(@RequestBody(required = false) BricklayerProjectDTO bricklayerProjectDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerProjectDTO> bricklayerProjectDTOIPage = bricklayerProjectServiceI.listBricklayerProjectPage(bricklayerProjectDTO);
        IPage<BricklayerProjectVO> rs = new Page(bricklayerProjectDTOIPage.getCurrent(), bricklayerProjectDTOIPage.getSize(), bricklayerProjectDTOIPage.getTotal());
        rs.setRecords(BricklayerProjectDTO.toBricklayerProjectVOList(bricklayerProjectDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerProject
     */
    @RequestMapping("/getById")
    public ResponseVo<BricklayerProjectVO> getBricklayerProjectById(@RequestBody(required = false) BricklayerProjectDTO bricklayerProjectDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectDTO = bricklayerProjectServiceI.getBricklayerProjectByIdWithTree(bricklayerProjectDTO);
        BricklayerProjectVO bricklayerProjectVO = bricklayerProjectDTO.toBricklayerProjectVO();
        responseVo.setData(bricklayerProjectVO);
        return responseVo;
    }


}