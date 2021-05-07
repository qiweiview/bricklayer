package cn.anicert.controller;

import cn.anicert.model.dto.BricklayerDirectDTO;
import cn.anicert.model.vo.BricklayerDirectVO;
import cn.anicert.serviceI.BricklayerDirectServiceI;
import cn.anicert.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/bricklayerDirect")
public class BricklayerDirectController {

    private final BricklayerDirectServiceI bricklayerDirectServiceI;

    @Autowired
    public BricklayerDirectController(BricklayerDirectServiceI bricklayerDirectServiceI) {
    this.bricklayerDirectServiceI = bricklayerDirectServiceI;
    }

    /**
     * 创建BricklayerDirect
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerDirectVO> saveBricklayerDirect(@RequestBody(required = false) BricklayerDirectDTO bricklayerDirectDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectDTO=bricklayerDirectServiceI.saveBricklayerDirect(bricklayerDirectDTO);
        BricklayerDirectVO bricklayerDirectVO=bricklayerDirectDTO.toBricklayerDirectVO();
        responseVo.setData(bricklayerDirectVO);
        return responseVo;
    }

    /**
     * 更新BricklayerDirect
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerDirectVO> updateBricklayerDirect(@RequestBody(required = false) BricklayerDirectDTO bricklayerDirectDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectServiceI.updateBricklayerDirect(bricklayerDirectDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerDirect
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerDirectVO> deleteBricklayerDirect(@RequestBody(required = false) BricklayerDirectDTO bricklayerDirectDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectDTO=bricklayerDirectServiceI.deleteBricklayerDirect(bricklayerDirectDTO);
        BricklayerDirectVO bricklayerDirectVO=bricklayerDirectDTO.toBricklayerDirectVO();
        responseVo.setData(bricklayerDirectVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerDirect
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerDirectVO> listBricklayerDirectPage(@RequestBody(required = false) BricklayerDirectDTO bricklayerDirectDTO) {
        ResponseVo responseVo=new ResponseVo();
        IPage<BricklayerDirectDTO> bricklayerDirectDTOIPage = bricklayerDirectServiceI.listBricklayerDirectPage(bricklayerDirectDTO);
        IPage<BricklayerDirectVO> rs = new Page(bricklayerDirectDTOIPage.getCurrent(), bricklayerDirectDTOIPage.getSize(), bricklayerDirectDTOIPage.getTotal());
        rs.setRecords(BricklayerDirectDTO.toBricklayerDirectVOList(bricklayerDirectDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }




    /**
     * 根据主键获取BricklayerDirect
     */
    @RequestMapping("/getById")
    public ResponseVo<BricklayerDirectVO> getBricklayerDirectById(@RequestBody(required = false) BricklayerDirectDTO bricklayerDirectDTO) {
        ResponseVo responseVo=new ResponseVo();
        bricklayerDirectDTO=bricklayerDirectServiceI.getBricklayerDirectById(bricklayerDirectDTO);
        BricklayerDirectVO bricklayerDirectVO=bricklayerDirectDTO.toBricklayerDirectVO();
        responseVo.setData(bricklayerDirectVO);
        return responseVo;
        }




}