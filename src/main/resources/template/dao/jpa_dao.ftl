${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;

<#include "signature.ftl">
public interface ${fullName} extends PagingAndSortingRepository<${shortName}${suffixManager.dOSuffix},Long> {



<#--不另外添加方法-->
<#--<#list methods as method>-->

<#--    ${method.description};-->
<#--</#list>-->

}