package com.anicert.serviceI;

import com.anicert.model.dto.BricklayerDictionaryDTO;
import com.anicert.model.dto.IdsDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;



public interface BricklayerDictionaryServiceI {

    public  BricklayerDictionaryDTO   saveBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    public  BricklayerDictionaryDTO   updateBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    public  BricklayerDictionaryDTO   deleteBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    public  IPage<BricklayerDictionaryDTO>   listBricklayerDictionaryPage(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    public  List<BricklayerDictionaryDTO>   listBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    public  BricklayerDictionaryDTO   getBricklayerDictionaryById(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    List<BricklayerDictionaryDTO> listByCode(BricklayerDictionaryDTO bricklayerDictionaryDTO);

    void batchDelete(IdsDTO idsDTO);

}