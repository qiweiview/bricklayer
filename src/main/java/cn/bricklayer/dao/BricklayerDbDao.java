package cn.bricklayer.dao;

import cn.bricklayer.model.d_o.BricklayerDbDO;
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
public interface BricklayerDbDao extends BaseMapper<BricklayerDbDO>{

    public  BricklayerDbDO   updateBricklayerDb(BricklayerDbDO bricklayerDbDO);

    public  void   deleteBricklayerDb(BricklayerDbDO bricklayerDbDO);

    public  IPage<BricklayerDbDO>   listBricklayerDbPage(Page page,@Param("do")  BricklayerDbDO bricklayerDbDO);

    public  List<BricklayerDbDO>   listBricklayerDb(@Param("do")  BricklayerDbDO bricklayerDbDO);

    public  BricklayerDbDO   getBricklayerDbById(BricklayerDbDO bricklayerDbDO);

    List<BricklayerDbDO> listBricklayerDbAll();
}