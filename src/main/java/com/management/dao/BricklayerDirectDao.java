package com.management.dao;

import com.management.dao.BricklayerDirectDao;
import com.management.model.d_o.BricklayerDirectDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


@Mapper
public interface BricklayerDirectDao extends BaseMapper<BricklayerDirectDO>{

    public  void   updateBricklayerDirect(BricklayerDirectDO bricklayerDirectDO);

    public  void   deleteBricklayerDirect(BricklayerDirectDO bricklayerDirectDO);

    public  IPage<BricklayerDirectDO>   listBricklayerDirectPage(Page page,@Param("do")  BricklayerDirectDO bricklayerDirectDO);

    public  List<BricklayerDirectDO>   listBricklayerDirect(@Param("do")  BricklayerDirectDO bricklayerDirectDO);

    public  BricklayerDirectDO   getBricklayerDirectById(BricklayerDirectDO bricklayerDirectDO);

    void deleteDirectByProjectId(@Param("id") Integer id);

    public  List<BricklayerDirectDO>   listBricklayerDirectsByProjectId(@Param("id")  Integer id);
}