package cn.bricklayer.controller;

import cn.bricklayer.model.dto.BricklayerTableDTO;
import cn.bricklayer.model.vo.BricklayerTableVO;
import cn.bricklayer.serviceI.BricklayerTableServiceI;
import cn.bricklayer.utils.MessageRuntimeException;
import cn.bricklayer.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 根据主键获取BricklayerTable
     */
    //@ApiOperation(value = "根据主键获取BricklayerTable", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<BricklayerTableVO> getBricklayerTableById(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerTableDTO = bricklayerTableServiceI.getBricklayerTableById(bricklayerTableDTO);
        BricklayerTableVO bricklayerTableVO = bricklayerTableDTO.toBricklayerTableVO();
        responseVo.setData(bricklayerTableVO);
        return responseVo;
    }

    /**
     * 批量删除BricklayerTable
     */
    @RequestMapping("/deleteBatch")
    public ResponseVo<BricklayerTableVO> deleteBricklayerTableBatch(@RequestBody(required = false) BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerTableServiceI.deleteBricklayerTableBatch(bricklayerTableDTO);
        return responseVo;
    }

    /**
     * 导出
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping("importModel")
    public ResponseVo importModel(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            byte[] bytes = multipartFile.getBytes();
            bricklayerTableServiceI.importProject(bytes, originalFilename);
        } catch (IOException e) {
            throw new MessageRuntimeException("read import fail cause:" + e.getMessage());
        }

        return ResponseVo.success("导入成功");
    }


}