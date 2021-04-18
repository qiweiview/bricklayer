package com.management.dao;

import com.management.dao.BricklayerColumnDao;
import com.management.model.d_o.BricklayerColumnDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    public  IPage<BricklayerColumnDO>   listBricklayerColumnPage(Page page,@Param("do")  BricklayerColumnDO bricklayerColumnDO);

    public  List<BricklayerColumnDO>   listBricklayerColumn(@Param("do")  BricklayerColumnDO bricklayerColumnDO);

    public  BricklayerColumnDO   getBricklayerColumnById(BricklayerColumnDO bricklayerColumnDO);
}