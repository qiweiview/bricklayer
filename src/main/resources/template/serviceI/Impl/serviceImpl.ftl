${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

<#include "signature.ftl">

@Service
public class ${fullName} implements ${interfaceName}{

    @Autowired
    private ${innerDao} ${innerDao?uncap_first};

<#list methods as method>

    @Override
    ${method.description}{
     ${shortName}DO ${shortName?uncap_first}=${shortName?uncap_first}DTO.to${shortName}DO();
     <#if method.hasReturn>
     ${shortName}DO ${shortName?uncap_first}DOResult=${innerDao?uncap_first}.${method.name}${shortName}(${shortName?uncap_first});
     ${shortName}DTO ${shortName?uncap_first}DTOResult=${shortName?uncap_first}DOResult.to${shortName}DTO();
     return ${shortName?uncap_first}DTOResult;
        <#else >
     ${innerDao?uncap_first}.${method.name}${shortName}(${shortName?uncap_first});
    </#if>
    }

</#list>
}