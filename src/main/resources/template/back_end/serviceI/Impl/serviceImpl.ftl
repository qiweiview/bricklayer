package ${basePath};

import com.model.dto.${className}DTO;
import com.model.dto.${className}DO;
import com.dao.${className}Dao;
import com.serviceI.${className}ServiceI;
import com.utils.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ${className}ServiceImpl implements ${className}ServiceI{

private final ${className}Dao ${className?uncap_first}Dao;


@Override
public  ${className}DTO   save${className}(${className}DTO ${className?uncap_first}DTO){
${className}DO ${className?uncap_first}DO=${className?uncap_first}DTO.to${className}DO();
${className?uncap_first}DO.doInit();
${className?uncap_first}Dao.insert(${className?uncap_first}DO);
return ${className?uncap_first}DO.to${className}DTO();
}

@Override
public  ${className}DTO   update${className}(${className}DTO ${className?uncap_first}DTO){
get${className}ById(${className?uncap_first}DTO);
${className}DO ${className?uncap_first}DO=${className?uncap_first}DTO.to${className}DO();
${className?uncap_first}DO.doUpdate();
${className?uncap_first}Dao.updateById(${className?uncap_first}DO);
return ${className?uncap_first}DO.to${className}DTO();
}

@Override
public  ${className}DTO   delete${className}(${className}DTO ${className?uncap_first}DTO){
${className?uncap_first}DTO=get${className}ById(${className?uncap_first}DTO);
${className}DO ${className?uncap_first}DO=${className?uncap_first}DTO.to${className}DO();
${className?uncap_first}DO.doDelete();
${className?uncap_first}Dao.delete${className}(${className?uncap_first}DO);
return ${className?uncap_first}DO.to${className}DTO();
}

@Override
public  List
<${className}DTO> list${className}(${className}DTO ${className?uncap_first}DTO){
    ${className}DO ${className?uncap_first}DO=${className?uncap_first}DTO.to${className}DO();
    List
    <${className}DO> records = ${className?uncap_first}Dao.list${className}( ${className?uncap_first}DO);
        List
        <${className}DTO> list = ${className}DO.to${className}DTOList(records);
            return list;
            }

            @Override
            public IPage
            <${className}DTO> list${className}Page(${className}DTO ${className?uncap_first}DTO){
                ${className}DO ${className?uncap_first}DO=${className?uncap_first}DTO.to${className}DO();
                Page page = new Page(${className?uncap_first}DTO.getCurrent(), ${className?uncap_first}DTO.getSize());
                IPage
                <${className}DO> pageResult = ${className?uncap_first}Dao.list${className}
                    Page(page, ${className?uncap_first}DTO);
                    List
                    <${className}DO> records = pageResult.getRecords();
                        List
                        <${className}DTO> list = ${className}DO.to${className}DTOList(records);
                            IPage
                            <${className}DTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(),
                                pageResult.getTotal());
                                rs.setRecords(list);
                                return rs;
                                }

                                @Override
                                public ${className}DTO get${className}ById(${className}DTO ${className?uncap_first}DTO){
                                ${className}DO ${className?uncap_first}DO=${className?uncap_first}DTO.to${className}
                                DO();
                                ${className?uncap_first}DO=${className?uncap_first}Dao.get${className}
                                ById(${className?uncap_first}DTO);
                                if(${className?uncap_first}DO==null){
                                throw new DataNotFoundException();
                                }
                                return ${className?uncap_first}DO.to${className}DTO();
                                }

                                }