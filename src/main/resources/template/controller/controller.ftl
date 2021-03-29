${contextModel.controllerPackage}

import ${contextModel.dtoPath};
import ${contextModel.voPath};
import ${contextModel.serviceIPath};
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import cn.anicert.university.common.entity.dto.ResponseVo;

<#include "signature.ftl">
@RestController
@RequestMapping("/${contextModel.controllerName?uncap_first}")
public class ${contextModel.controllerName} {

    private final ${contextModel.serviceIName} ${contextModel.serviceIName?uncap_first};

    @Autowired
    public ${contextModel.controllerName}(${contextModel.serviceIName} ${contextModel.serviceIName?uncap_first}) {
    this.${contextModel.serviceIName?uncap_first} = ${contextModel.serviceIName?uncap_first};
    }



    @RequestMapping("/save${className}")
    public Object save${className}(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.save${className}(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        return responseVo;
    }

    @RequestMapping("/update${className}")
    public Object update${className}(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.update${className}(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        return responseVo;
    }

    @RequestMapping("/delete${className}")
    public Object delete${className}(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.delete${className}(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        return responseVo;
    }

    @RequestMapping("/list${className}Page")
    public Object list${className}Page(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.serviceIName?uncap_first}.list${className}Page(${contextModel.dtoName?uncap_first});
        return responseVo;
    }


    @RequestMapping("/get${className}ById")
    public Object get${className}ById(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.get${className}ById(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        return responseVo;
        }



}