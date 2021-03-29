${contextModel.daoPackage}

import ${contextModel.daoPath};
import ${contextModel.doPath};
import org.apache.ibatis.annotations.Mapper;

<#include "signature.ftl">
@Mapper
public interface ${contextModel.daoName} {

    public  ${contextModel.doName}   save${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   update${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   delete${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  Object   list${className}Page(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   get${className}ById(${contextModel.doName} ${contextModel.doName?uncap_first});
}