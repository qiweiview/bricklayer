<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${contextModel.daoPath}" >

    <resultMap type="${contextModel.doPath}" id="${className}Map" >
<#list fieldList as field>
    <#if field.primaryKey>
                <id property="${field.beanName}" column="${field.originalColumnName}" />
    </#if>
</#list>
<#list fieldList as field>
    <#if !field.primaryKey>
                <result property="${field.beanName}" column="${field.originalColumnName}" />
    </#if>
</#list>
    </resultMap>



    <select id="list${className}Page" parameterType="${contextModel.doPath}" resultMap="${className}Map" >
        select
        <#list fieldList as field>
            ${field.originalColumnName},
        </#list>
        '1'as blank_
        from ${originalName}
        where 1=1
    </select>

    <select id="get${className}ById" parameterType="${contextModel.doPath}" resultMap="${className}Map" >
        select
<#list fieldList as field>
    ${field.originalColumnName},
</#list>
        '1'as blank_
        from ${originalName}
        where 1=1
        and ${primaryName}  = <#noparse>#{</#noparse>${primaryName}<#noparse>}</#noparse>
    </select>


    <delete id="delete${className}" parameterType="${contextModel.doPath}" >
        delete
        from ${originalName}
        where 1=1
        and ${primaryName}  = <#noparse>#{</#noparse>${primaryName}<#noparse>}</#noparse>
    </delete>

    <update id="update${className}" parameterType="${contextModel.doPath}" >
        update ${originalName}
        set
<#list fieldList as field>
    <#if !field.primaryKey>
        <if test="${field.beanName}!=null <#if field.javaType?contains("String")> and ${field.beanName}!=''</#if>" >
        ${field.originalColumnName} = <#noparse>#{</#noparse> ${field.beanName}<#noparse>}</#noparse>,
        </if>
    </#if>
</#list>
        ${primaryName}=${primaryName}
        where 1=1
        and ${primaryName}  = <#noparse>#{</#noparse>${primaryName}<#noparse>}</#noparse>
    </update>


</mapper>