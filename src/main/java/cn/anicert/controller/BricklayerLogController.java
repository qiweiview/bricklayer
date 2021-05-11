package cn.anicert.controller;

import cn.anicert.model.dto.BricklayerLogDTO;
import cn.anicert.model.eo.BricklayerLogEO;
import cn.anicert.model.vo.BricklayerLogVO;
import cn.anicert.serviceI.BricklayerLogServiceI;
import cn.anicert.utils.ResponseVo;
import cn.anicert.utils.exporter.ExcelExporter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/bricklayerLog")
@RequiredArgsConstructor
public class BricklayerLogController {

    private final BricklayerLogServiceI bricklayerLogServiceI;


    /**
     * 创建BricklayerLog
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerLogVO> saveBricklayerLog(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerLogDTO = bricklayerLogServiceI.saveBricklayerLog(bricklayerLogDTO);
        BricklayerLogVO bricklayerLogVO = bricklayerLogDTO.toBricklayerLogVO();
        responseVo.setData(bricklayerLogVO);
        return responseVo;
    }

    /**
     * 更新BricklayerLog
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerLogVO> updateBricklayerLog(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerLogServiceI.updateBricklayerLog(bricklayerLogDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerLog
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerLogVO> deleteBricklayerLog(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerLogDTO = bricklayerLogServiceI.deleteBricklayerLog(bricklayerLogDTO);
        BricklayerLogVO bricklayerLogVO = bricklayerLogDTO.toBricklayerLogVO();
        responseVo.setData(bricklayerLogVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerLog
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerLogVO> listBricklayerLogPage(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerLogDTO> bricklayerLogDTOIPage = bricklayerLogServiceI.listBricklayerLogPage(bricklayerLogDTO);
        IPage<BricklayerLogVO> rs = new Page(bricklayerLogDTOIPage.getCurrent(), bricklayerLogDTOIPage.getSize(), bricklayerLogDTOIPage.getTotal());
        rs.setRecords(BricklayerLogDTO.toBricklayerLogVOList(bricklayerLogDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerLog
     */
    @RequestMapping("/getById")
    public ResponseVo<BricklayerLogVO> getBricklayerLogById(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerLogDTO = bricklayerLogServiceI.getBricklayerLogById(bricklayerLogDTO);
        BricklayerLogVO bricklayerLogVO = bricklayerLogDTO.toBricklayerLogVO();
        responseVo.setData(bricklayerLogVO);
        return responseVo;
    }

    /**
     * 批量删除BricklayerLog
     */
    @RequestMapping("/deleteBatch")
    public ResponseVo<BricklayerLogVO> deleteBricklayerLogBatch(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerLogServiceI.deleteBricklayerLogBatch(bricklayerLogDTO);
        return responseVo;
    }


    /**
     * 批量导出BricklayerLog
     */
    @RequestMapping("/exportBatch")
    public ResponseVo<BricklayerLogVO> exportBricklayerLogBatch(@RequestBody(required = false) BricklayerLogDTO bricklayerLogDTO, HttpServletResponse httpServletResponse) {
        ResponseVo responseVo = new ResponseVo();
        List<BricklayerLogDTO> list = bricklayerLogServiceI.listBricklayerLogByIds(bricklayerLogDTO);

        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.addSheet(list, BricklayerLogEO.class);
        byte[] bytes = excelExporter.list2Excel();


        httpServletResponse.reset();
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + "export.xlsx" + "\"");
        httpServletResponse.setHeader("Set-Cookie", "fileDownload=true; path=/");
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");

        try {
            ServletOutputStream out = httpServletResponse.getOutputStream();
            out.write(bytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseVo;
    }


}