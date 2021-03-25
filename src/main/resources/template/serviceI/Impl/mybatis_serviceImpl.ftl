${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
     <#if method.hasReturn>
     ${shortName}${suffixManager.dOSuffix} ${shortName?uncap_first}DOResult=${innerDao?uncap_first}.${method.name}${shortName}(${shortName?uncap_first});
     ${shortName}${suffixManager.dTOSuffix} ${shortName?uncap_first}DTOResult=${shortName?uncap_first}DOResult.to${shortName}${suffixManager.dTOSuffix}();
     return ${shortName?uncap_first}DTOResult;
        <#else >
     ${innerDao?uncap_first}.${method.name}${shortName}(${shortName?uncap_first});
    </#if>
    }

</#list>
}