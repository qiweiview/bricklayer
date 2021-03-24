${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>

<#include "/signature.ftl">
public class ${fullName} {
<#list attributes as attribute>
    private  ${attribute.type} ${attribute.name};
</#list>


/*  ------------ data conversion ------------  */
public  ${shortName}DO to${shortName}DO(){
    ${shortName}DO ${shortName?uncap_first}DO =new ${shortName}DO();
<#list attributes as attribute>
    ${shortName?uncap_first}DO.set${attribute.name?cap_first}(get${attribute.name?cap_first}());
</#list>
    return ${shortName?uncap_first}DO;
}

public  ${shortName}VO to${shortName}VO(){
${shortName}VO ${shortName?uncap_first}VO =new ${shortName}VO();
<#list attributes as attribute>
    ${shortName?uncap_first}VO.set${attribute.name?cap_first}(get${attribute.name?cap_first}());
</#list>
return ${shortName?uncap_first}VO;
}

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