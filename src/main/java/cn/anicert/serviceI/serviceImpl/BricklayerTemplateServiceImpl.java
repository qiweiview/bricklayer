package cn.anicert.serviceI.serviceImpl;

import cn.anicert.dao.BricklayerTemplateDao;
import cn.anicert.model.d_o.BricklayerTemplateDO;
import cn.anicert.model.dto.BricklayerTemplateDTO;
import cn.anicert.serviceI.BricklayerTemplateServiceI;
import cn.anicert.utils.DataNotFoundException;
import cn.anicert.utils.LoginInterceptor;
import cn.anicert.utils.MessageRuntimeException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
//import cn.anicert.university.common.exception.EntityNotFoundException;

/**
 * create by view
 */

@Service
@RequiredArgsConstructor
public class BricklayerTemplateServiceImpl implements BricklayerTemplateServiceI {

    private final BricklayerTemplateDao bricklayerTemplateDao;


    @Override
    public BricklayerTemplateDTO saveBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {

        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();

        BricklayerTemplateDO bricklayerTemplateByTemplateName = bricklayerTemplateDao.getBricklayerTemplateByTemplateName(bricklayerTemplateDO);
        if (bricklayerTemplateByTemplateName != null) {
            throw new MessageRuntimeException("已存在同名模板：" + bricklayerTemplateByTemplateName.getTemplateName());
        }

        bricklayerTemplateDO.doInit();
        bricklayerTemplateDao.insert(bricklayerTemplateDO);
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }

    @Override
    public BricklayerTemplateDTO updateBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDTO bricklayerTemplateById = getBricklayerTemplateById(bricklayerTemplateDTO);

        if (!LoginInterceptor.isCurrentUser(bricklayerTemplateById.getCreateBy())) {
            //todo 其他人员模板无法编辑
            throw new MessageRuntimeException("无法编辑其他用户创建模板");
        }

        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO.doUpdate();
        bricklayerTemplateDao.updateById(bricklayerTemplateDO);
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }

    @Override
    public BricklayerTemplateDTO deleteBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        bricklayerTemplateDTO = getBricklayerTemplateById(bricklayerTemplateDTO);


        if (!LoginInterceptor.isCurrentUser(bricklayerTemplateDTO.getCreateBy())) {
            //todo 其他人员模板无法删除
            throw new MessageRuntimeException("无法删除其他用户创建模板");
        }
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO.doDelete();
        bricklayerTemplateDao.deleteBricklayerTemplate(bricklayerTemplateDO);
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }

    @Override
    public List<BricklayerTemplateDTO> listBricklayerTemplate(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        List<BricklayerTemplateDO> records = bricklayerTemplateDao.listBricklayerTemplate(bricklayerTemplateDO);
        List<BricklayerTemplateDTO> list = BricklayerTemplateDO.toBricklayerTemplateDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerTemplateDTO> listBricklayerTemplatePage(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        if (bricklayerTemplateDTO.getOnlyMine()!=null&&bricklayerTemplateDTO.getOnlyMine()) {
            bricklayerTemplateDO.setCreateBy(LoginInterceptor.getCurrentName());
        }
        Page page = new Page(bricklayerTemplateDTO.getCurrent(), bricklayerTemplateDTO.getSize());
        IPage<BricklayerTemplateDO> pageResult = bricklayerTemplateDao.listBricklayerTemplatePage(page, bricklayerTemplateDO);
        List<BricklayerTemplateDO> records = pageResult.getRecords();
        List<BricklayerTemplateDTO> list = BricklayerTemplateDO.toBricklayerTemplateDTOList(records);
        IPage<BricklayerTemplateDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerTemplateDTO getBricklayerTemplateById(BricklayerTemplateDTO bricklayerTemplateDTO) {
        BricklayerTemplateDO bricklayerTemplateDO = bricklayerTemplateDTO.toBricklayerTemplateDO();
        bricklayerTemplateDO = bricklayerTemplateDao.getBricklayerTemplateById(bricklayerTemplateDO);
        if (bricklayerTemplateDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerTemplateDO.toBricklayerTemplateDTO();
    }


}