${contextModel.dtoPackage}

import ${contextModel.doPath};
import ${contextModel.voPath};

<#include "signature.ftl">
public class ${contextModel.dtoName} {
    private int current;

    private int size;
<#list fieldList as field>

    private  ${field.javaType} ${field.beanName};
</#list>


/*  ------------ data conversion ------------  */
public  ${contextModel.doName} to${contextModel.doName}(){
    ${contextModel.doName} ${contextModel.doName?uncap_first} =new ${contextModel.doName}();
<#list fieldList as field>
    ${contextModel.doName?uncap_first}.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
    return ${contextModel.doName?uncap_first};

}

public  ${contextModel.voName} to${contextModel.voName}(){
${contextModel.voName} ${contextModel.voName?uncap_first} =new ${contextModel.voName}();
<#list fieldList as field>
    ${contextModel.voName?uncap_first}.set${field.beanName?cap_first}(get${field.beanName?cap_first}());
</#list>
        return ${contextModel.voName?uncap_first};

        }

/*  ------------ getter setter ------------  */
    public int getCurrent() {
        return current;
        }

    public void setCurrent(int current) {
        this.current = current;
        }

    public int getSize() {
        return size;
        }

    public void setSize(int size) {
        this.size = size;
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