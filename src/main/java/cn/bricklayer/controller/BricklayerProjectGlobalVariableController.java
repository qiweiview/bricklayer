package cn.bricklayer.controller;

import cn.bricklayer.model.dto.BricklayerProjectGlobalVariableDTO;
import cn.bricklayer.model.vo.BricklayerProjectGlobalVariableVO;
import cn.bricklayer.serviceI.BricklayerProjectGlobalVariableServiceI;
import cn.bricklayer.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import cn.anicert.utils.exporter.ExcelExporter;


@RestController
@RequestMapping("/bricklayerProjectGlobalVariable")
@RequiredArgsConstructor
public class BricklayerProjectGlobalVariableController {

    private final BricklayerProjectGlobalVariableServiceI bricklayerProjectGlobalVariableServiceI;


    /**
     * 创建BricklayerProjectGlobalVariable
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerProjectGlobalVariableVO> saveBricklayerProjectGlobalVariable(@RequestBody(required = false) BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectGlobalVariableDTO = bricklayerProjectGlobalVariableServiceI.saveBricklayerProjectGlobalVariable(bricklayerProjectGlobalVariableDTO);
        BricklayerProjectGlobalVariableVO bricklayerProjectGlobalVariableVO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableVO();
        responseVo.setData(bricklayerProjectGlobalVariableVO);
        return responseVo;
    }

    /**
     * 更新BricklayerProjectGlobalVariable
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerProjectGlobalVariableVO> updateBricklayerProjectGlobalVariable(@RequestBody(required = false) BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectGlobalVariableServiceI.updateBricklayerProjectGlobalVariable(bricklayerProjectGlobalVariableDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerProjectGlobalVariable
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerProjectGlobalVariableVO> deleteBricklayerProjectGlobalVariable(@RequestBody(required = false) BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectGlobalVariableDTO = bricklayerProjectGlobalVariableServiceI.deleteBricklayerProjectGlobalVariable(bricklayerProjectGlobalVariableDTO);
        BricklayerProjectGlobalVariableVO bricklayerProjectGlobalVariableVO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableVO();
        responseVo.setData(bricklayerProjectGlobalVariableVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerProjectGlobalVariable
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerProjectGlobalVariableVO> listBricklayerProjectGlobalVariablePage(@RequestBody(required = false) BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerProjectGlobalVariableDTO> bricklayerProjectGlobalVariableDTOIPage = bricklayerProjectGlobalVariableServiceI.listBricklayerProjectGlobalVariablePage(bricklayerProjectGlobalVariableDTO);
        IPage<BricklayerProjectGlobalVariableVO> rs = new Page(bricklayerProjectGlobalVariableDTOIPage.getCurrent(), bricklayerProjectGlobalVariableDTOIPage.getSize(), bricklayerProjectGlobalVariableDTOIPage.getTotal());
        rs.setRecords(BricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableVOList(bricklayerProjectGlobalVariableDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerProjectGlobalVariable
     */
    @RequestMapping("/queryById")
    public ResponseVo<BricklayerProjectGlobalVariableVO> queryBricklayerProjectGlobalVariableById(@RequestBody(required = false) BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectGlobalVariableDTO = bricklayerProjectGlobalVariableServiceI.getBricklayerProjectGlobalVariableById(bricklayerProjectGlobalVariableDTO);
        BricklayerProjectGlobalVariableVO bricklayerProjectGlobalVariableVO = bricklayerProjectGlobalVariableDTO.toBricklayerProjectGlobalVariableVO();
        responseVo.setData(bricklayerProjectGlobalVariableVO);
        return responseVo;
    }

    /**
     * 批量删除BricklayerProjectGlobalVariable
     */
    @RequestMapping("/deleteBatch")
    public ResponseVo<BricklayerProjectGlobalVariableVO> deleteBricklayerProjectGlobalVariableBatch(@RequestBody(required = false) BricklayerProjectGlobalVariableDTO bricklayerProjectGlobalVariableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectGlobalVariableServiceI.deleteBricklayerProjectGlobalVariableBatch(bricklayerProjectGlobalVariableDTO);
        return responseVo;
    }


}