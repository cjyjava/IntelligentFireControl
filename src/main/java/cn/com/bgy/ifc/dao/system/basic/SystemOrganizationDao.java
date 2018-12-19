package cn.com.bgy.ifc.dao.system.basic;
import cn.com.bgy.ifc.entity.po.system.basic.SystemOrganization;

import java.util.List;

public interface SystemOrganizationDao {
    SystemOrganization findById(Long id);

    int insert(SystemOrganization systemOrganization);

    int deleteById(Long id);

    int update(SystemOrganization systemOrganization);
    /**
     * 分页
     * @param interfaceAccount
     * @return
     */
    List<SystemOrganization> searchByWhere(SystemOrganization interfaceAccount);
}