${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>

<#include "signature.ftl">
public class ${fullName} {
<#list attributes as attribute>

    /**
    * ${attribute.comment}
    */
    private  ${attribute.type} ${attribute.name};
</#list>




/*  ------------ getter setter ------------  */
<#list attributes as attribute>
    public ${attribute.type} get${attribute.name?cap_first}(){
    return ${attribute.name};
    }

    public void set${attribute.name?cap_first}(${attribute.type} ${attribute.name?uncap_first}){
    this.${attribute.name}=${attribute.name?uncap_first};
    }
</#list>
}