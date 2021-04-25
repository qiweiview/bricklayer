package ${basePath};

import com.model.dto.${className}DTO;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@TableName("${originalName}")
public class ${className}DO {
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
    public void doInit() {
        //setDelFlag(false);
        //setCreateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        }

    public void doUpdate() {
//setUpdateTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
}

public void doDelete() {
//setDelFlag(true);
//doUpdate();
}


/*  ------------ data conversion by model ------------  */
public  ${className}DTO to${className}DTO(){
${className}DTO ${className?uncap_first}DTO =new ${className}DTO();
<#list fieldList as field>
    ${className?uncap_first}DTO.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
return ${className?uncap_first}DTO;

}


public static   List
<${className}DTO> to${className}DTOList(List
    <${className}DO> ${className?uncap_first}DOList){
        List
        <${className}DTO> collect = ${className?uncap_first}DOList.stream().map(x -> x.to${className}
            DTO()).collect(Collectors.toList());
            return collect;
            }

            /* ------------ getter setter ------------ */
            <#list fieldList as field>

                public ${field.javaType} get${field.beanName?cap_first}(){
                return ${field.beanName};
                }

    public void set${field.beanName?cap_first}(${field.javaType} ${field.beanName?uncap_first}){
    this.${field.beanName}=${field.beanName?uncap_first};
    }
</#list>
}