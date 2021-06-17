package cn.anicert.dao;

import cn.anicert.model.d_o.BricklayerTemplateDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by view
 */
@Mapper
public interface BricklayerTemplateDao extends BaseMapper<BricklayerTemplateDO> {

    public void updateBricklayerTemplate(BricklayerTemplateDO bricklayerTemplateDO);

    public void deleteBricklayerTemplate(BricklayerTemplateDO bricklayerTemplateDO);

    public IPage<BricklayerTemplateDO> listBricklayerTemplatePage(Page page, @Param("do") BricklayerTemplateDO bricklayerTemplateDO);

    public List<BricklayerTemplateDO> listBricklayerTemplate(@Param("do") BricklayerTemplateDO bricklayerTemplateDO);

    public BricklayerTemplateDO getBricklayerTemplateById(BricklayerTemplateDO bricklayerTemplateDO);

    public BricklayerTemplateDO getBricklayerTemplateByTemplateName(BricklayerTemplateDO bricklayerTemplateDO);

    List<BricklayerTemplateDO> listBricklayerTemplatesByDirectIds(@Param("ids") List<Integer> ids);


    void deleteBricklayerTableBatch(@Param("ids") List<Integer> ids, @Param("createBy") String currentName);
}