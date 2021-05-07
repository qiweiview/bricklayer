package cn.anicert.dao;

import cn.anicert.model.d_o.BricklayerColumnDO;
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
public interface BricklayerColumnDao extends BaseMapper<BricklayerColumnDO>{

    public  void   updateBricklayerColumn(BricklayerColumnDO bricklayerColumnDO);

    public  void   deleteBricklayerColumn(BricklayerColumnDO bricklayerColumnDO);

    public IPage<BricklayerColumnDO> listBricklayerColumnPage(Page page, @Param("do") BricklayerColumnDO bricklayerColumnDO);

    public List<BricklayerColumnDO> listBricklayerColumn(@Param("do") BricklayerColumnDO bricklayerColumnDO);

    public BricklayerColumnDO getBricklayerColumnById(BricklayerColumnDO bricklayerColumnDO);

    void deleteBricklayerColumnByBelongTableId(BricklayerColumnDO bricklayerColumnDO);

    List<BricklayerColumnDO> getBricklayerColumnsByBelongTableId(BricklayerColumnDO bricklayerColumnDO);

    List<BricklayerColumnDO> getBricklayerTablesByIds(@Param("ids") List<Integer> ids);

    List<BricklayerColumnDO> getBricklayerTableById(@Param("id") Integer id);

    void deleteBricklayerColumnByBelongTableIds(@Param("ids") List<Integer> ids);

}