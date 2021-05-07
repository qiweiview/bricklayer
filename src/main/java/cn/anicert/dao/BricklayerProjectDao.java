package cn.anicert.dao;

import cn.anicert.model.d_o.BricklayerProjectDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BricklayerProjectDao extends BaseMapper<BricklayerProjectDO> {

    public void updateBricklayerProject(BricklayerProjectDO bricklayerProjectDO);

    public void deleteBricklayerProject(BricklayerProjectDO bricklayerProjectDO);

    public IPage<BricklayerProjectDO> listBricklayerProjectPage(Page page, @Param("do") BricklayerProjectDO bricklayerProjectDO);

    public List<BricklayerProjectDO> listBricklayerProject(@Param("do") BricklayerProjectDO bricklayerProjectDO);

    public BricklayerProjectDO getBricklayerProjectById(BricklayerProjectDO bricklayerProjectDO);
}