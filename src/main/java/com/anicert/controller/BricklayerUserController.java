package com.anicert.controller;

import com.anicert.model.dto.BricklayerUserDTO;
import com.anicert.model.vo.BricklayerUserVO;
import com.anicert.serviceI.BricklayerUserServiceI;
import com.anicert.utils.ResponseVo;
import com.management.utils.MessageRuntimeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/bricklayerUser")
@RequiredArgsConstructor
public class BricklayerUserController {

    private final BricklayerUserServiceI bricklayerUserServiceI;


    /**
     * 创建BricklayerUser
     */
    @RequestMapping("/save")
    public ResponseVo<BricklayerUserVO> saveBricklayerUser(@RequestBody(required = false) BricklayerUserDTO bricklayerUserDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerUserDTO = bricklayerUserServiceI.saveBricklayerUser(bricklayerUserDTO);
        BricklayerUserVO bricklayerUserVO = bricklayerUserDTO.toBricklayerUserVO();
        responseVo.setData(bricklayerUserVO);
        return responseVo;
    }

    /**
     * 更新BricklayerUser
     */
    @RequestMapping("/update")
    public ResponseVo<BricklayerUserVO> updateBricklayerUser(@RequestBody(required = false) BricklayerUserDTO bricklayerUserDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerUserServiceI.updateBricklayerUser(bricklayerUserDTO);
        return responseVo;
    }

    /**
     * 删除BricklayerUser
     */
    @RequestMapping("/delete")
    public ResponseVo<BricklayerUserVO> deleteBricklayerUser(@RequestBody(required = false) BricklayerUserDTO bricklayerUserDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerUserDTO = bricklayerUserServiceI.deleteBricklayerUser(bricklayerUserDTO);
        BricklayerUserVO bricklayerUserVO = bricklayerUserDTO.toBricklayerUserVO();
        responseVo.setData(bricklayerUserVO);
        return responseVo;
    }

    /**
     * 分页查询BricklayerUser
     */
    @RequestMapping("/listPage")
    public ResponseVo<BricklayerUserVO> listBricklayerUserPage(@RequestBody(required = false) BricklayerUserDTO bricklayerUserDTO) {
        ResponseVo responseVo = new ResponseVo();
        IPage<BricklayerUserDTO> bricklayerUserDTOIPage = bricklayerUserServiceI.listBricklayerUserPage(bricklayerUserDTO);
        IPage<BricklayerUserVO> rs = new Page(bricklayerUserDTOIPage.getCurrent(), bricklayerUserDTOIPage.getSize(), bricklayerUserDTOIPage.getTotal());
        rs.setRecords(BricklayerUserDTO.toBricklayerUserVOList(bricklayerUserDTOIPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerUser
     */
    @RequestMapping("/getById")
    public ResponseVo<BricklayerUserVO> getBricklayerUserById(@RequestBody(required = false) BricklayerUserDTO bricklayerUserDTO) {
        ResponseVo responseVo = new ResponseVo();
        bricklayerUserDTO = bricklayerUserServiceI.getBricklayerUserById(bricklayerUserDTO);
        BricklayerUserVO bricklayerUserVO = bricklayerUserDTO.toBricklayerUserVO();
        responseVo.setData(bricklayerUserVO);
        return responseVo;
    }


    /**
     * 根据主键获取BricklayerUser
     */
    @RequestMapping("/doLogin")
    public ResponseVo<BricklayerUserVO> doLogin(@RequestBody(required = false) BricklayerUserDTO bricklayerUserDTO) {
        ResponseVo responseVo = new ResponseVo();

        BricklayerUserDTO fromDb = bricklayerUserServiceI.getBricklayerUserByName(bricklayerUserDTO);


        if (fromDb == null || !fromDb.getUserPassword().equals(bricklayerUserDTO.getUserPassword())) {
            throw new MessageRuntimeException("密码错误");
        }

        BricklayerUserVO bricklayerUserVO = bricklayerUserDTO.toBricklayerUserVO();
        responseVo.setData(bricklayerUserVO.secrete());
        return responseVo;
    }


}