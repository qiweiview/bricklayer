${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import javax.persistence.*;

<#include "signature.ftl">
@Entity
@Table(name="${shortName?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}")
public class ${fullName} {
<#list attributes as attribute>

    /**
    * ${attribute.comment}
    */
    <#if attribute.columnKey=="PRI">
    @Id
        <#if attribute.extra=="auto_increment">
    @GeneratedValue(strategy=GenerationType.IDENTITY)
        </#if>
    <#else>
    // @Column(name="${attribute.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}", updatable = false)
    </#if>
    private  ${attribute.type} ${attribute.name};


</#list>


/*  ------------ data conversion ------------  */
public  ${shortName}${suffixManager.dTOSuffix} to${shortName}${suffixManager.dTOSuffix}(){
${shortName}${suffixManager.dTOSuffix} ${shortName?uncap_first}${suffixManager.dTOSuffix} =new ${shortName}${suffixManager.dTOSuffix}();
<#list attributes as attribute>
    ${shortName?uncap_first}${suffixManager.dTOSuffix}.set${attribute.name?cap_first}(get${attribute.name?cap_first}());
</#list>
return ${shortName?uncap_first}${suffixManager.dTOSuffix};
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