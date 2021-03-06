package cn.com.bgy.ifc.domain.impl.maintenance;

import cn.com.bgy.ifc.bgy.constant.ExternalConstant;
import cn.com.bgy.ifc.bgy.utils.DbUtil;
import cn.com.bgy.ifc.bgy.utils.TimeUtil;
import cn.com.bgy.ifc.dao.maintenance.MaintenanceCompanyDao;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceCompanyDomain;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceMsgDomain;
import cn.com.bgy.ifc.entity.po.maintenance.MaintenanceCompany;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.maintenance.BgyMaintenanceCompanyVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author lvbingjian
 * @Date 2018年12月20日
 * @Description 维保公司管理（维保）
 **/
@Service
public class MaintenanceCompanyDomainImpl implements MaintenanceCompanyDomain {
    @Resource
    private MaintenanceCompanyDao dao;

    @Autowired
    private ExternalInterfaceMsgDomain externalInterfaceMsgDomain;

    /**
     * 分页查询
     * @param page
     * @param maintenanceCompany
     * @return
     */
    @Override
    public PageInfo<MaintenanceCompany> queryListByPage(Page<MaintenanceCompany> page, MaintenanceCompany maintenanceCompany) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<MaintenanceCompany> list = dao.queryListByParam(maintenanceCompany);
        PageInfo<MaintenanceCompany> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 列表查询
     * @param record
     * @return
     */
    @Override
    public List<MaintenanceCompany> queryListByParam(MaintenanceCompany record) {
        return dao.queryListByParam(record);
    }

    @Override
    public int addMaintenanceCompanyInfo(MaintenanceCompany record) {
        return dao.insertSelective(record);
    }

    @Override
    public int updateMaintenanceCompany(MaintenanceCompany record) {
        return dao.update(record);
    }

    @Override
    public MaintenanceCompany findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public int deleteMaintenanceCompanys(String str) {
        List<Long> list = new ArrayList<>();
        String [] arr= str.split(",");
        if(arr.length>0){
            for (int i = 0; i <arr.length ; i++) {
                list.add(Long.valueOf(arr[i]));
            }
            return dao.delete(list);
        }else{
            return 0;
        }
    }

    @Override
    public ResponseVO<Object> saveBgyRepairCompany(List<BgyMaintenanceCompanyVo> list, Long orgId) {
        try{
            List<MaintenanceCompany> companyList=new ArrayList<>();
            Date createTime=new Date();
            for(BgyMaintenanceCompanyVo companyVo:list){
                MaintenanceCompany company=new MaintenanceCompany();
                company.setId(companyVo.getId());
                company.setCompanyName(companyVo.getName());
                company.setCreateTime(createTime);
                company.setOrganizationId(orgId);
                company.setRegionId(companyVo.getAreaId());
                company.setTelephone(companyVo.getTelephone());
                company.setLogicRemove(false);
                companyList.add(company);
            }
            int totalCount = DbUtil.insertByList("maintenance_company", list);
            if (totalCount != companyList.size()) {
            return ResponseVO.error().setMsg("同步集成平台维保公司异常");
        } else {
            externalInterfaceMsgDomain.successInterfaceMsg(orgId, ExternalConstant.MsgTypeValue.BGY_REPAIR_COMPANY_OBTAIN.getValue(), totalCount);
            return ResponseVO.success().setMsg("同步集成平台维保公司总条数：" + totalCount + "，新增条数：" + totalCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
        }
        } catch (Exception e) {
            return ResponseVO.error().setMsg("同步集成平台维保公司异常");
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public ResponseVO<Object> alterBgyRepairCompany(List<BgyMaintenanceCompanyVo> list, Long orgId) {
        int addType = ExternalConstant.OperationType.ADD.getValue();
        int updateType = ExternalConstant.OperationType.UPDATE.getValue();
        int deleteType = ExternalConstant.OperationType.DELETE.getValue();
        int totalCount = list.size();
        int addCount = 0;
        int updateCount = 0;
        int deleteCount = 0;
        Date createTime = new Date();
        for(BgyMaintenanceCompanyVo companyVo:list){
            MaintenanceCompany company=new MaintenanceCompany();
            company.setId(companyVo.getId());
            company.setCompanyName(companyVo.getName());
            company.setCreateTime(createTime);
            company.setOrganizationId(orgId);
            company.setRegionId(companyVo.getAreaId());
            company.setTelephone(companyVo.getTelephone());
            int operType = companyVo.getOperType();
            //新增
            if (operType == addType) {
                int count = dao.insertSelective(company);
                if (count == 1) {
                    addCount++;
                }
            }
            //修改
            if (operType == updateType) {
                int count = dao.updateSelective(company);
                if (count == 1) {
                    updateCount++;
                }
            }
            //删除
            if (operType == deleteType) {
                company.setLogicRemove(true);
                int count = dao.updateSelective(company);
                if (count == 1) {
                    deleteCount++;
                }
            }
        }
        if (addCount + updateCount + deleteCount != totalCount) {
            throw new RuntimeException("批量同步维保公司增量数据失败!");
        } else {
            Date updateTime = new Date();
            if (list.get(totalCount - 1).getOperTime() != null) {
                updateTime = TimeUtil.parseStrToDateTime(list.get(totalCount - 1).getOperTime());
            }
            int msgType = ExternalConstant.MsgTypeValue.BGY_REPAIR_COMPANY_OBTAIN.getValue();
            externalInterfaceMsgDomain.alterInterfaceMsg(orgId, msgType, totalCount, addCount, updateCount, deleteCount,updateTime);
            return ResponseVO.success().setMsg("同步集成平台维保公司总条数：" + totalCount + "，新增条数：" + addCount + ",修改条数：" + updateCount + ",删除条数：" + deleteCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
        }
    }
}
