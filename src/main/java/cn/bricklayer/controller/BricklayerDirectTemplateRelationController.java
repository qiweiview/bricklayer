package cn.bricklayer.controller;

import cn.bricklayer.model.dto.BricklayerDirectTemplateRelationDTO;
import cn.bricklayer.model.vo.BricklayerDirectTemplateRelationVO;
import cn.bricklayer.serviceI.BricklayerDirectTemplateRelationServiceI;
import cn.bricklayer.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/bricklayerDirectTemplateRelation")
public class BricklayerDirectTemplateRelationController {

    private final BricklayerDirectTemplateRelationServiceI bricklayerDirectTemplateRelationServiceI;

    @Autowired
    public BricklayerDirectTemplateRelationController(BricklayerDirectTemplateRelationServiceI bricklayerDirectTemplateRelationServiceI) {
    this.bricklayerDirectTemplateRelationServiceI = bricklayerDirectTemplateRelationServiceI;
    }

    /**
     * 创建BricklayerDirectTemplateRelation
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerDirectTemplateRelationVO> saveBricklayerDirectTemplateRelation(@RequestBody(required = false) BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectTemplateRelationDTO=bricklayerDirectTemplateRelationServiceI.saveBricklayerDirectTemplateRelation(bricklayerDirectTemplateRelationDTO);
        BricklayerDirectTemplateRelationVO bricklayerDirectTemplateRelationVO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationVO();
        responseVo.setData(bricklayerDirectTemplateRelationVO);
        return responseVo;
    }

    /**
     * 更新BricklayerDirectTemplateRelation
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerDirectTemplateRelationVO> updateBricklayerDirectTemplateRelation(@RequestBody(required = false) BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectTemplateRelationServiceI.updateBricklayerDirectTemplateRelation(bricklayerDirectTemplateRelationDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerDirectTemplateRelation
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerDirectTemplateRelationVO> deleteBricklayerDirectTemplateRelation(@RequestBody(required = false) BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectTemplateRelationDTO=bricklayerDirectTemplateRelationServiceI.deleteBricklayerDirectTemplateRelation(bricklayerDirectTemplateRelationDTO);
        BricklayerDirectTemplateRelationVO bricklayerDirectTemplateRelationVO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationVO();
        responseVo.setData(bricklayerDirectTemplateRelationVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerDirectTemplateRelation
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerDirectTemplateRelationVO> listBricklayerDirectTemplateRelationPage(@RequestBody(required = false) BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO) {
        ResponseVo responseVo=new ResponseVo();
        IPage<BricklayerDirectTemplateRelationDTO> bricklayerDirectTemplateRelationDTOIPage = bricklayerDirectTemplateRelationServiceI.listBricklayerDirectTemplateRelationPage(bricklayerDirectTemplateRelationDTO);
        IPage<BricklayerDirectTemplateRelationVO> rs = new Page(bricklayerDirectTemplateRelationDTOIPage.getCurrent(), bricklayerDirectTemplateRelationDTOIPage.getSize(), bricklayerDirectTemplateRelationDTOIPage.getTotal());
        rs.setRecords(BricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationVOList(bricklayerDirectTemplateRelationDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }




    /**
     * 根据主键获取BricklayerDirectTemplateRelation
     */
    @RequestMapping("/getById")
    public ResponseVo<BricklayerDirectTemplateRelationVO> getBricklayerDirectTemplateRelationById(@RequestBody(required = false) BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectTemplateRelationDTO=bricklayerDirectTemplateRelationServiceI.getBricklayerDirectTemplateRelationById(bricklayerDirectTemplateRelationDTO);
        BricklayerDirectTemplateRelationVO bricklayerDirectTemplateRelationVO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationVO();
        responseVo.setData(bricklayerDirectTemplateRelationVO);
        return responseVo;
        }




}