package cn.anicert.serviceI;

import cn.anicert.model.dto.BricklayerProjectDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;


public interface BricklayerProjectServiceI {

    public BricklayerProjectDTO saveBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO);

    public BricklayerProjectDTO updateBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO);

    public BricklayerProjectDTO deleteBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO);

    public IPage<BricklayerProjectDTO> listBricklayerProjectPage(BricklayerProjectDTO bricklayerProjectDTO);

    public List<BricklayerProjectDTO> listBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO);

    public BricklayerProjectDTO getBricklayerProjectById(BricklayerProjectDTO bricklayerProjectDTO);

    public BricklayerProjectDTO getBricklayerProjectByIdWithTree(BricklayerProjectDTO bricklayerProjectDTO);

    void copyBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO);
}