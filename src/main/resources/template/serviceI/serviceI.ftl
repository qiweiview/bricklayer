${belongPackage}

<#list dependents as dependent>
    ${dependent}
</#list>

public interface ${className} {
<#list methods as method>
    ${method.description}
</#list>
}