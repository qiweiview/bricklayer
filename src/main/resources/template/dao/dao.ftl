${contextModel.daoPackage}

import ${contextModel.daoPath};
import ${contextModel.doPath};
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

<#include "signature.ftl">
@Mapper
public interface ${contextModel.daoName} {

    public  ${contextModel.doName}   save${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   update${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   delete${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  IPage<${contextModel.doName}>   list${className}Page(Page page,${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   get${className}ById(${contextModel.doName} ${contextModel.doName?uncap_first});
}