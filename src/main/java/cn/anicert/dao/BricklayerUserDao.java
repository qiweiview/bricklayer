package cn.anicert.dao;

import cn.anicert.model.d_o.BricklayerUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BricklayerUserDao extends BaseMapper<BricklayerUserDO>{

    public  void   updateBricklayerUser(BricklayerUserDO bricklayerUserDO);

    public  void   deleteBricklayerUser(BricklayerUserDO bricklayerUserDO);

    public  IPage<BricklayerUserDO>   listBricklayerUserPage(Page page,@Param("do")  BricklayerUserDO bricklayerUserDO);

    public  List<BricklayerUserDO>   listBricklayerUser(@Param("do")  BricklayerUserDO bricklayerUserDO);

    public  BricklayerUserDO   getBricklayerUserById(BricklayerUserDO bricklayerUserDO);

    BricklayerUserDO getBricklayerUserByName(@Param("name")  String name);

}