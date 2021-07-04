package cn.bricklayer.dao;

import cn.bricklayer.model.d_o.BricklayerTableDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
* create by view
*/
@Mapper
public interface BricklayerTableDao extends BaseMapper<BricklayerTableDO>{

    public void updateBricklayerTable(BricklayerTableDO bricklayerTableDO);

    public void deleteBricklayerTable(BricklayerTableDO bricklayerTableDO);

    public IPage<BricklayerTableDO> listBricklayerTablePage(Page page, @Param("do") BricklayerTableDO bricklayerTableDO);

    public List<BricklayerTableDO> listBricklayerTable(@Param("do") BricklayerTableDO bricklayerTableDO);

    public BricklayerTableDO getBricklayerTableById(BricklayerTableDO bricklayerTableDO);


    void deleteBricklayerTableBatch(@Param("ids") List<Integer> ids);

}