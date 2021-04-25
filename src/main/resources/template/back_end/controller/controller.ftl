package ${basePath};

import com.model.dto.${className}DTO;
import com.model.dto.${className}VO;
import com.serviceI.${className}ServiceI;
import com.utils.ResponseVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/${className?uncap_first}")
@RequiredArgsConstructor
public class ${className}Controller {

private final ${className}ServiceI ${className?uncap_first}ServiceI;


/**
* 创建${className}
*/
@RequestMapping("/save")
public ResponseVo
<${className}VO> save${className}(@RequestBody(required = false) ${className}DTO ${className?uncap_first}DTO) {
    ResponseVo responseVo=new ResponseVo();
    ${className?uncap_first}DTO=${className?uncap_first}ServiceI.save${className}(${className?uncap_first}DTO);
    ${className}VO ${className?uncap_first}VO=${className?uncap_first}DTO.to${className}VO();
    responseVo.setData(${className?uncap_first}VO);
    return responseVo;
    }

    /**
    * 更新${className}
    */
    @RequestMapping("/update")
    public ResponseVo
    <${className}VO> update${className}(@RequestBody(required = false) ${className}DTO ${className?uncap_first}DTO) {
        ResponseVo responseVo=new ResponseVo();
        ${className?uncap_first}ServiceI.update${className}(${className?uncap_first}DTO);
        return responseVo;
        }

        /**
        * 删除${className}
        */
        @RequestMapping("/delete")
        public ResponseVo
        <${className}VO> delete${className}(@RequestBody(required = false) ${className}DTO ${className?uncap_first}DTO)
            {
            ResponseVo responseVo=new ResponseVo();
            ${className?uncap_first}DTO=${className?uncap_first}ServiceI.delete${className}(${className?uncap_first}
            DTO);
            ${className}VO ${className?uncap_first}VO=${className?uncap_first}DTO.to${className}VO();
            responseVo.setData(${className?uncap_first}VO);
            return responseVo;
            }

            /**
            * 分页查询${className}
            */
            @RequestMapping("/listPage")
            public ResponseVo
            <${className}VO> list${className}Page(@RequestBody(required = false) ${className}
                DTO ${className?uncap_first}DTO) {
                ResponseVo responseVo=new ResponseVo();
                IPage
                <${className}DTO> ${className?uncap_first}DTOIPage = ${className?uncap_first}ServiceI.list${className}
                    Page(${className?uncap_first}DTO);
                    IPage
                    <${className}VO> rs = new Page(${className?uncap_first}
                        DTOIPage.getCurrent(), ${className?uncap_first}DTOIPage.getSize(), ${className?uncap_first}
                        DTOIPage.getTotal());
                        rs.setRecords(${className}DTO.to${className}VOList(${className?uncap_first}
                        DTOIPage.getRecords()));
                        responseVo.setData(rs);
                        return responseVo;
                        }


                        /**
                        * 根据主键获取${className}
                        */
                        @RequestMapping("/getById")
                        public ResponseVo
                        <${className}VO> get${className}ById(@RequestBody(required = false) ${className}
                            DTO ${className?uncap_first}DTO) {
                            ResponseVo responseVo=new ResponseVo();
                            ${className?uncap_first}DTO= ${className?uncap_first}ServiceI.get${className}
                            ById(${className?uncap_first}DTO);
                            ${className}VO ${className?uncap_first}VO=${className?uncap_first}DTO.to${className}VO();
                            responseVo.setData(${className?uncap_first}VO);
                            return responseVo;
                            }


                            }