package cn.com.bgy.ifc.dao.system.basic;

import cn.com.bgy.ifc.entity.po.system.basic.SystemRole;
import cn.com.bgy.ifc.entity.vo.system.basic.SystemRoleVo;

import java.util.List;

public interface SystemRoleDao {


    List<SystemRole> queryAllList(SystemRoleVo systemRoleVo);

    List<SystemRole> queryListByParam(SystemRole record);

    SystemRole findById(Long id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    int updateRole(SystemRole record);

    int deleteById(Long id);

    int deleteRole(List<Long> list);

     List<SystemRole> queryListByUserId(Long userId);



}