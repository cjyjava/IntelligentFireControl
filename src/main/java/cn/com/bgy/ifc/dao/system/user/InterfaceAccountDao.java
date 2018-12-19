package cn.com.bgy.ifc.dao.system.user;

import cn.com.bgy.ifc.entity.po.system.user.InterfaceAccount;

import java.util.List;

public interface InterfaceAccountDao {
    InterfaceAccount findById(Long id);

    int insert(InterfaceAccount interfaceaccount);

    int deleteById(Long id);
    /**
     * 逻辑删除用户接口（批量）
     * @param longs
     */
    void  deleteInterfaceAccount(Long[] longs);

    int update(InterfaceAccount interfaceAccount);

    /**
     * 分页
     * @param interfaceAccount
     * @return
     */
    List<InterfaceAccount> searchByWhere(InterfaceAccount interfaceAccount);
}