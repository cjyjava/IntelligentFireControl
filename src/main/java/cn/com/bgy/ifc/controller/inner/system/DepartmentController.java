package cn.com.bgy.ifc.controller.inner.system;

import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.constant.SystemConstant;
import cn.com.bgy.ifc.bgy.utils.CopyUtil;
import cn.com.bgy.ifc.bgy.utils.ListUtil;
import cn.com.bgy.ifc.bgy.utils.TreeUtil;
import cn.com.bgy.ifc.controller.inner.common.BaseController;
import cn.com.bgy.ifc.domain.interfaces.system.DepartmentDomain;
import cn.com.bgy.ifc.entity.po.system.Account;
import cn.com.bgy.ifc.entity.po.system.Department;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.system.DepartmentVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: ZhangCheng
 * @description:部门管理
 * @date: 2018-12-05 09:30
 **/
@RestController
@RequestMapping("/system/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentDomain departmentDomain;

    /**
     * @author: ZhangCheng
     * @description:查询部门信息列表(分页)
     * @param: [page, departmentVo]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<com.github.pagehelper.PageInfo < cn.com.bgy.ifc.entity.po.basic.Department>>
     */
    @GetMapping("searchPage")
    public ResponseVO<PageInfo<Department>> queryListByPage(Page<Department> page, Department department) {
        PageInfo<Department> pageInfo = departmentDomain.queryListByPage(page, department);
        return ResponseVO.<PageInfo<Department>>success().setData(pageInfo);
    }

    /**
     * @author: ZhangCheng
     * @description:查询部门树
     * @param: []
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.util.List < cn.com.bgy.ifc.entity.vo.basic.DepartmentVo>>
     */
    @GetMapping("queryTree")
    public ResponseVO<List<DepartmentVo>> queryTree() {
        List<Department> list = departmentDomain.queryAllList();
        List<DepartmentVo> functionList = new ArrayList<DepartmentVo>();
        for (Department department : list) {
            DepartmentVo departmentVo = new DepartmentVo();
            CopyUtil.copyProperties(department, departmentVo);
            functionList.add(departmentVo);
        }
        List<DepartmentVo> treeList = TreeUtil.switchTree(functionList, 0L);
        return ResponseVO.<List<DepartmentVo>>success().setData(treeList);
    }

    @GetMapping("queryAllList")
    public ResponseVO<List<Department>> queryAllList() {
        List<Department> list = departmentDomain.queryAllList();
        return ResponseVO.<List<Department>>success().setData(list);
    }

    /**
     * @author: ZhangCheng
     * @description:上级部门查询
     * @param: []
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.util.List < cn.com.bgy.ifc.entity.po.basic.Department>>
     */
    @GetMapping("queryList")
    public ResponseVO<List<Department>> queryList(Department department) {
        List<Department> list = departmentDomain.queryListByParam(department);
        return ResponseVO.<List<Department>>success().setData(list);
    }

    @GetMapping("queryById")
    public ResponseVO<Object> queryById(Long id) {
        Department department = departmentDomain.findById(id);
        return ResponseVO.success().setData(department);
    }

    /**
     * YanXiaoLu
     * 根据当前登录用户添加部门
     *
     * @param departmentVo
     * @param error
     * @return
     */
    @PostMapping("add")
    @SystemLogAfterSave(description = "部门信息添加")
    public ResponseVO<Object> add(@Validated DepartmentVo departmentVo, BindingResult error) {
        Account user = this.getUser();
        Department department = new Department();
        CopyUtil.copyProperties(departmentVo, department);
        department.setOrganizationId(user.getOrganizationId());
        int count = departmentDomain.insert(department);
        if (count == 1) {
            return ResponseVO.addSuccess();
        } else if (count == 2) {
            return ResponseVO.addError().setMsg("添加的部门名已经存在！！请重新输入部门名!!");
        }
        return ResponseVO.addError();
    }

    /**
     * @author: YanXiaoLu
     * @description:根据登录用户查询部门（下拉框展示）
     * @param:
     * @return:
     */
    @GetMapping("findParentNameByUserId")
    public ResponseVO<List<Department>> findParentNameByUserId(Long userId) {
        DepartmentVo departmentVo = new DepartmentVo();
        departmentVo.setState(SystemConstant.EnableState.ENABLE.getValue());
        List<Department> list = departmentDomain.findParentNameByUserId(userId);
        return ResponseVO.<List<Department>>success().setData(list);
    }


    /**
     * @author: ZhangCheng
     * @description:部门信息修改
     * @param: [departmentVo, error]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("update")
    @SystemLogAfterSave(description = "部门信息修改")
    public ResponseVO<Object> update(@Validated DepartmentVo departmentVo,BindingResult error) {
        if (error.hasErrors()) {
            return ResponseVO.error().setMsg(error.getFieldError().getDefaultMessage());
        }
        if(departmentVo.getParentId().equals(departmentVo.getId())){
            return ResponseVO.error().setMsg("上级部门不能选择当前部门！");
        }
        Department department = new Department();
        CopyUtil.copyProperties(departmentVo, department);
        int count = departmentDomain.update(department);
        if (count > 0) {
            return ResponseVO.editSuccess();
        }
        return ResponseVO.editError();
    }

    /**
     * @author: ZhangCheng
     * @description:部门启用操作
     * @param: [departmentVo, error]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("forbidden")
    @SystemLogAfterSave(description = "部门启用操作")
    public ResponseVO<Object> forbidden(DepartmentVo departmentVo) {
        Department department = new Department();
        CopyUtil.copyProperties(departmentVo, department);
        int count = departmentDomain.update(department);
        if (count > 0) {
            return ResponseVO.success().setMsg("操作成功");
        }
        return ResponseVO.error().setMsg("操作失败！");
    }

    /**
     * @author: ZhangCheng
     * @description:部门信息删除
     * @param: [id]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @PostMapping("deleteData")
    @SystemLogAfterSave(description = "部门信息删除")
    public ResponseVO<Object> delete(String ids) {
        int count = departmentDomain.deleteBatch(ListUtil.getListId(ids));
        if (count > 0) {
            return ResponseVO.deleteSuccess();
        }
        return ResponseVO.deleteError();
    }

    /**
     * 查询所有部门
     *
     * @return
     */
    @GetMapping("getDpartmentName")
    public ResponseVO<Object> getDpartmentName() {
        List<Department> list = departmentDomain.queryAllList();
        return ResponseVO.success().setData(list);
    }


}
