package cn.com.bgy.ifc.controller.inner.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.constant.SystemConstant;
import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.domain.interfaces.maintenance.MaintenanceOrderItemDomain;
import cn.com.bgy.ifc.entity.po.maintenance.MaintenanceOrderItem;
import cn.com.bgy.ifc.entity.po.system.Account;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.maintenance.MaintenanceOrderItemVo;
/**
 * @author lvbingjian
 * 维保工单项目结果---控制层
 * 2018年12月26日15:35:25
 */
@RestController
@RequestMapping("/maintenance/maintenanceOrderItem")
public class MaintenanceOrderItemController extends BaseController{
	@Autowired
	private MaintenanceOrderItemDomain domain;

    /**
     *
     * @param page
     * @param po
     * @return
     */
    @GetMapping("queryPageList")
    public ResponseVO<Object> queryPageList(Page<MaintenanceOrderItem> page, MaintenanceOrderItem po) {
        //关键只查询暂时默认为公司名称的模糊查询
        PageInfo<MaintenanceOrderItem> pageInfo = domain.queryListByPage(page, po);
        return ResponseVO.success().setData(pageInfo);
    }
    /**
     * 查询全部
     * @return
     */
    @GetMapping("queryAllList")
    public ResponseVO<Object> queryAllList() {
        return ResponseVO.success().setData(domain.queryListByParam(null));
    }
    /**
     * @Author lvbingjian
     * @Description 新增维保公司
     * @Date 2018年12月20日09:48:38
     */
    @PostMapping("add")
    @SystemLogAfterSave(description = "维保工单项目新增")
    public ResponseVO<Object> add(@Validated MaintenanceOrderItemVo vo, BindingResult error) {
        //参数校检
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }

        MaintenanceOrderItem maintenanceOrderItem = new MaintenanceOrderItem();
        //默认是false删除后设为true
        
        vo.setLogicRemove(false);
       
        CopyUtil.copyProperties(vo, maintenanceOrderItem);
        int count = domain.insert(maintenanceOrderItem);
        if (count == 1) {
            return ResponseVO.addSuccess();
        }
        return ResponseVO.addError();
    }
    /**
     * @Author lvbingjian
     * @Description 修改
     * @Date 2018年12月20日09:48:38
     */
    @PostMapping("update")
    @SystemLogAfterSave(description = "维保工单项目修改")
    public ResponseVO<Object> updateRegionStreet(MaintenanceOrderItem po){
        int resout = 1;
        int count = domain.update(po);
        if (count == resout) {
            return ResponseVO.editSuccess();
        }
        return ResponseVO.editError();
    }

    /**
     * 通过ID查看详细信息
     * lbj
     * 2018年12月20日
     * @param id
     * @return
     */
    @GetMapping("queryById")
    public ResponseVO<MaintenanceOrderItem> queryById(Long id) {
        MaintenanceOrderItem bean = domain.findById(id);
        return ResponseVO.<MaintenanceOrderItem>success().setData(bean);
    }
    /**
     * @Author lvbingjian
     * @Description 删除
     * @Date 2018/12/18 15:22
     */
    @PostMapping("delete")
    @SystemLogAfterSave(description = "维保工单项目删除")
    public ResponseVO<Object> deleteRegionComputerRoom(String arr){
    	String []ids = arr.split(",");
    	List<Long>list = new ArrayList<Long>();
    	int count;
    	if(ids.length>0) {
    		for (int i = 0; i < ids.length; i++) {
    			list.add(Long.valueOf(ids[i]));
			}
    		count = domain.deleteBatch(list);
    	}else {
    		count = 0;
    	}
        if (count > 0) {
            return ResponseVO.success().setMsg("删除成功");
        }
        return ResponseVO.error().setMsg("删除失败！");
    }
	

}
