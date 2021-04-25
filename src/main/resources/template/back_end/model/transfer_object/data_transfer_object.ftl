package ${basePath};

import com.model.dto.${className}DO;
import com.model.dto.${className}VO;
import java.util.List;
import java.util.stream.Collectors;


public class ${className}DTO {

// page field
private int current;

private int size;

<#list fieldList as field>

    private  ${field.javaType} ${field.beanName};
</#list>


/*  ------------ data conversion ------------  */
public  ${className}DO to${className}DO(){
${className}DO ${className?uncap_first}DO =new ${className}DO();
<#list fieldList as field>
    ${className?uncap_first}DO.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
return ${className?uncap_first}DO;

}

public  ${className}VO to${className}VO(){
${className}VO ${className?uncap_first}VO =new ${className}VO();
<#list fieldList as field>
    ${className?uncap_first}VO.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
return ${className?uncap_first}VO;

}


public static   List
<${className}VO> to${className}VOList(List
    <${className}DTO> ${className?uncap_first}DTOList){
        List
        <${className}VO> collect= ${className?uncap_first}DTOList.stream().map(x->x.to${className}
            VO()).collect(Collectors.toList());
            return collect;
            }

            public static List
            <${className}DO> to${className}DOList(List
                <${className}DTO> ${className?uncap_first}DtOList){
                    List
                    <${className}DO> collect= ${className?uncap_first}DtOList.stream().map(x->x.to${className}
                        DO()).collect(Collectors.toList());
                        return collect;
                        }

                        /* ------------ getter setter ------------ */
                        public int getCurrent(){
                        return current;
                        }

                        public void setCurrent(int current){
                        this.current=current;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size=size;
    }

<#list fieldList as field>

    public ${field.javaType} get${field.beanName?cap_first}(){
        return ${field.beanName};
    }

    public void set${field.beanName?cap_first}(${field.javaType} ${field.beanName?uncap_first}){
        this.${field.beanName}=${field.beanName?uncap_first};
    }
</#list>
}