package cn.anicert.serviceI;


import cn.anicert.model.dto.*;
import cn.anicert.model.vo.GenerationVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
*
* create by view
*/
public interface BricklayerDbServiceI {

    public BricklayerDbDTO saveBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  BricklayerDbDTO   updateBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  BricklayerDbDTO   deleteBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  IPage<BricklayerDbDTO>   listBricklayerDbPage(BricklayerDbDTO bricklayerDbDTO);

    public  List<BricklayerDbDTO>   listBricklayerDb(BricklayerDbDTO bricklayerDbDTO);

    public  BricklayerDbDTO   getBricklayerDbById(BricklayerDbDTO bricklayerDbDTO);

    List<BricklayerDbDTO> listBricklayerDbAll();

    List<String> getDataSourceList(BricklayerDbDTO bricklayerDbDTO);

    BricklayerTableDTO getTableDetail(TableDetailDTO tableDetailDTO);

    void saveSingleModel( BricklayerTableDTO bricklayerTableDTO);

    void saveBatchModels(BricklayerTableDTO bricklayerTableDTO);

    List<String> getTables(TableDetailDTO tableDetailDTO);

    List<BricklayerTableDTO> getBricklayerTablesByIds(List<Integer> ids);

    BricklayerTableDTO getBricklayerTableById(Integer id);

    GenerationVO generateCode(GenerateCodeDTO generateCodeDTO);

    void deleteBricklayerTableBatch(BricklayerTemplateDTO bricklayerTemplateDTO);
}