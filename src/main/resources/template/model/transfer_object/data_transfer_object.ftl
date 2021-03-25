${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>

<#include "/signature.ftl">
public class ${fullName} {
<#list attributes as attribute>

    /**
    * ${attribute.comment}
    */
    private  ${attribute.type} ${attribute.name};
</#list>

    private int pageNumber;

    private int pageSize;

    private int totalNumber;

/*  ------------ data conversion ------------  */
public  ${shortName}${suffixManager.dOSuffix} to${shortName}${suffixManager.dOSuffix}(){
    ${shortName}${suffixManager.dOSuffix} ${shortName?uncap_first}${suffixManager.dOSuffix} =new ${shortName}${suffixManager.dOSuffix}();
<#list attributes as attribute>
    ${shortName?uncap_first}${suffixManager.dOSuffix}.set${attribute.name?cap_first}(get${attribute.name?cap_first}());
</#list>
    return ${shortName?uncap_first}${suffixManager.dOSuffix};
}

public  ${shortName}${suffixManager.vOSuffix} to${shortName}${suffixManager.vOSuffix}(){
${shortName}${suffixManager.vOSuffix} ${shortName?uncap_first}${suffixManager.vOSuffix} =new ${shortName}${suffixManager.vOSuffix}();
<#list attributes as attribute>
    <#if attribute!="page"||attribute!="size"||attribute!="total">
    ${shortName?uncap_first}${suffixManager.vOSuffix}.set${attribute.name?cap_first}(get${attribute.name?cap_first}());
    </#if>
</#list>
return ${shortName?uncap_first}${suffixManager.vOSuffix};
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


    public int getPageNumber() {
    return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
    }

    public int getPageSize() {
    return pageSize;
    }

    public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
    }

    public int getTotalNumber() {
    return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
    this.totalNumber = totalNumber;
    }

}