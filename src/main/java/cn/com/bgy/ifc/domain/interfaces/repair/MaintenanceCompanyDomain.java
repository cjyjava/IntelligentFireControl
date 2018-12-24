package cn.com.bgy.ifc.domain.interfaces.repair;

import cn.com.bgy.ifc.entity.po.repair.MaintenanceCompany;
import cn.com.bgy.ifc.entity.vo.repair.MaintenanceCompanyVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * @Author lvbingjian
 * @Date 22018年12月20日16:18:17
 * @Description 维保公司管理
 **/
public interface MaintenanceCompanyDomain {
    /**
     * 分页查询维保公司
     * @param page
     * @param maintenanceCompany
     * @return
     */
    PageInfo<MaintenanceCompanyVo> queryListByPage(Page<MaintenanceCompanyVo> page, MaintenanceCompanyVo maintenanceCompany);

    /**
     * 列表查询
     * @param record
     * @return
     */
    List<MaintenanceCompanyVo> queryListByParam(MaintenanceCompanyVo record);

    /**
     * 添加
     * @param record set值
     * @return
     */
    int addMaintenanceCompanyInfo(MaintenanceCompany record);

    /**
     * 修改
     * @param record 维保公司修改字段
     * @return
     */
    int updateMaintenanceCompany( MaintenanceCompany record );

    /**
     *通过ID查询
     * @param （维保公司id）
     * @return
     */
    MaintenanceCompanyVo findById(Long id);

    /**
     * 批量删除
     * @param str（id逗号拼接）
     * @return
     */
    int deleteMaintenanceCompanys(String str);
}
