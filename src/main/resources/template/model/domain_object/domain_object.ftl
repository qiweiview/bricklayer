${belongPackage}

<#list dependents as dependent>
    ${dependent}
</#list>

public class ${className} {

<#list attributes as attribute>
  private  ${attribute.type} ${attribute.name};
</#list>


<#list attributes as attribute>
    private   ${attribute.type}  get${attribute.name?cap_first}(){
    return ${attribute.name};
    }

    private   set${attribute.name?cap_first}(${attribute.name?cap_first} ${attribute.name?uncap_first}){
    this.${attribute.name}=${attribute.name?uncap_first}
    }
</#list>
}