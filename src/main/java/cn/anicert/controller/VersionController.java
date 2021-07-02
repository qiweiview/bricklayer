package cn.anicert.controller;

import cn.anicert.model.vo.BricklayerProjectVO;
import cn.anicert.utils.ResponseVo;
import cn.anicert.utils.VersionTag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/version")
public class VersionController {


    /**
     * get the version
     */
    @RequestMapping("/getVersion")
    public ResponseVo<BricklayerProjectVO> saveBricklayerProject() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(VersionTag.VERSION);
        return responseVo;
    }


}