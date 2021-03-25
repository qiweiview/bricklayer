${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
<#include "signature.ftl">

@Service
public class ${fullName} implements ${interfaceName}{

    private final ${innerDao} ${innerDao?uncap_first};

    @Autowired
    public ${fullName}(${innerDao} ${innerDao?uncap_first}) {
    this.${innerDao?uncap_first} = ${innerDao?uncap_first};
    }


<#list methods as method>

    @Override
    ${method.description}{

     ${shortName}${suffixManager.dOSuffix} ${shortName?uncap_first}=${shortName?uncap_first}${suffixManager.dTOSuffix}.to${shortName}${suffixManager.dOSuffix}();

     <#if method.description?contains("save")||method.description?contains("update")>
     ${innerDao?uncap_first}.save(${shortName?uncap_first});
     </#if>
     <#if method.description?contains("count")>
     long count = ${innerDao?uncap_first}.count();
     </#if>
     <#if method.description?contains("remove")>
      ${innerDao?uncap_first}.delete(${shortName?uncap_first});
     </#if>
     <#if method.description?contains("listAll")>
     ${innerDao?uncap_first}.findAll();
     </#if>
     <#if method.hasReturn>
     return null;
        <#else >
    </#if>
    }



</#list>

    /**
    * jpa 分页
    * @param ${shortName?uncap_first}${suffixManager.dTOSuffix}
    * @return
    */
    public  Page<${shortName}${suffixManager.dOSuffix}>  listByPage(${shortName}${suffixManager.dTOSuffix} ${shortName?uncap_first}${suffixManager.dTOSuffix}) {

    Pageable pageable = PageRequest.of(${shortName?uncap_first}${suffixManager.dTOSuffix}.getPageNumber(), ${shortName?uncap_first}${suffixManager.dTOSuffix}.getPageSize());
    Page<${shortName}${suffixManager.dOSuffix}> all = ${innerDao?uncap_first}.findAll(pageable);
    return all;

    }
}