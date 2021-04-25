package ${basePath};

import com.model.dto.${className}DTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;


public interface ${className}ServiceI {

public  ${className}DTO   save${className}(${className}DTO ${className?uncap_first}DTO);

public  ${className}DTO   update${className}(${className}DTO ${className?uncap_first}DTO);

public  ${className}DTO   delete${className}(${className}DTO ${className?uncap_first}DTO);

public  IPage
<${className}DTO> list${className}Page(${className}DTO ${className?uncap_first}DTO);

    public List
    <${className}DTO> list${className}(${className}DTO ${className?uncap_first}DTO);

        public ${className}DTO get${className}ById(${className}DTO ${className?uncap_first}DTO);
        }