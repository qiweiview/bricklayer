package cn.bricklayer.dao;

import cn.bricklayer.model.d_o.BricklayerProjectGlobalVariableDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BricklayerProjectGlobalVariableDao extends BaseMapper<BricklayerProjectGlobalVariableDO> {

    public void updateBricklayerProjectGlobalVariable(BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO);

    public void deleteBricklayerProjectGlobalVariable(BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO);

    public void deleteBricklayerProjectGlobalVariableLogically(BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO);

    public IPage<BricklayerProjectGlobalVariableDO> listBricklayerProjectGlobalVariablePage(Page page, @Param("do") BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO);

    public List<BricklayerProjectGlobalVariableDO> listBricklayerProjectGlobalVariable(@Param("do") BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO);

    public BricklayerProjectGlobalVariableDO getBricklayerProjectGlobalVariableById(BricklayerProjectGlobalVariableDO bricklayerProjectGlobalVariableDO);

    void deleteBricklayerProjectGlobalVariableBatch(@Param("ids") List<Integer> ids);

    void deleteBricklayerProjectGlobalVariableBatchLogically(@Param("ids") List<Integer> ids);

    List<BricklayerProjectGlobalVariableDO> listBricklayerProjectGlobalVariableByIds(@Param("ids") List<Integer> ids);

    void deleteByProjectId(@Param("id") Integer id);

    List<BricklayerProjectGlobalVariableDO> listByProjectId(@Param("id") Integer id);
}