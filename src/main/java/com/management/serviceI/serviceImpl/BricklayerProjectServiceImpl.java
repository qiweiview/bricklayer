package com.management.serviceI.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.management.dao.BricklayerDirectDao;
import com.management.dao.BricklayerDirectTemplateRelationDao;
import com.management.dao.BricklayerProjectDao;
import com.management.model.d_o.BricklayerDirectDO;
import com.management.model.d_o.BricklayerDirectTemplateRelationDO;
import com.management.model.d_o.BricklayerProjectDO;
import com.management.model.dto.BricklayerProjectDTO;
import com.management.model.dto.TreeNodeDTO;
import com.management.serviceI.BricklayerProjectServiceI;
import com.management.utils.DataNotFoundException;
import com.management.utils.MessageRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BricklayerProjectServiceImpl implements BricklayerProjectServiceI {

    private final BricklayerProjectDao bricklayerProjectDao;

    private final BricklayerDirectDao bricklayerDirectDao;

    private final BricklayerDirectTemplateRelationDao bricklayerDirectTemplateRelationDao;

    @Override
    public BricklayerProjectDTO saveBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO.doInit();
        bricklayerProjectDao.insert(bricklayerProjectDO);
        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

    @Transactional
    @Override
    public BricklayerProjectDTO updateBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDTO bricklayerProjectById = getBricklayerProjectById(bricklayerProjectDTO);

        if (bricklayerProjectById.getFixProject()) {
            //todo 基础项目无法编辑
            throw new MessageRuntimeException("基础项目无法编辑");
        }

        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO.doUpdate();
        bricklayerProjectDao.updateById(bricklayerProjectDO);

        //全删
        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectDO.getId());
        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> x.getId()).collect(Collectors.toList());
        if (collect.size() > 0) {
            bricklayerDirectDao.deleteByDirectIds(collect);
            bricklayerDirectTemplateRelationDao.deleteByDirectIds(collect);
        }

        //全录
        TreeNodeDTO tree = bricklayerProjectDTO.getTree();
        parseTreeNodeDTO(tree, bricklayerProjectDTO.getId(), -1, "");
        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

    public void parseTreeNodeDTO(TreeNodeDTO treeNodeDTO, Integer projectId, Integer parentId, String parentPath) {
        String label = treeNodeDTO.getLabel();
        Integer templateId = treeNodeDTO.getTemplateId();
        String type = treeNodeDTO.getType();
        if ("direct".equals(type)) {
            //todo direct
            BricklayerDirectDO bricklayerDirectDO = new BricklayerDirectDO();
            bricklayerDirectDO.setBelongProjectId(projectId);
            bricklayerDirectDO.setDirectName(label);
            bricklayerDirectDO.setParentDirectId(parentId);
            if ("/".equals(label)) {
                bricklayerDirectDO.setDirectFullPath("/");
            } else {
                if ("/".equals(parentPath)) {
                    bricklayerDirectDO.setDirectFullPath(parentPath + label);
                } else {
                    bricklayerDirectDO.setDirectFullPath(parentPath + "/" + label);
                }

            }
            bricklayerDirectDao.insert(bricklayerDirectDO);
            List<TreeNodeDTO> children = treeNodeDTO.getChildren();
            if (children != null) {
                children.forEach(x -> {
                    parseTreeNodeDTO(x, projectId, bricklayerDirectDO.getId(), bricklayerDirectDO.getDirectFullPath());
                });
            }
        } else {
            BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO = new BricklayerDirectTemplateRelationDO();
            bricklayerDirectTemplateRelationDO.setTemplateId(templateId);
            bricklayerDirectTemplateRelationDO.setBelongDirectId(parentId);
            bricklayerDirectTemplateRelationDao.insert(bricklayerDirectTemplateRelationDO);
        }


    }


    @Transactional
    @Override
    public BricklayerProjectDTO deleteBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        bricklayerProjectDTO = getBricklayerProjectById(bricklayerProjectDTO);
        if (bricklayerProjectDTO.getFixProject()) {
            //todo 固定项目无法删除
            throw new MessageRuntimeException("基础项目无法删除");
        }
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO.doDelete();
        bricklayerProjectDao.deleteBricklayerProject(bricklayerProjectDO);

        //删除相关目录
        bricklayerDirectDao.deleteByDirectsByProjectId(bricklayerProjectDO.getId());
        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

    @Override
    public List<BricklayerProjectDTO> listBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        List<BricklayerProjectDO> records = bricklayerProjectDao.listBricklayerProject(bricklayerProjectDO);
        List<BricklayerProjectDTO> list = BricklayerProjectDO.toBricklayerProjectDTOList(records);
        return list;
    }

    @Override
    public IPage<BricklayerProjectDTO> listBricklayerProjectPage(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        Page page = new Page(bricklayerProjectDTO.getCurrent(), bricklayerProjectDTO.getSize());
        IPage<BricklayerProjectDO> pageResult = bricklayerProjectDao.listBricklayerProjectPage(page, bricklayerProjectDO);
        List<BricklayerProjectDO> records = pageResult.getRecords();
        List<BricklayerProjectDTO> list = BricklayerProjectDO.toBricklayerProjectDTOList(records);
        IPage<BricklayerProjectDTO> rs = new Page(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal());
        rs.setRecords(list);
        return rs;
    }

    @Override
    public BricklayerProjectDTO getBricklayerProjectByIdWithTree(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDTO bricklayerProjectById = getBricklayerProjectById(bricklayerProjectDTO);

        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectById.getId());

        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> x.getId()).collect(Collectors.toList());

        collect.add(-999);

        List<BricklayerDirectTemplateRelationDO> bricklayerDirectTemplateRelationDOS = bricklayerDirectTemplateRelationDao.getByDirectIds(collect);

        Map<Integer, List<BricklayerDirectTemplateRelationDO>> collect1 = bricklayerDirectTemplateRelationDOS.stream().collect(Collectors.groupingBy(x -> x.getBelongDirectId()));


        Map<Integer, TreeNodeDTO> map = new HashMap<>();
        bricklayerDirectDOS.forEach(x -> {
            Integer id = x.getId();
            TreeNodeDTO treeNodeDTO = map.get(id);
            if (treeNodeDTO == null) {
                treeNodeDTO = new TreeNodeDTO();
                map.put(id, treeNodeDTO);
            }
            treeNodeDTO.setLabel(x.getDirectName());
            treeNodeDTO.setType("direct");
            Integer parentDirectId = x.getParentDirectId();
            if (-1 == parentDirectId) {
                treeNodeDTO.setLevel("root");
            } else {
                treeNodeDTO.setLevel("son");
            }

            TreeNodeDTO parent = map.get(parentDirectId);
            if (parent == null) {
                parent = new TreeNodeDTO();
                map.put(parentDirectId, parent);
            }
            parent.addChild(treeNodeDTO);

        });

        TreeNodeDTO[] root = new TreeNodeDTO[1];
        map.forEach((k, v) -> {
            if ("root" == v.getLevel()) {
                root[0] = v;
            }
            List<BricklayerDirectTemplateRelationDO> bricklayerDirectTemplateRelationDOS1 = collect1.get(k);
            if (bricklayerDirectTemplateRelationDOS1 != null) {
                bricklayerDirectTemplateRelationDOS1.forEach(z -> {
                    TreeNodeDTO treeNodeDTO = new TreeNodeDTO();
                    treeNodeDTO.setLabel(z.getTemplateName());
                    treeNodeDTO.setTemplateId(z.getTemplateId());
                    treeNodeDTO.setType("file");
                    treeNodeDTO.setLevel("son");
                    v.addChild(treeNodeDTO);
                });
            }

        });

        if (root[0] == null) {
            TreeNodeDTO treeNodeDTO = new TreeNodeDTO();
            treeNodeDTO.toBeRoot();
            root[0] = treeNodeDTO;
        }

        bricklayerProjectById.setTree(root[0]);
        return bricklayerProjectById;
    }

    @Override
    public BricklayerProjectDTO getBricklayerProjectById(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO = bricklayerProjectDao.getBricklayerProjectById(bricklayerProjectDO);
        if (bricklayerProjectDO == null) {
            throw new DataNotFoundException();
        }


        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

}