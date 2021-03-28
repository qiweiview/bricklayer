${contextModel.serviceIPackage}

import ${contextModel.dtoPath};

<#include "signature.ftl">
public interface ${contextModel.serviceIName} {

    public  ${contextModel.dtoName}   save${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  ${contextModel.dtoName}   update${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  ${contextModel.dtoName}   delete${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  Object   list${className}Page(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  ${contextModel.dtoName}   get${className}ById(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});
}