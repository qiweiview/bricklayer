package cn.anicert.dao;

import cn.anicert.model.d_o.BricklayerLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BricklayerLogDao extends BaseMapper<BricklayerLogDO> {

    public void updateBricklayerLog(BricklayerLogDO bricklayerLogDO);

    public void deleteBricklayerLog(BricklayerLogDO bricklayerLogDO);

    public IPage<BricklayerLogDO> listBricklayerLogPage(Page page, @Param("do") BricklayerLogDO bricklayerLogDO);

    public List<BricklayerLogDO> listBricklayerLog(@Param("do") BricklayerLogDO bricklayerLogDO);

    public BricklayerLogDO getBricklayerLogById(BricklayerLogDO bricklayerLogDO);

    void deleteBricklayerLogBatch(@Param("ids") List<Integer> ids);

    List<BricklayerLogDO> listBricklayerLogByIds(@Param("ids") List<Integer> ids);
}