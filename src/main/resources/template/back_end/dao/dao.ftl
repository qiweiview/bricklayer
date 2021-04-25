package ${basePath};

import com.model.dto.${className}DO;
import com.serviceI.${className}Dao;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


@Mapper
public interface ${className}Dao extends BaseMapper
<${className}DO>{

    public void update${className}(${className}DO ${className?uncap_first}DO);

    public void delete${className}(${className}DO ${className?uncap_first}DO);

    public IPage
    <${className}DO> list${className}Page(Page page,@Param("do") ${className}DO ${className?uncap_first}DO);

        public List
        <${className}DO> list${className}(@Param("do") ${className}DO ${className?uncap_first}DO);

            public ${className}DO get${className}ById(${className}DO ${className?uncap_first}DO);
            }