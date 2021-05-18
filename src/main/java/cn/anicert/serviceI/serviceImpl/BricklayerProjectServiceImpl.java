package cn.anicert.serviceI.serviceImpl;

import cn.anicert.dao.BricklayerDirectDao;
import cn.anicert.dao.BricklayerDirectTemplateRelationDao;
import cn.anicert.dao.BricklayerProjectDao;
import cn.anicert.dao.BricklayerTemplateDao;
import cn.anicert.model.d_o.BricklayerDirectDO;
import cn.anicert.model.d_o.BricklayerDirectTemplateRelationDO;
import cn.anicert.model.d_o.BricklayerProjectDO;
import cn.anicert.model.d_o.BricklayerTemplateDO;
import cn.anicert.model.dto.BricklayerProjectDTO;
import cn.anicert.model.dto.TreeNodeDTO;
import cn.anicert.serviceI.BricklayerProjectServiceI;
import cn.anicert.utils.DataNotFoundException;
import cn.anicert.utils.LoginInterceptor;
import cn.anicert.utils.MessageRuntimeException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    private final BricklayerTemplateDao bricklayerTemplateDao;

    @Override
    public BricklayerProjectDTO saveBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO.doInit();
        bricklayerProjectDao.insert(bricklayerProjectDO);
        return bricklayerProjectDO.toBricklayerProjectDTO();
    }


    @Override
    public void copyBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {

        //保存项目
        BricklayerProjectDTO saveBricklayerProject = saveBricklayerProject(bricklayerProjectDTO);


        //查询目录
        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectDTO.getId());

        Map<Integer, Integer> map = new HashMap<>();

        bricklayerDirectDOS.forEach(x -> {
            Integer oldId = x.getId();
            x.setId(null);
            x.doInit();
            bricklayerDirectDao.insert(x);
            Integer newId = x.getId();
            map.put(oldId, newId);
        });

        //转换id
        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> x.getId()).collect(Collectors.toList());


        //查询模板
        List<BricklayerTemplateDO> bricklayerTemplateDOS = bricklayerTemplateDao.listBricklayerTemplatesByDirectIds(collect);


        //分组模板
        Map<Integer, List<BricklayerTemplateDO>> collect1 = bricklayerTemplateDOS.stream().collect(Collectors.groupingBy(x -> x.getBelongDirectId()));

        collect1.forEach((k, v) -> {
            v.forEach(x -> {

            });
        });

    }

    @Transactional
    @Override
    public BricklayerProjectDTO updateBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDTO bricklayerProjectById = getBricklayerProjectById(bricklayerProjectDTO);


        if (!LoginInterceptor.isCurrentUser(bricklayerProjectById.getCreateBy())) {
            //todo 其他人员项目无法编辑无法编辑
            throw new MessageRuntimeException("无法编辑其他用户创建项目");
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
        }
        if ("document".equals(type)) {
            BricklayerDirectTemplateRelationDO bricklayerDirectTemplateRelationDO = new BricklayerDirectTemplateRelationDO();
            bricklayerDirectTemplateRelationDO.setTemplateId(templateId);
            bricklayerDirectTemplateRelationDO.setBelongDirectId(parentId);
            bricklayerDirectTemplateRelationDO.setTemplateName(treeNodeDTO.getLabel());
            bricklayerDirectTemplateRelationDao.insert(bricklayerDirectTemplateRelationDO);
        }

        if ("file".equals(type)) {
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

        if (!LoginInterceptor.isCurrentUser(bricklayerProjectDTO.getCreateBy())) {
            //todo 其他人员项目无法编辑无法删除
            throw new MessageRuntimeException("无法删除其他用户创建项目");
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
        if (bricklayerProjectDTO.getOnlyMine() != null && bricklayerProjectDTO.getOnlyMine()) {
            bricklayerProjectDO.setCreateBy(LoginInterceptor.getCurrentName());
        }
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
                    if (z.getTemplateId() < 0) {
                        treeNodeDTO.setType("document");
                    } else {
                        treeNodeDTO.setType("file");
                    }
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