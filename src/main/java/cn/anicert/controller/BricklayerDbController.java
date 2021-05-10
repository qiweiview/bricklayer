package cn.anicert.controller;


import cn.anicert.model.dto.BricklayerDbDTO;
import cn.anicert.model.dto.BricklayerTableDTO;
import cn.anicert.model.dto.GenerateCodeDTO;
import cn.anicert.model.dto.TableDetailDTO;
import cn.anicert.model.vo.BricklayerDbVO;
import cn.anicert.model.vo.GenerationVO;
import cn.anicert.serviceI.BricklayerDbServiceI;
import cn.anicert.utils.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * create by view
 */
@RestController
@RequestMapping("/bricklayerDb")
@RequiredArgsConstructor
public class BricklayerDbController {

    private final BricklayerDbServiceI bricklayerDbServiceI;


    /**
     * 创建BricklayerDb
     */
    //@ApiOperation(value = "创建BricklayerDb", httpMethod = "POST")
    @RequestMapping("/save")
    public ResponseVo<BricklayerDbVO> saveBricklayerDb(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbDTO = bricklayerDbServiceI.saveBricklayerDb(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO = bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
    }

    /**
     * 更新BricklayerDb
     */
    //@ApiOperation(value = "更新BricklayerDb", httpMethod = "POST")
    @RequestMapping("/update")
    public ResponseVo<BricklayerDbVO> updateBricklayerDb(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbServiceI.updateBricklayerDb(bricklayerDbDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerDb
     */
    //@ApiOperation(value = "删除BricklayerDb", httpMethod = "POST")
    @RequestMapping("/delete")
    public ResponseVo<BricklayerDbVO> deleteBricklayerDb(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbDTO = bricklayerDbServiceI.deleteBricklayerDb(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO = bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerDb
     */
    //@ApiOperation(value = "分页查询BricklayerDb", httpMethod = "POST")
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerDbVO> listBricklayerDbPage(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerDbDTO> bricklayerDbDTOIPage = bricklayerDbServiceI.listBricklayerDbPage(bricklayerDbDTO);
        IPage<BricklayerDbVO> rs = new Page(bricklayerDbDTOIPage.getCurrent(), bricklayerDbDTOIPage.getSize(), bricklayerDbDTOIPage.getTotal());
        rs.setRecords(BricklayerDbDTO.toBricklayerDbVOList(bricklayerDbDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 查询所有
     */
    //@ApiOperation(value = "查询所有", httpMethod = "POST")
    @RequestMapping("/listAll")
    public ResponseVo<BricklayerDbVO> listBricklayerDbAll() {
        ResponseVo responseVo = new ResponseVo();
        List<BricklayerDbDTO> bricklayerDbDTOIPage = bricklayerDbServiceI.listBricklayerDbAll();
        List<BricklayerDbVO> bricklayerDbVOS = BricklayerDbDTO.toBricklayerDbVOList(bricklayerDbDTOIPage);
        responseVo.setData(bricklayerDbVOS);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerDb
     */
    //@ApiOperation(value = "根据主键获取BricklayerDb", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<BricklayerDbVO> getBricklayerDbById(@RequestBody(required = false) BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbDTO = bricklayerDbServiceI.getBricklayerDbById(bricklayerDbDTO);
        BricklayerDbVO bricklayerDbVO = bricklayerDbDTO.toBricklayerDbVO();
        responseVo.setData(bricklayerDbVO);
        return responseVo;
    }



    /* ----------- 分割线 -------------- */

    /**
     * 获取数据库列表
     *
     * @param bricklayerDbDTO
     * @return
     */
    @RequestMapping("/getDataSourceList")
    public ResponseVo getDataSourceList(@RequestBody BricklayerDbDTO bricklayerDbDTO) {
        ResponseVo responseVo = new ResponseVo();
        List<String> dataSources = bricklayerDbServiceI.getDataSourceList(bricklayerDbDTO);
        responseVo.setData(dataSources);
        return responseVo;
    }

    /**
     * 获取表结构
     *
     * @param tableDetailDTO
     * @return
     */
    @RequestMapping("/getTableDetail")
    public ResponseVo getTableDetail(@RequestBody TableDetailDTO tableDetailDTO) {
        ResponseVo responseVo = new ResponseVo();
        BricklayerTableDTO tableDetail = bricklayerDbServiceI.getTableDetail(tableDetailDTO);
        responseVo.setData(tableDetail.getBricklayerColumnDTOList());
        return responseVo;
    }

    @RequestMapping("/getTables")
    public ResponseVo getTables(@RequestBody TableDetailDTO tableDetailDTO) {
        ResponseVo responseVo = new ResponseVo();
        List<String> tables = bricklayerDbServiceI.getTables(tableDetailDTO);
        responseVo.setData(tables);
        return responseVo;
    }

    /**
     * 创建单个模型
     *
     * @param bricklayerTableDTO
     * @return
     */
    @RequestMapping("/saveSingleModel")
    public ResponseVo saveSingleModel(@RequestBody BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbServiceI.saveSingleModel(bricklayerTableDTO);
        return responseVo;
    }

    /**
     * 创建单个模型
     *
     * @param bricklayerTableDTO
     * @return
     */
    @RequestMapping("/saveBatchModels")
    public ResponseVo saveBatchModels(@RequestBody BricklayerTableDTO bricklayerTableDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerDbServiceI.saveBatchModels(bricklayerTableDTO);
        return responseVo;
    }


    /**
     * 生成代码
     *
     * @param generateCodeDTO
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @RequestMapping("/generateCode")
    public ResponseVo generateCode(@RequestBody GenerateCodeDTO generateCodeDTO, HttpServletResponse httpServletResponse) throws IOException {
        GenerationVO generationVO = bricklayerDbServiceI.generateCode(generateCodeDTO);
        return ResponseVo.success(generationVO);
    }


}