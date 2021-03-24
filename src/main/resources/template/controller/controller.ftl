${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

<#include "signature.ftl">
@RestController
@RequestMapping("${baseMapping}")
public class ${fullName} {
    @Autowired
    private ${innerService} ${innerService?uncap_first};

<#list methods as method>

    @RequestMapping("${method.path}")
    public GeneralResponseObject ${method.name}(@RequestBody(required = false) ${shortName}DTO ${shortName?uncap_first}) {
        GeneralResponseObject generalResponseObject=new GeneralResponseObject();
        <#if method.hasReturn>
        ${shortName}DTO ${shortName?uncap_first}DTO=${innerService?uncap_first}.${method.name}(${shortName?uncap_first});
        ${shortName}VO ${shortName?uncap_first}VO=${shortName?uncap_first}DTO.to${shortName}VO();
        generalResponseObject.setData(${shortName?uncap_first}VO);
            <#else>
        ${innerService?uncap_first}.${method.name}(${shortName?uncap_first});
        </#if>
        return generalResponseObject;
    }

</#list>


}