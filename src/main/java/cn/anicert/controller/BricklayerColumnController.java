package cn.anicert.controller;

import cn.anicert.model.dto.BricklayerColumnDTO;
import cn.anicert.model.vo.BricklayerColumnVO;
import cn.anicert.serviceI.BricklayerColumnServiceI;
import cn.anicert.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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



    @RequestMapping("/getBricklayerColumnsByBelongTableId")
    public ResponseVo<BricklayerColumnVO> getBricklayerColumnsByBelongTableId(@RequestBody(required = false) BricklayerColumnDTO bricklayerColumnDTO) {
        ResponseVo responseVo=new ResponseVo();
        List<BricklayerColumnDTO> bricklayerColumnDTOS=bricklayerColumnServiceI.getBricklayerColumnsByBelongTableId(bricklayerColumnDTO);
        List<BricklayerColumnVO> bricklayerColumnVOS = BricklayerColumnDTO.toBricklayerColumnVOList(bricklayerColumnDTOS);
        responseVo.setData(bricklayerColumnVOS);
        return responseVo;
    }



}