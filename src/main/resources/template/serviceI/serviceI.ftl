${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>

<#include "signature.ftl">
public interface ${fullName} {
<#list methods as method>
    ${method.description};
</#list>
}