${contextModel.controllerPackage}

import ${contextModel.dtoPath};
import ${contextModel.voPath};
import ${contextModel.serviceIPath};
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${contextModel.utilsBase}.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;
//import cn.anicert.university.constant.ErrorEnum;
//import cn.anicert.university.common.entity.dto.ResponseVo;

<#include "signature.ftl">
@RestController
@RequestMapping("/${className?uncap_first}")
public class ${contextModel.controllerName} {

    private final ${contextModel.serviceIName} ${contextModel.serviceIName?uncap_first};

    @Autowired
    public ${contextModel.controllerName}(${contextModel.serviceIName} ${contextModel.serviceIName?uncap_first}) {
    this.${contextModel.serviceIName?uncap_first} = ${contextModel.serviceIName?uncap_first};
    }

    /**
     * 创建${className}
     */
    //@ApiOperation(value = "创建${className}", httpMethod = "POST")
    @RequestMapping("/save")
    public ResponseVo<${contextModel.voName}> save${className}(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.save${className}(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        responseVo.setData(${contextModel.voName?uncap_first});
        return responseVo;
    }

    /**
     * 更新${className}
     */
    //@ApiOperation(value = "更新${className}", httpMethod = "POST")
    @RequestMapping("/update")
    public ResponseVo<${contextModel.voName}> update${className}(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.serviceIName?uncap_first}.update${className}(${contextModel.dtoName?uncap_first});
        return responseVo;
    }

    /**
     * 删除${className}
     */
    //@ApiOperation(value = "删除${className}", httpMethod = "POST")
    @RequestMapping("/delete")
    public ResponseVo<${contextModel.voName}> delete${className}(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.delete${className}(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        responseVo.setData(${contextModel.voName?uncap_first});
        return responseVo;
    }

    /**
     * 分页查询${className}
     */
    //@ApiOperation(value = "分页查询${className}", httpMethod = "POST")
    @RequestMapping("/listPage")
    public ResponseVo<${contextModel.voName}> list${className}Page(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        IPage<${contextModel.dtoName}> ${contextModel.dtoName?uncap_first}IPage = ${contextModel.serviceIName?uncap_first}.list${className}Page(${contextModel.dtoName?uncap_first});
        IPage<${contextModel.voName}> rs = new Page(${contextModel.dtoName?uncap_first}IPage.getCurrent(), ${contextModel.dtoName?uncap_first}IPage.getSize(), ${contextModel.dtoName?uncap_first}IPage.getTotal());
        rs.setRecords(${contextModel.dtoName}.to${contextModel.voName}List(${contextModel.dtoName?uncap_first}IPage.getRecords()));
        responseVo.setData(rs);
        return responseVo;
    }



    /**
     * 导出${className}
     */
    //@ApiOperation(value = "导出${className}", httpMethod = "POST")
    /* @RequestMapping("/export")
    public ResponseVo<${contextModel.voName}> list${className}Page(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}, HttpServletResponse httpServletResponse) {
        ResponseVo responseVo=new ResponseVo();
        List<${contextModel.dtoName}> ${contextModel.dtoName?uncap_first}List = ${contextModel.serviceIName?uncap_first}.list${className}(${contextModel.dtoName?uncap_first});
        List<${contextModel.voName}> ${contextModel.voName?uncap_first} = ${contextModel.dtoName}.to${contextModel.voName}List(${contextModel.dtoName?uncap_first}List);

        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.addSheet(trainingDetailVOS, ${contextModel.voName}.class);
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
        }*/


    /**
     * 根据主键获取${className}
     */
    //@ApiOperation(value = "根据主键获取${className}", httpMethod = "POST")
    @RequestMapping("/getById")
    public ResponseVo<${contextModel.voName}> get${className}ById(@RequestBody(required = false) ${contextModel.dtoName} ${contextModel.dtoName?uncap_first}) {
        ResponseVo responseVo=new ResponseVo();
        ${contextModel.dtoName?uncap_first}=${contextModel.serviceIName?uncap_first}.get${className}ById(${contextModel.dtoName?uncap_first});
        ${contextModel.voName} ${contextModel.voName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.voName}();
        responseVo.setData(${contextModel.voName?uncap_first});
        return responseVo;
        }




}