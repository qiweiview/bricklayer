package cn.bricklayer.controller;

import cn.bricklayer.model.vo.BricklayerProjectVO;
import cn.bricklayer.utils.ResponseVo;
import cn.bricklayer.utils.VersionTag;
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