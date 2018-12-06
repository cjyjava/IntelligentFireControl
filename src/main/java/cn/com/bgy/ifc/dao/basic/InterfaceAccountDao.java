package cn.com.bgy.ifc.dao.basic;

import cn.com.bgy.ifc.entity.po.basic.InterfaceAccount;

import java.util.List;

public interface InterfaceAccountDao {
    InterfaceAccount findById(Long id);

    int insert(InterfaceAccount interfaceaccount);

    int deleteById(Long id);

    int update(InterfaceAccount interfaceAccount);

    /**
     * 分页
     * @param interfaceAccount
     * @return
     */
    List<InterfaceAccount> searchByWhere(InterfaceAccount interfaceAccount);
}