package cn.com.bgy.ifc.controller.inner.fireinspection;
import java.util.ArrayList;
import java.util.List;

import cn.com.bgy.ifc.bgy.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.domain.interfaces.fireinspection.FireInspectionDetailDomain;
import cn.com.bgy.ifc.entity.po.fireinspection.FireInspectionDetail;
import cn.com.bgy.ifc.entity.po.system.Account;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.fireinspection.FireInspectionDetailVo;
/**
 * lvbingjian
 * 消防巡检明细
 * 2018年12月30日
 */
@Controller
@RequestMapping("/fireinspection/fireInspectionDetail")
public class FireInspectionDetailController extends BaseController{
	@Autowired
	private FireInspectionDetailDomain domain;
	/**
     * 分页查询
     *
     * @return
     */
    @GetMapping("queryPageData")
    @ResponseBody
    public ResponseVO<Object> queryPageList(Page<FireInspectionDetail> page, FireInspectionDetail po) {
    	//获取当前登录人做角色数据权限过滤
    	//Account user=this.getUser();
        PageInfo<FireInspectionDetail> pageInfo = domain.getPageList(page, po,null);
        return ResponseVO.success().setData(pageInfo);
    }
    /**
     * 查询全部
     * @return
     */
    @GetMapping("queryAllData")
    @ResponseBody
    public ResponseVO<Object> queryAllList() {
        return ResponseVO.success().setData(domain.queryListByParam(null));
    }
    /** chenlie
     * @Description 新增消防巡查明细
     * @Date 2018年12月30日15:12:24
     */
    @PostMapping("createData")
    @SystemLogAfterSave(description = "防火巡查明细新增")
    @ResponseBody
    public ResponseVO<Object> add(@Validated FireInspectionDetailVo vo, BindingResult error, String token) {
        //参数校检
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }

        FireInspectionDetail FireInspectionDetail = new FireInspectionDetail();
        //默认是false删除后设为true
        vo.setLogicRemove(false);
        CopyUtil.copyProperties(vo, FireInspectionDetail);
        int count = domain.insert(FireInspectionDetail);
        if (count == 1) {
            return ResponseVO.success().setMsg("添加成功！");
        }
        return ResponseVO.error().setMsg("添加失败！");
    }
    /**
     * @Author lvbingjian
     * @Description 修改
     * @Date 2018年12月30日15:12:24
     */
    @PostMapping("editData")
    //@RequiresRoles(value= {SystemConstant.SYSTEM_ROLES_ADMIN,SystemConstant.SYSTEM_ROLES_ADMIN},logical=Logical.OR)
    @SystemLogAfterSave(description = "防火巡查明细修改")
    @ResponseBody
    public ResponseVO<Object> updateRegionStreet(FireInspectionDetail po, String token){
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
     * 2018年12月30日15:12:24
     * @param id
     * @return
     */
    @GetMapping("findById")
    @ResponseBody
    public ResponseVO<FireInspectionDetail> queryById( long id) {
        FireInspectionDetail bean = domain.findById(id);

        return ResponseVO.<FireInspectionDetail>success().setData(bean);
    }
    /**
     * @Author chenlie
     * @Description 删除
     * @Date 2018年12月30日15:12:24
     */
    @PostMapping("deleteData")
    @SystemLogAfterSave(description = "防火巡查明细删除")
    @ResponseBody
    public ResponseVO<Object> deleteRegionComputerRoom( String ids){

    	List<Long>list = ListUtil.getListId(ids);

    	int	count = domain.deleteBatch(list);

        if (count > 0) {
            return ResponseVO.success().setMsg("删除成功");
        }
        return ResponseVO.error().setMsg("删除失败！");
    }


}
