



<#include "signature.ftl">
public class ${className} {
<#list fieldList as field>


    private  ${field.javaType} ${field.beanName};
</#list>


/*  ------------ data conversion ------------  */


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