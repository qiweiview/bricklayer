<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${daoPath}">

    <resultMap type="${doPath}" id="${name}Map">
        <#list dbColumnModelList as attribute>
            <#if attribute.columnKey=="PRI">
                <id property="${attribute.name}" column="${attribute.originalName}"/>
            <#else>
                <result property="${attribute.name}" column="${attribute.originalName}"/>
            </#if>
        </#list>
    </resultMap>


    <select id="listAll${name}" parameterType="${doPath}" resultMap="${name}Map">
        select
        <#list dbColumnModelList as attribute>
            ${attribute.originalName},
        </#list>
        1
        from ${originalName}
        where 1=1
    </select>

    <select id="get${name}ByPrimaryKey" parameterType="${doPath}" resultMap="${name}Map">
        select
        <#list dbColumnModelList as attribute>
            ${attribute.originalName},
        </#list>
        1
        from ${originalName}
        where 1=1
        and ${primaryKey}  = <#noparse>#{</#noparse>${primaryKey}<#noparse>}</#noparse>
    </select>


    <delete id="remove${name}ByPrimaryKey" parameterType="${doPath}" >
        delete
        from ${originalName}
        where 1=1
        and ${primaryKey}  = <#noparse>#{</#noparse>${primaryKey}<#noparse>}</#noparse>
    </delete>

    <update id="update${name}ByPrimaryKey" parameterType="${doPath}" >
        update ${originalName}
        set ${primaryKey}=${primaryKey}
        <#list dbColumnModelList as attribute>
            <#if attribute.columnKey!="PRI">
        <if test="${attribute.originalName}!=null and ${attribute.originalName}!=''">
            ${attribute.originalName}  = <#noparse>#{</#noparse>${attribute.name}<#noparse>}</#noparse>,
        </if>
            </#if>
        </#list>
        where 1=1
        and ${primaryKey}  = <#noparse>#{</#noparse>${primaryKey}<#noparse>}</#noparse>
    </update>


</mapper>