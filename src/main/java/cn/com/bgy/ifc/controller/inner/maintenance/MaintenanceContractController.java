package cn.com.bgy.ifc.controller.inner.maintenance;

import cn.com.bgy.ifc.bgy.annotation.RolePermission;
import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.bgy.utils.excel.ExcelUtil;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceCompanyDomain;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceContractDomain;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceContractFileDomain;
import cn.com.bgy.ifc.entity.po.maintenance.MaintenanceContract;
import cn.com.bgy.ifc.entity.po.system.Account;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.maintenance.MaintenanceContractExcelModel;
import cn.com.bgy.ifc.entity.vo.maintenance.MaintenanceContractVo;
import com.alibaba.excel.support.ExcelTypeEnum;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * lvbingjian
 * 维保合同
 * 2018年12月20日
 */
@Controller
@RequestMapping("/maintenance/maintenanceContract")
@RolePermission
public class MaintenanceContractController extends BaseController {
    @Autowired
    private MaintenanceContractDomain maintenanceContractDomain;

    @Autowired
    private MaintenanceCompanyDomain maintenanceCompanyDomain;

    @Autowired
    private MaintenanceContractFileDomain maintenanceContractFileDomain;

    /**
     * 分页查询
     *
     * @param maintenanceContract
     * @return
     */
    @GetMapping("queryPageList")
    @ResponseBody
    public ResponseVO<Object> queryPageList(Page<MaintenanceContract> page, MaintenanceContract maintenanceContract) {
        //关键只查询暂时默认为合同名称的模糊查询
        PageInfo<MaintenanceContract> pageInfo = maintenanceContractDomain.queryListByPage(page, maintenanceContract);
        return ResponseVO.success().setData(pageInfo);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping("queryAllList")
    @ResponseBody
    public ResponseVO<Object> queryAllList() {
        return ResponseVO.success().setData(maintenanceContractDomain.queryListByParam(null));
    }

    /**
     * @Author lvbingjian
     * @Description 新增维保合同
     * @Date 2018年12月20日09:48:38
     */
    @PostMapping("add")
    @SystemLogAfterSave(description = "维保合同新增")
    @ResponseBody
    public ResponseVO<Object> add(@Validated MaintenanceContractVo maintenanceContractVo, BindingResult error ) {
        //参数校检
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }

        MaintenanceContract maintenanceContract = new MaintenanceContract();
        Account user = this.getUser();
        //默认登录人的机构
        maintenanceContractVo.setOrgId(user.getOrganizationId());
        //当前系统时间为新建时间
        maintenanceContractVo.setCreateTime(new Date());
        //默认是false删除后设为true
        maintenanceContractVo.setLogicRemove(false);
        CopyUtil.copyProperties(maintenanceContractVo, maintenanceContract);
        int count = maintenanceContractDomain.addMaintenanceContractInfo(maintenanceContract);
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
    @SystemLogAfterSave(description = "维保合同修改")
    @ResponseBody
    public ResponseVO<Object> updateRegionStreet(MaintenanceContract maintenanceContract) {
        int count = maintenanceContractDomain.updateMaintenanceContract(maintenanceContract);
        if (count >0) {
            return ResponseVO.success().setMsg("修改成功");
        }
        return ResponseVO.error().setMsg("修改失败！");
    }

    /**
     * 通过ID查看合同详细信息
     * lbj
     * 2018年12月20日
     *
     * @param id
     * @param token
     * @return
     */
    @GetMapping("queryById")

    @ResponseBody
    public ResponseVO<MaintenanceContract> queryById(long id, String token) {
        MaintenanceContract bean = maintenanceContractDomain.findById(id);

        return ResponseVO.<MaintenanceContract>success().setData(bean);
    }

    /**
     * 获取区域下拉框初始化
     *
     * @return
     */
    @GetMapping("queryRegionList")
    @ResponseBody
    public ResponseVO<Object> queryRegionList() {
        return ResponseVO.success().setData(maintenanceContractDomain.getRegionList());
    }

    /**
     * 项目下拉框初始化
     *
     * @return
     */
    @GetMapping("queryRegionProjectList")
    @ResponseBody
    public ResponseVO<Object> queryRegionProjectList() {
        return ResponseVO.success().setData(maintenanceContractDomain.getRegionProjectList());
    }

    /**
     * @Author lvbingjian
     * @Description 删除
     * @Date 2018年12月24日15:06:02
     */
    @PostMapping("delete")
    @SystemLogAfterSave(description = "维保合同删除")
    @ResponseBody
    public ResponseVO<Object> deleteRegionComputerRoom(String ids, String token) {
        ids = ids.replace("[", "");
        ids = ids.replace("]", "");
        int count = maintenanceContractDomain.deleteMaintenanceContracts(ids);
        if (count > 0) {
            return ResponseVO.success().setMsg("删除成功");
        }
        return ResponseVO.error().setMsg("删除失败！");
    }

    /**
     * 维保公司下拉框初始化
     *
     * @return
     */
    @GetMapping("querymaintenanceCompanyList")
    @ResponseBody
    public ResponseVO<Object> queryMaintenanceCompanyList() {
        return ResponseVO.success().setData(maintenanceCompanyDomain.queryListByParam(null));
    }

    @PostMapping("importContract")
    @ResponseBody
    public Object singleFileUpload(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }

        try {
            List<Object> list = ExcelUtil.readExcelWithModel(file.getInputStream(), MaintenanceContractExcelModel.class, ExcelTypeEnum.XLSX);
            for (Object obj:list){
                MaintenanceContractExcelModel model=(MaintenanceContractExcelModel)obj;

                MaintenanceContract record=new MaintenanceContract();
               /* record.setpId();
                record.setrId();
                record.setContractNo(model.getContractNum());
                record.setCompanyId();
                record.setContactPhone(model.getTelephone());
                record.setContractName(model.getName());
                record.setStartDate(model.getStartDay());
                record.setEndDate(model.getEndDay());*/
                //maintenanceContractDomain.addMaintenanceContractInfo(record,MaintenanceContractFile  maintenanceContractFile);
            }
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }
}

