${belongPackage}

<#list dependents as dependent>
    ${dependent}
</#list>
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

<#include "signature.ftl">
@RestController
@RequestMapping("${baseMapping}")
public class ${fullName} {

    private final ${innerService} ${innerService?uncap_first};

    @Autowired
    public ${fullName}(${innerService} ${innerService?uncap_first}) {
    this.${innerService?uncap_first} = ${innerService?uncap_first};
    }

<#list methods as method>

    @RequestMapping("${method.path}")
    public ResponseEntity<Object> ${method.name}(@RequestBody(required = false) ${shortName}${suffixManager.dTOSuffix} ${shortName?uncap_first}) {
        ResponseEntity responseEntity;
    <#if method.hasReturn>
        ${shortName}${suffixManager.dTOSuffix} ${shortName?uncap_first}${suffixManager.dTOSuffix}=${innerService?uncap_first}.${method.name}(${shortName?uncap_first});
        ${shortName}${suffixManager.vOSuffix} ${shortName?uncap_first}${suffixManager.vOSuffix}=${shortName?uncap_first}${suffixManager.dTOSuffix}.to${shortName}${suffixManager.vOSuffix}();
        responseEntity=new ResponseEntity(${shortName?uncap_first}${suffixManager.vOSuffix}, HttpStatus.OK);
    <#else>
        ${innerService?uncap_first}.${method.name}(${shortName?uncap_first});
        responseEntity=new ResponseEntity(HttpStatus.OK);
    </#if>
        return responseEntity;
    }

</#list>


}