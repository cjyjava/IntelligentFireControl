package cn.com.bgy.ifc.domain.interfaces.system.user;

import cn.com.bgy.ifc.entity.po.basic.Department;
import cn.com.bgy.ifc.entity.vo.basic.DepartmentVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DepartmentDomain {

    PageInfo<Department> queryListByPage(Page<Department> page, DepartmentVo departmentVo);

    List<Department> queryListByParam(DepartmentVo departmentVo);

    List<Department> queryAllList();

    Department findById(Long id);

    int deleteById(Long id);

    int insert(Department department);

    int update(Department department);

    int deleteBatchById(List<Long> ids);

}