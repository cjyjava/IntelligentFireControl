package cn.com.bgy.ifc.controller.inner.maintenance;

import cn.com.bgy.ifc.bgy.annotation.RolePermission;
import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceCompanyDomain;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceContractDomain;
import cn.com.bgy.ifc.entity.po.maintenance.MaintenanceCompany;
import cn.com.bgy.ifc.entity.po.system.Account;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.maintenance.MaintenanceCompanyVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * lvbingjian
 * 维保公司控制层
 * 2018年12月20日
 */
@Controller
@RequestMapping("/maintenance/maintenanceCompany")
@RolePermission
public class MaintenanceCompanyController extends BaseController{
    @Autowired
    private MaintenanceCompanyDomain domain;
    @Autowired
    private MaintenanceContractDomain maintenanceContractDomain;

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("queryPageList")
    @ResponseBody
    public ResponseVO<Object> queryPageList(Page<MaintenanceCompany> page, MaintenanceCompany po) {
        //关键只查询暂时默认为公司名称的模糊查询
        PageInfo<MaintenanceCompany> pageInfo = domain.queryListByPage(page, po);
        return ResponseVO.success().setData(pageInfo);
    }

    /**
     * @Author lvbingjian
     * @Description 新增维保公司
     * @Date 2018年12月20日09:48:38
     */
    @PostMapping("add")
    @SystemLogAfterSave(description = "维保公司新增")
    @ResponseBody
    public ResponseVO<Object> add(@Validated MaintenanceCompanyVo vo, BindingResult error, String token) {
        //参数校检
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }

        MaintenanceCompany maintenanceCompany = new MaintenanceCompany();
        Account user= this.getUser();
        //默认登录人的机构
        vo.setOrganizationId(user.getOrganizationId());
        //当前系统时间为新建时间
        vo.setCreateTime(new Date());
        //默认是false删除后设为true
        vo.setLogicRemove(false);
        CopyUtil.copyProperties(vo, maintenanceCompany);
        int count = domain.addMaintenanceCompanyInfo(maintenanceCompany);
        if (count == 1) {
            return ResponseVO.success().setMsg("添加成功！");
        }
        return ResponseVO.error().setMsg("添加失败！");
    }
    /**
     * @Author lvbingjian
     * @Description 修改
     * @Date 2018年12月20日09:48:38
     */
    @PostMapping("update")
    @SystemLogAfterSave(description = "维保公司修改")
    @ResponseBody
    public ResponseVO<Object> updateRegionStreet(MaintenanceCompany po, String token){
        int resout = 1;
        //当前系统时间为新建时间
        po.setCreateTime(new Date());
        int count = domain.updateMaintenanceCompany(po);
        if (count == resout) {
            return ResponseVO.success().setMsg("修改成功");
        }
        return ResponseVO.error().setMsg("修改失败！");
    }

    /**
     * 通过ID查看详细信息
     * lbj
     * 2018年12月20日
     * @param id
     * @param token
     * @return
     */
    @GetMapping("queryById")
    @ResponseBody
    public ResponseVO<MaintenanceCompany> queryById( long id, String token) {
        MaintenanceCompany bean = domain.findById(id);

        return ResponseVO.<MaintenanceCompany>success().setData(bean);
    }
    /**
     * @Author lvbingjian
     * @Description 删除
     * @Date 2018/12/18 15:22
     */
    @PostMapping("delete")
    @SystemLogAfterSave(description = "维保公司删除")
    @ResponseBody
    public ResponseVO<Object> deleteRegionComputerRoom( String ids, String token){
    	ids = ids.replace("[", "") ;
    	ids = ids.replace("]", "") ;
        int count = domain.deleteMaintenanceCompanys(ids);
        if (count > 0) {
            return ResponseVO.success().setMsg("删除成功");
        }
        return ResponseVO.error().setMsg("删除失败！");
    }
    /**
     * 获取区域下拉框初始化
     * @return
     */
    @GetMapping("queryRegionList")
    @ResponseBody
    public ResponseVO<Object> queryRegionList() {
        return ResponseVO.success().setData(maintenanceContractDomain.getRegionList());
    }
}
