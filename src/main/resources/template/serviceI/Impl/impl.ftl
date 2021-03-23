${belongPackage}

<#list dependents as dependent>
    ${dependent}
</#list>
public class ${className} implements ${interfaceName}{

    private ${innerDao} ${innerDao?uncap_first};

<#list methods as method>

    @Override
    ${method.description}{

    }

</#list>
}