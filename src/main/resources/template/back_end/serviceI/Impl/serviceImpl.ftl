${contextModel.serviceImplPackage}

import ${contextModel.doPath};
import ${contextModel.daoPath};
import ${contextModel.dtoPath};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${contextModel.serviceIPath};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import ${contextModel.utilsBase}.DataNotFoundException;
//import cn.anicert.university.common.exception.EntityNotFoundException;

<#include "signature.ftl">

@Service
public class ${contextModel.serviceImplName} implements ${contextModel.serviceIName}{

    private final ${contextModel.daoName} ${contextModel.daoName?uncap_first};

    @Autowired
    public ${contextModel.serviceImplName}(${contextModel.daoName} ${contextModel.daoName?uncap_first}){
        this.${contextModel.daoName?uncap_first} =${contextModel.daoName?uncap_first};
        }

    @Override
    public  ${contextModel.dtoName}   save${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.doName?uncap_first}.doInit();
        ${contextModel.daoName?uncap_first}.insert(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

    @Override
    public  ${contextModel.dtoName}   update${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.dtoName?uncap_first}=get${className}ById(${contextModel.dtoName?uncap_first});
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.doName?uncap_first}.doUpdate();
        ${contextModel.daoName?uncap_first}.updateById(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

    @Override
    public  ${contextModel.dtoName}   delete${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.dtoName?uncap_first}=get${className}ById(${contextModel.dtoName?uncap_first});
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.doName?uncap_first}.doDelete();
        ${contextModel.daoName?uncap_first}.delete${className}(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

    @Override
    public  IPage<${contextModel.dtoName}> list${className}Page(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        Page page = new Page(${contextModel.dtoName?uncap_first}.getCurrent(), ${contextModel.dtoName?uncap_first}.getSize());
        IPage<${contextModel.doName}> pageResult = ${contextModel.daoName?uncap_first}.list${className}Page(page, ${contextModel.doName?uncap_first});
        List<${contextModel.doName}> records = pageResult.getRecords();
        List<${contextModel.dtoName}> list = ${contextModel.doName}.to${contextModel.dtoName}List(records);
        IPage<${contextModel.dtoName}> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
        }

    @Override
    public  ${contextModel.dtoName}   get${className}ById(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.doName?uncap_first}=${contextModel.daoName?uncap_first}.get${className}ById(${contextModel.doName?uncap_first});
        if(${contextModel.doName?uncap_first}==null){
            throw new DataNotFoundException();
        }
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

}