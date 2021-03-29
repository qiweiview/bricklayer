${contextModel.doPackage}

import ${contextModel.dtoPath};
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;

<#include "signature.ftl">
@TableName("${originalName}")
public class ${contextModel.doName} {
<#list fieldList as field>

    /**
     * ${field.columnComment}
     */
<#if field.primaryKey>
    @TableId(value = "${field.originalColumnName}",type = IdType.AUTO)
    <#else>
    @TableField(value = "${field.originalColumnName}")
</#if>
    private  ${field.javaType} ${field.beanName};
</#list>

/*  ------------ init value ------------  */
public void doInit(){

}


/*  ------------ data conversion ------------  */
public  ${contextModel.dtoName} to${contextModel.dtoName}(){
${contextModel.dtoName} ${contextModel.dtoName?uncap_first} =new ${contextModel.dtoName}();
<#list fieldList as field>
    ${contextModel.dtoName?uncap_first}.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
        return ${contextModel.dtoName?uncap_first};

}


public static   List<${contextModel.dtoName}>  to${contextModel.dtoName}List(List<${contextModel.doName}> ${contextModel.doName?uncap_first}List){
        List<${contextModel.dtoName}> collect = ${contextModel.doName?uncap_first}List.stream().map(x -> x.to${contextModel.dtoName}()).collect(Collectors.toList());
        return collect;
}

/*  ------------ getter setter ------------  */
<#list fieldList as field>
    public ${field.javaType} get${field.beanName?cap_first}(){
    return ${field.beanName};
    }

    public void set${field.beanName?cap_first}(${field.javaType} ${field.beanName?uncap_first}){
    this.${field.beanName}=${field.beanName?uncap_first};
    }
</#list>
}