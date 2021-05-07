package cn.anicert.dao;

import cn.anicert.model.d_o.BricklayerDirectTemplateRelationDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BricklayerDirectTemplateRelationDao extends BaseMapper<BricklayerDirectTemplateRelationDO>{

    public  void   updateBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO);

    public void deleteBricklayerDirectTemplateRelation(BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO);

    public IPage<BricklayerDirectTemplateRelationDO> listBricklayerDirectTemplateRelationPage(Page page, @Param("do") BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO);

    public List<BricklayerDirectTemplateRelationDO> listBricklayerDirectTemplateRelation(@Param("do") BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO);

    public BricklayerDirectTemplateRelationDO getBricklayerDirectTemplateRelationById(BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO);


    void deleteByDirectIds(@Param("ids") List<Integer> collect);

    List<BricklayerDirectTemplateRelationDO> getByDirectIds(@Param("ids") List<Integer> collect);

}