package cn.bricklayer.dao;

import cn.bricklayer.model.d_o.BricklayerDictionaryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BricklayerDictionaryDao extends BaseMapper<BricklayerDictionaryDO> {

    public void updateBricklayerDictionary(BricklayerDictionaryDO bricklayerDictionaryDO);

    public void deleteBricklayerDictionary(BricklayerDictionaryDO bricklayerDictionaryDO);

    public IPage<BricklayerDictionaryDO> listBricklayerDictionaryPage(Page page, @Param("do") BricklayerDictionaryDO bricklayerDictionaryDO);

    public List<BricklayerDictionaryDO> listBricklayerDictionary(@Param("do") BricklayerDictionaryDO bricklayerDictionaryDO);

    public BricklayerDictionaryDO getBricklayerDictionaryById(BricklayerDictionaryDO bricklayerDictionaryDO);

    List<BricklayerDictionaryDO> listBricklayerDictionaryByCode(@Param("name") String name);

    void deleteBatch(@Param("ids") List<Integer> ids);

}