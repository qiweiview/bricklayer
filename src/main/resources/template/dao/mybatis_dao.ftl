${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.stereotype.Repository;

<#include "signature.ftl">
@Repository
public interface ${fullName} {
<#list methods as method>

    ${method.description};
</#list>

}