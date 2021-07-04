package cn.bricklayer.controller;

import cn.bricklayer.model.dto.BricklayerProjectDTO;
import cn.bricklayer.model.dto.GenerateCodeDTO;
import cn.bricklayer.model.vo.BricklayerProjectVO;
import cn.bricklayer.model.vo.GenerationVO;
import cn.bricklayer.serviceI.BricklayerProjectServiceI;
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
     * 更新BricklayerProject
     */
    @RequestMapping("/copy")
    public ResponseVo<BricklayerProjectVO> copyBricklayerProject(@RequestBody(required = false) BricklayerProjectDTO bricklayerProjectDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerProjectServiceI.copyBricklayerProject(bricklayerProjectDTO);
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


    /**
     * 导出
     *
     * @param generateCodeDTO
     * @return
     * @throws IOException
     */
    @RequestMapping("/exportProject")
    public ResponseVo exportProject(@RequestBody GenerateCodeDTO generateCodeDTO) throws IOException {
        GenerationVO generationVO = bricklayerProjectServiceI.exportProject(generateCodeDTO);
        return ResponseVo.success(generationVO);
    }


    /**
     * 导出
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping("importProject")
    public ResponseVo importProject(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            bricklayerProjectServiceI.importProject(bytes);
        } catch (IOException e) {
            throw new MessageRuntimeException("read import fail cause:" + e.getMessage());
        }

        return ResponseVo.success("导入成功");
    }


}