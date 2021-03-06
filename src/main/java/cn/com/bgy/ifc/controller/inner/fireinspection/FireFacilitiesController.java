package cn.com.bgy.ifc.controller.inner.fireinspection;

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
import cn.com.bgy.ifc.domain.interfaces.fireinspection.FireFacilitiesDomain;
import cn.com.bgy.ifc.entity.po.fireinspection.FireFacilities;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.fireinspection.FireFacilitiesVo;
/**
 * lvbingjian
 * 消防设施表  码表维护
 * 2019年1月3日
 */
@RestController
@RequestMapping("/fireinspection/fireFacilities")
public class FireFacilitiesController extends BaseController{
	@Autowired
	private FireFacilitiesDomain domain;
	/**
     * 分页查询
     *
     * @return
     */
    @GetMapping("queryPageData")
    public ResponseVO<Object> queryPageList(Page<FireFacilities> page, FireFacilities po) {
    	//获取当前登录人做角色数据权限过滤
    	//Account user=this.getUser();
        PageInfo<FireFacilities> pageInfo = domain.getPageList(page, po);
        return ResponseVO.success().setData(pageInfo);
    }
	 /**
     * 查询全部
     * @return
     */
    @GetMapping("queryAllData")
    public ResponseVO<Object> queryAllList() {
        return ResponseVO.success().setData(domain.queryListByParam(null));
    }
    /**
     * @Author lvbingjian
     * @Description 新增消防设施表  码表
     * @Date 2018年12月20日09:48:38
     */
    @PostMapping("createData")
    @SystemLogAfterSave(description = "消防设施表  码表新增")
    public ResponseVO<Object> add(@Validated FireFacilitiesVo vo, BindingResult error, String token) {
        //参数校检
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }

        FireFacilities fireFacilities = new FireFacilities();
        //默认是false删除后设为true
        vo.setLogicRemove(false);
        CopyUtil.copyProperties(vo, fireFacilities);
        int count = domain.insert(fireFacilities);
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
    @PostMapping("editData")
    @SystemLogAfterSave(description = "消防设施表  码表修改")
    public ResponseVO<Object> updateRegionStreet(FireFacilities po, String token){
        int resout = 1;
        int count = domain.update(po);
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
    public ResponseVO<FireFacilities> queryById( long id, String token) {
        FireFacilities bean = domain.findById(id);

        return ResponseVO.<FireFacilities>success().setData(bean);
    }
    /**
     * @Author lvbingjian
     * @Description 删除
     * @Date 2018/12/18 15:22
     */
    @PostMapping("deleteData")
    @SystemLogAfterSave(description = "消防设施表  码表删除")
    public ResponseVO<Object> deleteRegionComputerRoom( String arr){
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
