package cn.bricklayer.serviceI.Impl;

import cn.bricklayer.dao.BricklayerDictionaryDao;
import cn.bricklayer.model.d_o.BricklayerDictionaryDO;
import cn.bricklayer.model.dto.BricklayerDictionaryDTO;
import cn.bricklayer.model.dto.IdsDTO;
import cn.bricklayer.serviceI.BricklayerDictionaryServiceI;
import cn.bricklayer.utils.DataNotFoundException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BricklayerDictionaryServiceImpl implements BricklayerDictionaryServiceI {

    private final BricklayerDictionaryDao bricklayerDictionaryDao;


    @Override
    public BricklayerDictionaryDTO saveBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        BricklayerDictionaryDO bricklayerDictionaryDO = bricklayerDictionaryDTO.toBricklayerDictionaryDO();
        bricklayerDictionaryDO.doInit();
        bricklayerDictionaryDao.insert(bricklayerDictionaryDO);
        return bricklayerDictionaryDO.toBricklayerDictionaryDTO();
    }

    @Override
    public BricklayerDictionaryDTO updateBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        getBricklayerDictionaryById(bricklayerDictionaryDTO);
        BricklayerDictionaryDO bricklayerDictionaryDO = bricklayerDictionaryDTO.toBricklayerDictionaryDO();
        bricklayerDictionaryDO.doUpdate();
        bricklayerDictionaryDao.updateById(bricklayerDictionaryDO);
        return bricklayerDictionaryDO.toBricklayerDictionaryDTO();
    }

    @Override
    public BricklayerDictionaryDTO deleteBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        bricklayerDictionaryDTO = getBricklayerDictionaryById(bricklayerDictionaryDTO);
        BricklayerDictionaryDO bricklayerDictionaryDO = bricklayerDictionaryDTO.toBricklayerDictionaryDO();
        bricklayerDictionaryDO.doDelete();
        bricklayerDictionaryDao.deleteBricklayerDictionary(bricklayerDictionaryDO);
        return bricklayerDictionaryDO.toBricklayerDictionaryDTO();
    }

    @Override
    public List<BricklayerDictionaryDTO> listBricklayerDictionary(BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        BricklayerDictionaryDO bricklayerDictionaryDO = bricklayerDictionaryDTO.toBricklayerDictionaryDO();
        List<BricklayerDictionaryDO> records = bricklayerDictionaryDao.listBricklayerDictionary(bricklayerDictionaryDO);
        List<BricklayerDictionaryDTO> list = BricklayerDictionaryDO.toBricklayerDictionaryDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerDictionaryDTO> listBricklayerDictionaryPage(BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        BricklayerDictionaryDO bricklayerDictionaryDO = bricklayerDictionaryDTO.toBricklayerDictionaryDO();
        Page page = new Page(bricklayerDictionaryDTO.getCurrent(), bricklayerDictionaryDTO.getSize());
        IPage<BricklayerDictionaryDO> pageResult = bricklayerDictionaryDao.listBricklayerDictionaryPage(page, bricklayerDictionaryDO);
        List<BricklayerDictionaryDO> records = pageResult.getRecords();
        List<BricklayerDictionaryDTO> list = BricklayerDictionaryDO.toBricklayerDictionaryDTOList(records);
        IPage<BricklayerDictionaryDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerDictionaryDTO getBricklayerDictionaryById(BricklayerDictionaryDTO bricklayerDictionaryDTO) {
        BricklayerDictionaryDO bricklayerDictionaryDO = bricklayerDictionaryDTO.toBricklayerDictionaryDO();
        bricklayerDictionaryDO = bricklayerDictionaryDao.getBricklayerDictionaryById(bricklayerDictionaryDO);
        if (bricklayerDictionaryDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerDictionaryDO.toBricklayerDictionaryDTO();
    }

    @Override
    public List<BricklayerDictionaryDTO> listByCode(BricklayerDictionaryDTO bricklayerDictionaryDTO) {

        List<BricklayerDictionaryDO> bricklayerDictionaryDOS = bricklayerDictionaryDao.listBricklayerDictionaryByCode(bricklayerDictionaryDTO.getDictionaryCode());

        List<BricklayerDictionaryDTO> bricklayerDictionaryDTOS = BricklayerDictionaryDO.toBricklayerDictionaryDTOList(bricklayerDictionaryDOS);
        return bricklayerDictionaryDTOS;
    }

    @Override
    public void batchDelete(IdsDTO idsDTO) {
        List<Integer> ids = idsDTO.getIds();
        if (ids.size() > 0) {
            bricklayerDictionaryDao.deleteBatch(idsDTO.getIds());
        }

    }

}