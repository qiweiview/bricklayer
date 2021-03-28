${contextModel.serviceImplPackage}

import ${contextModel.doPath};
import ${contextModel.daoPath};
import ${contextModel.dtoPath};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${contextModel.serviceIPath};
<#include "signature.ftl">

@Service
public class ${contextModel.serviceImplName} implements ${contextModel.serviceIName}{

    private final ${contextModel.daoName} ${contextModel.daoName?uncap_first};

    @Autowired
    public ${contextModel.serviceImplName}(${contextModel.daoName} ${contextModel.daoName?uncap_first}){
        this.${contextModel.daoName?uncap_first} =${contextModel.daoName?uncap_first};
        }

    public  ${contextModel.dtoName}   save${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.daoName?uncap_first}.save${className}(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

    public  ${contextModel.dtoName}   update${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.daoName?uncap_first}.update${className}(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

    public  ${contextModel.dtoName}   delete${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.daoName?uncap_first}.delete${className}(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

    public  Object list${className}Page(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        //todo fix point
        return null;
        }

    public  ${contextModel.dtoName}   get${className}ById(${contextModel.dtoName} ${contextModel.dtoName?uncap_first}){
        ${contextModel.doName} ${contextModel.doName?uncap_first}=${contextModel.dtoName?uncap_first}.to${contextModel.doName}();
        ${contextModel.doName?uncap_first}=${contextModel.daoName?uncap_first}.get${className}ById(${contextModel.doName?uncap_first});
        return ${contextModel.doName?uncap_first}.to${contextModel.dtoName}();
        }

}