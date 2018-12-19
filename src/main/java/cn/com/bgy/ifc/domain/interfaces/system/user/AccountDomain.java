package cn.com.bgy.ifc.domain.interfaces.system.user;

import cn.com.bgy.ifc.entity.po.system.user.Account;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.projects.BgyUserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountDomain {
    /**
     * 保存用户
     * @param account
     * @return
     */
    int save(Account account);

    /**
     * 条件查找用户
     * @param account
     * @return
     */
    List<Account> searchByWhere(Account account);

    /**
     * 分页查询用户
     * @param page
     * @param account
     * @return
     */
    PageInfo<Account> searchByPage(Page page, Account account);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    Account findById(Long id);

    /**
     * 根据id更新用户
     * @param account
     * @return
     */
    int update(Account account);

    /**
     * 根据电话称查询用户对象
     * @param telephone
     * @return
     */
    Account findAccountByUserName(String telephone);

    /**
     * 根据用户名查询用户
     * @param page
     * @param account
     * @return
     */
    public PageInfo<Account> findUserPowerByPage(Page<Account> page, Account account);

    /**
     * 保存集成平台用户
     * @param bgyUserVo
     * @return
     */
    int saveBgyAccount(BgyUserVo bgyUserVo);

    /**
     *
     * @param list
     * @return
     */
    ResponseVO<Object> saveBgyAccountList(List<BgyUserVo> list, Long orgId);

}