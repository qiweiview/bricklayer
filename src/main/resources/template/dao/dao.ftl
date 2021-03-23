${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.stereotype.Repository;

@Repository
public interface ${className} {

<#list methods as method>
    ${method.description}
</#list>

}