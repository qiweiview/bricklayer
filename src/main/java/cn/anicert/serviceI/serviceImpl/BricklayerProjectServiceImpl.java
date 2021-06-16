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
import cn.anicert.model.dto.GenerateCodeDTO;
import cn.anicert.model.dto.TreeNodeDTO;
import cn.anicert.model.vo.GenerationVO;
import cn.anicert.serviceI.BricklayerProjectServiceI;
import cn.anicert.utils.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


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


    private TreeNodeDTO directListToTree(List<BricklayerDirectDO> bricklayerDirectDOS) {
        Map<Integer, TreeNodeDTO> map = new HashMap<>();

        TreeNodeDTO[] root = {new TreeNodeDTO()};
        bricklayerDirectDOS.forEach(x -> {
            Integer id = x.getId();
            TreeNodeDTO treeNodeDTO = map.get(id);
            if (treeNodeDTO == null) {
                treeNodeDTO = new TreeNodeDTO();
                treeNodeDTO.setId(id);
                map.put(id, treeNodeDTO);
            }
            treeNodeDTO.setLabel(x.getDirectName());
            treeNodeDTO.setType("direct");
            Integer parentDirectId = x.getParentDirectId();
            if (-1 == parentDirectId) {
                treeNodeDTO.setLevel("root");
                root[0] = treeNodeDTO;
            } else {
                treeNodeDTO.setLevel("son");
            }

            TreeNodeDTO parent = map.get(parentDirectId);
            if (parent == null) {
                parent = new TreeNodeDTO();
                parent.setId(parentDirectId);
                map.put(parentDirectId, parent);
            }
            parent.addChild(treeNodeDTO);

        });
        return root[0];
    }

    private void insertDirectByTree(TreeNodeDTO treeNodeDTO, Map<Integer, Integer> directMap, Integer projectId, Integer parentId) {
        Integer oldId = treeNodeDTO.getId();
        BricklayerDirectDO bricklayerDirectDO = new BricklayerDirectDO();
        bricklayerDirectDO.setBelongProjectId(projectId);
        bricklayerDirectDO.setDirectName(treeNodeDTO.getLabel());
        bricklayerDirectDO.setParentDirectId(parentId);
        bricklayerDirectDO.doInit();
        bricklayerDirectDO.setBelongProjectId(projectId);
        bricklayerDirectDao.insert(bricklayerDirectDO);
        Integer newId = bricklayerDirectDO.getId();
        directMap.put(oldId, newId);
        List<TreeNodeDTO> children = treeNodeDTO.getChildren();
        children.forEach(x -> {
            insertDirectByTree(x, directMap, projectId, newId);
        });


    }

    @Transactional
    @Override
    public void copyBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {

        //保存项目
        BricklayerProjectDTO saveBricklayerProject = saveBricklayerProject(bricklayerProjectDTO);

        //查询目录
        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectDTO.getId());


        //转换id
        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> {
            //todo
            return x.getId();
        }).collect(Collectors.toList());

        //目录模板关系
        List<BricklayerDirectTemplateRelationDO> bricklayerDirectTemplateRelationDOS = bricklayerDirectTemplateRelationDao.listBricklayerDirectTemplateRelationByDirectIds(collect);

        //查询模板
        List<BricklayerTemplateDO> bricklayerTemplateDOS = bricklayerTemplateDao.listBricklayerTemplatesByDirectIds(collect);

        TreeNodeDTO integerTreeNodeDTOMap = directListToTree(bricklayerDirectDOS);

        Map<Integer, Integer> directMap = new HashMap<>();


        //保存目录
        insertDirectByTree(integerTreeNodeDTOMap, directMap, saveBricklayerProject.getId(), -1);


