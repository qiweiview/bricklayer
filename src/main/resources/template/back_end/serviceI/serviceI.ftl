${contextModel.serviceIPackage}

import ${contextModel.dtoPath};
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;



public interface ${contextModel.serviceIName} {

    public  ${contextModel.dtoName}   save${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  ${contextModel.dtoName}   update${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  ${contextModel.dtoName}   delete${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  IPage<${contextModel.dtoName}>   list${className}Page(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  List<${contextModel.dtoName}>   list${className}(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});

    public  ${contextModel.dtoName}   get${className}ById(${contextModel.dtoName} ${contextModel.dtoName?uncap_first});
}