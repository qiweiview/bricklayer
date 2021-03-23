${belongPackage}

<#list dependents as dependent>
${dependent}
</#list>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${baseMapping}")
public class ${className} {

    private ${innerService} ${innerService?uncap_first};

<#list methods as method>

    @RequestMapping("${method.path}")
    public Object ${method.name}() {
        //todo need fix
        ${innerService?uncap_first}.${method.name}();
        return null;
    }

</#list>


}