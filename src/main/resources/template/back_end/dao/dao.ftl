${contextModel.daoPackage}

import ${contextModel.daoPath};
import ${contextModel.doPath};
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


<#include "signature.ftl">
@Mapper
public interface ${contextModel.daoName} extends BaseMapper<${contextModel.doName}>{

    public  ${contextModel.doName}   update${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   delete${className}(${contextModel.doName} ${contextModel.doName?uncap_first});

    public  IPage<${contextModel.doName}>   list${className}Page(Page page,@Param("do")  ${contextModel.doName} ${contextModel.doName?uncap_first});

    public  ${contextModel.doName}   get${className}ById(${contextModel.doName} ${contextModel.doName?uncap_first});
}