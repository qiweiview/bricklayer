package com.anicert.controller;

import com.anicert.model.dto.BricklayerDictionaryDTO;
import com.anicert.model.dto.IdsDTO;
import com.anicert.model.vo.BricklayerDictionaryVO;
import com.anicert.serviceI.BricklayerDictionaryServiceI;
import com.anicert.utils.ResponseVo;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequestMapping("/bricklayerDictionary")
@RequiredArgsConstructor
public class BricklayerDictionaryController {

    private final BricklayerDictionaryServiceI bricklayerDictionaryServiceI;


    /**
     * 创建BricklayerDictionary
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerDictionaryVO> saveBricklayerDictionary(@RequestBody(required = false) BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDictionaryDTO = bricklayerDictionaryServiceI.saveBricklayerDictionary(bricklayerDictionaryDTO);
        BricklayerDictionaryVO bricklayerDictionaryVO = bricklayerDictionaryDTO.toBricklayerDictionaryVO();
        responseVo.setData(bricklayerDictionaryVO);
        return responseVo;
    }

    /**
     * 更新BricklayerDictionary
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerDictionaryVO> updateBricklayerDictionary(@RequestBody(required = false) BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDictionaryServiceI.updateBricklayerDictionary(bricklayerDictionaryDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerDictionary
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerDictionaryVO> deleteBricklayerDictionary(@RequestBody(required = false) BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDictionaryDTO = bricklayerDictionaryServiceI.deleteBricklayerDictionary(bricklayerDictionaryDTO);
        BricklayerDictionaryVO bricklayerDictionaryVO = bricklayerDictionaryDTO.toBricklayerDictionaryVO();
        responseVo.setData(bricklayerDictionaryVO);
        return responseVo;
    }


    /**
     * 删除BricklayerDictionary
     */
    @RequestMapping("/batchDelete")
    public ResponseVo<BricklayerDictionaryVO> batchDelete(@RequestBody(required = false) IdsDTO idsDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDictionaryServiceI.batchDelete(idsDTO);
        return responseVo;
    }


    /**
     * 分页查询BricklayerDictionary
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerDictionaryVO> listBricklayerDictionaryPage(@RequestBody(required = false) BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerDictionaryDTO> bricklayerDictionaryDTOIPage = bricklayerDictionaryServiceI.listBricklayerDictionaryPage(bricklayerDictionaryDTO);
        IPage<BricklayerDictionaryVO> rs = new Page(bricklayerDictionaryDTOIPage.getCurrent(), bricklayerDictionaryDTOIPage.getSize(), bricklayerDictionaryDTOIPage.getTotal());
        rs.setRecords(BricklayerDictionaryDTO.toBricklayerDictionaryVOList(bricklayerDictionaryDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 根据名称获取字典值
     */
    @RequestMapping("/listByCode")
    public ResponseVo<BricklayerDictionaryVO> listByCode(@RequestBody(required = false) BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        ResponseVo responseVo = new ResponseVo();
        List<BricklayerDictionaryDTO> bricklayerDictionaryDTOS = bricklayerDictionaryServiceI.listByCode(bricklayerDictionaryDTO);
        List<BricklayerDictionaryVO> bricklayerDictionaryVOS = BricklayerDictionaryDTO.toBricklayerDictionaryVOList(bricklayerDictionaryDTOS);
        responseVo.setData(bricklayerDictionaryVOS);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerDictionary
     */
    @RequestMapping("/getById")
    public ResponseVo<BricklayerDictionaryVO> getBricklayerDictionaryById(@RequestBody(required = false) BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDictionaryDTO = bricklayerDictionaryServiceI.getBricklayerDictionaryById(bricklayerDictionaryDTO);
        BricklayerDictionaryVO bricklayerDictionaryVO = bricklayerDictionaryDTO.toBricklayerDictionaryVO();
        responseVo.setData(bricklayerDictionaryVO);
        return responseVo;
    }


}