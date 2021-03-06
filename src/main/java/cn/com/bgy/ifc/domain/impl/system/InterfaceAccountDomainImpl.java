package cn.com.bgy.ifc.domain.impl.system;
import cn.com.bgy.ifc.dao.system.InterfaceAccountDao;
import cn.com.bgy.ifc.domain.interfaces.system.InterfaceAccountDomain;
import cn.com.bgy.ifc.entity.po.system.InterfaceAccount;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class InterfaceAccountDomainImpl implements InterfaceAccountDomain {
    @Resource
    private InterfaceAccountDao interfaceaccountDao;
    @Override
    public InterfaceAccount findById(Long id) {
        return interfaceaccountDao.findById(id);
    }

    @Override
    public int insert(InterfaceAccount interfaceaccount) {
        return interfaceaccountDao.insert(interfaceaccount);
    }

    @Override
    public int deleteById(Long id) {
        return interfaceaccountDao.deleteById(id);
    }

    /**
     * 批量删除用户接口
     * @param list
     */
    @Override
    public void deleteInterfaceAccount(List<Long> list) {
        interfaceaccountDao.deleteInterfaceAccount(list);
    }

    @Override
    public int update(InterfaceAccount interfaceaccount) {
        return interfaceaccountDao.update(interfaceaccount);
    }

    /**
     * 分页
     * @param page
     * @param interfaceAccount
     * @return
     */
    @Override
    public PageInfo<InterfaceAccount> searchByWhere(Page page,InterfaceAccount interfaceAccount) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<InterfaceAccount> interfaceaccountList = interfaceaccountDao.searchByWhere(interfaceAccount);
        PageInfo<InterfaceAccount> pageInfo = new PageInfo<>(interfaceaccountList);
        return pageInfo;
    }
}

