package cn.anicert.serviceI.serviceImpl;

import cn.anicert.dao.BricklayerDirectTemplateRelationDao;
import cn.anicert.model.d_o.BricklayerDirectTemplateRelationDO;
import cn.anicert.model.dto.BricklayerDirectTemplateRelationDTO;
import cn.anicert.serviceI.BricklayerDirectTemplateRelationServiceI;
import cn.anicert.utils.DataNotFoundException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BricklayerDirectTemplateRelationServiceImpl implements BricklayerDirectTemplateRelationServiceI {

    private final BricklayerDirectTemplateRelationDao bricklayerDirectTemplateRelationDao;


    @Override
    public BricklayerDirectTemplateRelationDTO saveBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO) {
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO = bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationDO();
        bricklayerDirectTemplateRelationDO.doInit();
        bricklayerDirectTemplateRelationDao.insert(bricklayerDirectTemplateRelationDO);
        return bricklayerDirectTemplateRelationDO.toBricklayerDirectTemplateRelationDTO();
        }

    @Override
    public  BricklayerDirectTemplateRelationDTO   updateBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO){
        getBricklayerDirectTemplateRelationById(bricklayerDirectTemplateRelationDTO);
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationDO();
        bricklayerDirectTemplateRelationDO.doUpdate();
        bricklayerDirectTemplateRelationDao.updateById(bricklayerDirectTemplateRelationDO);
        return bricklayerDirectTemplateRelationDO.toBricklayerDirectTemplateRelationDTO();
        }

    @Override
    public  BricklayerDirectTemplateRelationDTO   deleteBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO){
        bricklayerDirectTemplateRelationDTO=getBricklayerDirectTemplateRelationById(bricklayerDirectTemplateRelationDTO);
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationDO();
        bricklayerDirectTemplateRelationDO.doDelete();
        bricklayerDirectTemplateRelationDao.deleteBricklayerDirectTemplateRelation(bricklayerDirectTemplateRelationDO);
        return bricklayerDirectTemplateRelationDO.toBricklayerDirectTemplateRelationDTO();
        }

    @Override
    public  List<BricklayerDirectTemplateRelationDTO> listBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO){
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationDO();
        List<BricklayerDirectTemplateRelationDO> records = bricklayerDirectTemplateRelationDao.listBricklayerDirectTemplateRelation( bricklayerDirectTemplateRelationDO);
        List<BricklayerDirectTemplateRelationDTO> list = BricklayerDirectTemplateRelationDO.toBricklayerDirectTemplateRelationDTOList(records);
        return list;
        }

    @Override
    public  IPage<BricklayerDirectTemplateRelationDTO> listBricklayerDirectTemplateRelationPage(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO){
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationDO();
        Page page = new Page(bricklayerDirectTemplateRelationDTO.getCurrent(), bricklayerDirectTemplateRelationDTO.getSize());
        IPage<BricklayerDirectTemplateRelationDO> pageResult = bricklayerDirectTemplateRelationDao.listBricklayerDirectTemplateRelationPage(page, bricklayerDirectTemplateRelationDO);
        List<BricklayerDirectTemplateRelationDO> records = pageResult.getRecords();
        List<BricklayerDirectTemplateRelationDTO> list = BricklayerDirectTemplateRelationDO.toBricklayerDirectTemplateRelationDTOList(records);
        IPage<BricklayerDirectTemplateRelationDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
        }

    @Override
    public  BricklayerDirectTemplateRelationDTO   getBricklayerDirectTemplateRelationById(BricklayerDirectTemplateRelationDTO bricklayerDirectTemplateRelationDTO){
        BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO=bricklayerDirectTemplateRelationDTO.toBricklayerDirectTemplateRelationDO();
        bricklayerDirectTemplateRelationDO=bricklayerDirectTemplateRelationDao.getBricklayerDirectTemplateRelationById(bricklayerDirectTemplateRelationDO);
        if(bricklayerDirectTemplateRelationDO==null){
            throw new DataNotFoundException();
        }
        return bricklayerDirectTemplateRelationDO.toBricklayerDirectTemplateRelationDTO();
        }

}