//        //保存目录
//        bricklayerDirectDOS.forEach(x -> {
//            Integer oldId = x.getId();
//            x.setId(null);
//            x.doInit();
//            x.setBelongProjectId(saveBricklayerProject.getId());
//            bricklayerDirectDao.insert(x);
//            Integer newId = x.getId();
//            directMap.put(oldId, newId);
//        });


        //分组模板
        Map<Integer, List<BricklayerTemplateDO>> collect1 = bricklayerTemplateDOS.stream().collect(Collectors.groupingBy(x -> x.getBelongDirectId()));


        Map<Integer, Integer> templateMap = new HashMap<>();

        //保存模板
        collect1.forEach((k, v) -> {
            Integer newId = directMap.get(k);
            v.forEach(x -> {
                Integer oldTemplateId = x.getId();
                x.setBelongDirectId(newId);
                x.doInit();
                x.setId(null);
                bricklayerTemplateDao.insert(x);
                Integer newTemplateId = x.getId();
                templateMap.put(oldTemplateId, newTemplateId);
            });
        });


        //保存新的文件夹模板关系
        bricklayerDirectTemplateRelationDOS.forEach(x -> {
            Integer templateId = x.getTemplateId();
            if (templateId != null) {
                templateId = templateMap.get(templateId);
            }


            Integer belongDirectId = x.getBelongDirectId();
            belongDirectId = directMap.get(belongDirectId);

            x.doInit();
            x.setTemplateId(templateId);
            x.setBelongDirectId(belongDirectId);
            x.setId(null);
            bricklayerDirectTemplateRelationDao.insert(x);


        });

    }


    @Transactional
    @Override
    public BricklayerProjectDTO updateBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDTO bricklayerProjectById = getBricklayerProjectById(bricklayerProjectDTO);


        if (!LoginInterceptor.isCurrentUser(bricklayerProjectById.getCreateBy())) {
            //todo 其他人员项目无法编辑无法编辑
            throw new MessageRuntimeException("无法编辑其他用户创建项目,可点击\"复制\"，复制项目至当前用户");
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

        //解析保存树
        parseTreeNodeDTO(tree, bricklayerProjectDTO.getId(), -1, "");
        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

    /**
     * 解析保存树
     *
     * @param treeNodeDTO
     * @param projectId
     * @param parentId
     * @param parentPath
     */
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


        //删除相关目录模板关系
        bricklayerDirectTemplateRelationDao.deleteDirectTemplateRelationByProjectId(bricklayerProjectDO.getId());


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

        List<BricklayerDirectTemplateRelationDO> bricklayerDirectTemplateRelationDOS = bricklayerDirectTemplateRelationDao.listBricklayerDirectTemplateRelationByDirectIds(collect);

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


    @Override
    public GenerationVO exportProject(GenerateCodeDTO generateCodeDTO) {


        ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream zipOutputStream = new ZipOutputStream(zipStream);


        BricklayerProjectDO bricklayerProjectDO = new BricklayerProjectDO();
        bricklayerProjectDO.setId(generateCodeDTO.getProjectId());
        BricklayerProjectDO bricklayerProjectById = bricklayerProjectDao.getBricklayerProjectById(bricklayerProjectDO);

        if (bricklayerProjectById == null) {
            throw new MessageRuntimeException("not found data");
        }


        //get directs by project id
        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectById.getId());

        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> x.getId()).collect(Collectors.toList());

        if (collect.size() > 0) {
            //get template by direct
            List<BricklayerTemplateDO> bricklayerTemplateDOS = bricklayerTemplateDao.listBricklayerTemplatesByDirectIds(collect);


            //目录分组
            Map<Integer, List<BricklayerTemplateDO>> directMap = bricklayerTemplateDOS.stream().collect(Collectors.groupingBy(BricklayerTemplateDO::getBelongDirectId));


            //写目录
            bricklayerDirectDOS.forEach(x -> {
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(x.getDirectFullPath() + "/"));
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    throw new MessageRuntimeException("export template fail cause:" + e.getMessage());
                }
            });

            //写文件
            bricklayerDirectDOS.forEach(x -> {
                List<BricklayerTemplateDO> bricklayerTemplateDOS1 = directMap.get(x.getId());
                if (bricklayerTemplateDOS1 != null) {

                    //循环模板
                    bricklayerTemplateDOS1.forEach(y -> {
                        String name = x.getDirectFullPath() + "/" + y.getTemplateName();
                        try {
                            zipOutputStream.putNextEntry(new ZipEntry(name.substring(1)));
                            zipOutputStream.write(y.getTemplateContent().getBytes());
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            throw new MessageRuntimeException("export template fail cause:" + e.getMessage());
                        }

                    });

                }

            });
        }


        try {
            zipOutputStream.putNextEntry(new ZipEntry("project_info.json"));
            String s = JacksonUtils.obj2Str(bricklayerProjectById);
            zipOutputStream.write(s.getBytes());
            zipOutputStream.closeEntry();


            LocalDateTime now = LocalDateTime.now();
            zipOutputStream.putNextEntry(new ZipEntry("description.md"));
            String description = "bricklayer项目版本号：" + VersionTag.VERSION + "\n\r" +
                    "导出用户：" + LoginInterceptor.getCurrentName() + "\n\r" +
                    "导出日期：" + now + "\n\r";
            zipOutputStream.write(description.getBytes());
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            throw new MessageRuntimeException("export template fail cause:" + e.getMessage());
        }


        try {
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        GenerationVO generationVO = new GenerationVO();

        generationVO.setData(zipStream.toByteArray());
        generationVO.setFileName("template_export.bricklayer.exp");

        return generationVO;
    }


    public static void main(String[] args) throws
            Exception {

        byte[] bytes = FileUtils.readFileToByteArray(new File("C:\\Users\\刘启威\\Desktop\\template_export.bricklayer.exp"));


        Map<String, TreeNodeDTO> treeNodeDTOMap = new HashMap();
        BricklayerProjectDO bricklayerProjectDO = null;

        TreeNodeDTO root = new TreeNodeDTO();
        root.setLabel("/");
        root.setType("root");


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ZipInputStream zipStream = new ZipInputStream(byteArrayInputStream);
        ZipEntry entry = null;
        try {
            while ((entry = zipStream.getNextEntry()) != null) {

                String name = entry.getName();
                if (entry.isDirectory()) {
                    //todo 目录
                    name = name.substring(1, name.length() - 1);

                    String[] split = name.trim().split("/");
                    if (split.length > 0) {
                        String son = split[split.length - 1];
                        if ("".equals(son)) {
                            continue;
                        }

                        TreeNodeDTO sonNode = new TreeNodeDTO();
                        sonNode.setType("direct");
                        sonNode.setLabel(son);
                        treeNodeDTOMap.put(son, sonNode);

                        if (split.length > 1) {
                            String father = split[split.length - 2];
                            TreeNodeDTO fatherNode = treeNodeDTOMap.get(father);
                            if (fatherNode == null) {
                                fatherNode = new TreeNodeDTO();
                                fatherNode.setType("direct");
                                fatherNode.setLabel(father);

                                treeNodeDTOMap.put(father, fatherNode);
                            }
                            fatherNode.addChild(sonNode);

                        } else if (split.length == 1) {
                            root.addChild(sonNode);
                            treeNodeDTOMap.put(son, sonNode);
                        }
                    }


                } else {
                    //todo 文件

                    byte[] mb10 = new byte[5 * 1024 * 1024];
                    int read = zipStream.read(mb10);

                    String content = "";
                    if (read != -1) {
                        byte[] readResult = Arrays.copyOfRange(mb10, 0, read);
                        content = new String(readResult);
                    }

                    if ("project_info.json".equals(name)) {
                        bricklayerProjectDO = JacksonUtils.str2obj(content, BricklayerProjectDO.class);
                        System.out.println(bricklayerProjectDO);
                        continue;
                    }

                    String[] split = name.split("/");
                    if (split.length > 1) {
                        String fileName = split[split.length - 1];
                        String belongDirect = split[split.length - 2];
                        TreeNodeDTO treeNodeDTO = treeNodeDTOMap.get(belongDirect);
                        if (treeNodeDTO != null) {


                            TreeNodeDTO fileTemplate = new TreeNodeDTO();
                            fileTemplate.setLabel(fileName);
                            fileTemplate.setType("file");
                            //单文件5mb读取

                            treeNodeDTO.addChild(fileTemplate);
                        }

                    }
                }


            }
            zipStream.close();
        } catch (Exception e) {
            throw new RuntimeException("to zip fail cause:" + e.getMessage());
        }

        System.out.println(JacksonUtils.obj2Str(root));


//        bricklayerProjectDO.doInit();
//        //重新生成主键
//        bricklayerProjectDO.setId(null);
//        bricklayerProjectDao.insert(bricklayerProjectDO);
//
//        //解析保存树
//        parseTreeNodeDTO(tree, bricklayerProjectDO.getId(), -1, "");

    }

    @Transactional
    @Override
    public void importProject(byte[] bytes) {

        Map<String, TreeNodeDTO> treeNodeDTOMap = new HashMap();
        TreeNodeDTO tree = new TreeNodeDTO();
        BricklayerProjectDO bricklayerProjectDO = null;


        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ZipInputStream zipStream = new ZipInputStream(byteArrayInputStream);
        ZipEntry entry = null;
        try {
            while ((entry = zipStream.getNextEntry()) != null) {

                String name = entry.getName();
                if (entry.isDirectory()) {
                    //todo 目录
                    name = name.substring(0, name.length() - 1);
                    String[] split = name.split("/");
                    for (int i = 0; i < split.length; i++) {

                    }

                } else {
                    //todo 文件

                    //单文件5mb读取
//                    byte[] mb10 = new byte[5 * 1024 * 1024];
//                    int read = zipStream.read(mb10);
//
//                    if (read != -1) {
//                        byte[] readResult = Arrays.copyOfRange(mb10, 0, read);
//                        System.out.println("file: \n" + new String(readResult));
//                    }

                    System.out.println("file: " + name);
                }


            }
            zipStream.close();
        } catch (Exception e) {
            throw new RuntimeException("to zip fail cause:" + e.getMessage());
        }

        if (bricklayerProjectDO == null) {
            throw new RuntimeException("can not found description file");
        }

//        bricklayerProjectDO.doInit();
//        //重新生成主键
//        bricklayerProjectDO.setId(null);
//        bricklayerProjectDao.insert(bricklayerProjectDO);
//
//        //解析保存树
//        parseTreeNodeDTO(tree, bricklayerProjectDO.getId(), -1, "");

    }

}