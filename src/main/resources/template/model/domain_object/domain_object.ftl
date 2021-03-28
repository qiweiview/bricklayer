${contextModel.doPackage}

import ${contextModel.dtoPath};

<#include "signature.ftl">
public class ${contextModel.doName} {
<#list fieldList as field>

    private  ${field.javaType} ${field.beanName};
</#list>


/*  ------------ data conversion ------------  */
public  ${contextModel.dtoName} to${contextModel.dtoName}(){
${contextModel.dtoName} ${contextModel.dtoName?uncap_first} =new ${contextModel.dtoName}();
<#list fieldList as field>
    ${contextModel.dtoName?uncap_first}.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
        return ${contextModel.dtoName?uncap_first};

        }

/*  ------------ getter setter ------------  */
<#list fieldList as field>
    public ${field.javaType} get${field.beanName?cap_first}(){
    return ${field.beanName};
    }

    public void set${field.beanName?cap_first}(${field.javaType} ${field.beanName?uncap_first}){
    this.${field.beanName}=${field.beanName?uncap_first};
    }
</#list>
}