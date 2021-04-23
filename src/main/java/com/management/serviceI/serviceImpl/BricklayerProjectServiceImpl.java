package com.management.serviceI.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.management.dao.BricklayerDirectDao;
import com.management.dao.BricklayerDirectTemplateRelationDao;
import com.management.dao.BricklayerProjectDao;
import com.management.model.d_o.BricklayerDirectDO;
import com.management.model.d_o.BricklayerProjectDO;
import com.management.model.dto.BricklayerProjectDTO;
import com.management.serviceI.BricklayerProjectServiceI;
import com.management.utils.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        getBricklayerProjectById(bricklayerProjectDTO);
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO.doUpdate();
        bricklayerProjectDao.updateById(bricklayerProjectDO);

        //全删
        List<BricklayerDirectDO> bricklayerDirectDOS = bricklayerDirectDao.listBricklayerDirectsByProjectId(bricklayerProjectDO.getId());

        List<Integer> collect = bricklayerDirectDOS.stream().map(x -> {
            return x.getId();
        }).collect(Collectors.toList());

    //    bricklayerDirectTemplateRelationDao.deleteByDirectIds(collect);

        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

    public static void main(String[] args) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        //params.add(compInfoServ.getDataDate());
        sql.append("select t.custName,t.certId,ROUND(sum(t.TOTAL_COUNT)/count(1),0) as dayMeanCount,max(t.TOTAL_COUNT) as dayConcCount from ( ");
        sql.append("select b.cust_name as custName,a.cert_id as certId,substr(a.DATA_DATE,0,6) as month,a.DATA_DATE,sum(a.TOTAL_COUNT) as TOTAL_COUNT from SUMMARY_DAY a,cust_certificate b,cust c ");
        sql.append("where a.cert_id = b.certificate and b.cust_id = c.cust_id and a.BIZ_TYPE='id_auth' and a.auth_mode in ('0x10','0x12','0x40','0x42','0x68','0x65','0x66') ");
        sql.append("GROUP BY b.cust_name,a.cert_id,substr(a.DATA_DATE,0,6),a.DATA_DATE order by TOTAL_COUNT desc) t ");
        sql.append("GROUP BY t.custName,t.certId ");
        sql.append("order by dayMeanCount desc ");
        System.out.println(sql);
    }

    @Override
    public BricklayerProjectDTO deleteBricklayerProject(BricklayerProjectDTO bricklayerProjectDTO) {
        bricklayerProjectDTO = getBricklayerProjectById(bricklayerProjectDTO);
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO.doDelete();
        bricklayerProjectDao.deleteBricklayerProject(bricklayerProjectDO);
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
    public BricklayerProjectDTO getBricklayerProjectById(BricklayerProjectDTO bricklayerProjectDTO) {
        BricklayerProjectDO bricklayerProjectDO = bricklayerProjectDTO.toBricklayerProjectDO();
        bricklayerProjectDO = bricklayerProjectDao.getBricklayerProjectById(bricklayerProjectDO);
        if (bricklayerProjectDO == null) {
            throw new DataNotFoundException();
        }
        return bricklayerProjectDO.toBricklayerProjectDTO();
    }

}