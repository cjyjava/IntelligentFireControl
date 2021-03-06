package cn.com.bgy.ifc.domain.impl.system;

import cn.com.bgy.ifc.bgy.constant.ExternalConstant;
import cn.com.bgy.ifc.bgy.constant.SystemConstant;
import cn.com.bgy.ifc.bgy.utils.DbUtil;
import cn.com.bgy.ifc.bgy.utils.ExceptionUtil;
import cn.com.bgy.ifc.bgy.utils.TimeUtil;
import cn.com.bgy.ifc.dao.system.AccountDao;
import cn.com.bgy.ifc.domain.interfaces.system.AccountDomain;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceMsgDomain;
import cn.com.bgy.ifc.entity.po.synchro.BgyAccount;
import cn.com.bgy.ifc.entity.po.system.Account;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.system.BgyUserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author zhangcheng
 */
@Service
public class AccountDomainImpl implements AccountDomain {

    private static Logger logger = LoggerFactory.getLogger(SystemOrganizationDomainImpl.class);
    @Resource
    private AccountDao accountDao;

    @Autowired
    private ExternalInterfaceMsgDomain externalInterfaceMsgDomain;

    @Override
    public int save(Account account) {
        return accountDao.insertSelective(account);
    }

    @Override
    public List<Account> searchByWhere(Account account) {
        return accountDao.searchByWhere(account);
    }

    @Override
    public PageInfo<Account> searchByPage(Page page, Account account) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<Account> accountList = this.searchByWhere(account);
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        return pageInfo;
    }

    @Override
    public int deleteById(Long id) {
        return accountDao.deleteById(id);
    }

    @Override
    public List<Account> queryListByParam(Account account) {
        return null;
    }

    @Override
    public List<Account> queryListByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public int insert(Account account) {
        return 0;
    }

    @Override
    public int insertSelective(Account account) {
        return 0;
    }

    @Override
    public int update(Account account) {
        return accountDao.updateById(account);
    }

    @Override
    public int updateSelective(Account account) {
        return accountDao.updateSelective(account);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return accountDao.deleteBatch(ids);
    }

    /**
     * 登录
     *
     * @param telephone
     * @return
     */
    @Override
    public Account findAccountByUserName(String telephone) {
        Account account = accountDao.findAccountByUserName(telephone);
        return account;
    }

    @Override
    public PageInfo<Account> findUserPowerByPage(Page<Account> page, Account account) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<Account> accountList = accountDao.findUserPowerByPage(account);
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        return pageInfo;
    }


    @Override
    public ResponseVO<Object> saveBgyAccountList(List<BgyUserVo> list, Long orgId) {
        try {
            List<BgyAccount> accountList = new ArrayList<>();
            Date createTime = new Date();
            for (BgyUserVo userVo : list) {
                BgyAccount account = new BgyAccount();
                //改为第三方用户ID
                account.setThirdUserId(userVo.getId());
                account.setSex(userVo.getSex());
                account.setOrganizationId(orgId);
                account.setDepartmentId(0L);
                account.setTelephone(userVo.getTelephone());
                account.setUserName(userVo.getUserName());
                account.setPassword(userVo.getPassword());
                account.setJobNumber(userVo.getJobNum());
                account.setIsDisable(userVo.getIsDisable());
                account.setIdentityNumber(userVo.getCreditNo());
                account.setRemark(userVo.getRemark());
                account.setRegistTime(createTime);
                account.getId();
                accountList.add(account);
            }
            int totalCount = DbUtil.insertByList("account", accountList);
            if (totalCount != accountList.size()) {
                return ResponseVO.error().setMsg("同步集成平台用户异常");
            } else {
                externalInterfaceMsgDomain.successInterfaceMsg(orgId, ExternalConstant.MsgTypeValue.BGY_ACCOUNT_OBTAIN.getValue(), totalCount);
                return ResponseVO.success().setMsg("同步集成平台用户总条数：" + totalCount + "，新增条数：" + totalCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
            }
        } catch (Exception e) {
            logger.error("同步集成平台用户异常",e);
            return ResponseVO.error().setMsg(ExceptionUtil.getExceptionMsg("同步集成平台用户异常",e));
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public ResponseVO<Object> alterBgyAccountList(List<BgyUserVo> list, Long orgId) {
        int addType = ExternalConstant.OperationType.ADD.getValue();
        int updateType = ExternalConstant.OperationType.UPDATE.getValue();
        int deleteType = ExternalConstant.OperationType.DELETE.getValue();
        int isDelete = SystemConstant.EnableState.DELETE.getValue();
        int totalCount = list.size();
        int addCount = 0;
        int updateCount = 0;
        int deleteCount = 0;
        Date createTime = new Date();
        for (BgyUserVo userVo : list) {
            Account account = getAccountByVo(userVo, orgId, createTime);
            int operType = userVo.getOperType();
            //新增
            if (operType == addType) {
                int count = accountDao.insertSelective(account);
                if (count == 1) {
                    addCount++;
                }
            }
            //修改
            if (operType == updateType) {
                int count = accountDao.updateByThirdId(account);
                if (count == 1) {
                    updateCount++;
                }
            }
            //删除
            if (operType == deleteType) {
                account.setIsDisable(isDelete);
                int count = accountDao.updateByThirdId(account);
                if (count == 1) {
                    deleteCount++;
                }
            }
        }
        if (addCount + updateCount + deleteCount != totalCount) {
            throw new RuntimeException("批量同步用户增量数据失败!");
        } else {
            Date updateTime = new Date();
            if (list.get(totalCount - 1).getOperTime() != null) {
                updateTime = TimeUtil.parseStrToDateTime(list.get(totalCount - 1).getOperTime());
            }
            int msgType = ExternalConstant.MsgTypeValue.BGY_ACCOUNT_OBTAIN.getValue();
            externalInterfaceMsgDomain.alterInterfaceMsg(orgId, msgType, totalCount, addCount, updateCount, deleteCount,updateTime);
            return ResponseVO.success().setMsg("同步集成平台用户总条数：" + totalCount + "，新增条数：" + addCount + ",修改条数：" + updateCount + ",删除条数：" + deleteCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
        }
    }

    private Account getAccountByVo(BgyUserVo userVo, Long orgId, Date createTime) {
        Account account = new Account();
        //改为第三方用户ID
        account.setThirdUserId(userVo.getId());
        account.setSex(userVo.getSex());
        account.setOrganizationId(orgId);
        account.setDepartmentId(0L);
        account.setTelephone(userVo.getTelephone());
        account.setUserName(userVo.getUserName());
        account.setPassword(userVo.getPassword());
        account.setJobNumber(userVo.getJobNum());
        account.setIsDisable(userVo.getIsDisable());
        account.setIdentityNumber(userVo.getCreditNo());
        account.setRemark(userVo.getRemark());
        account.setRegistTime(createTime);
        return account;
    }

    /**
     * @param idslist
     * @param isDisable
     * @return
     * @author chenlie
     * 批量更新用户状态
     */
    @Override
    public int updateIsDisable(List<Long> idslist, int isDisable) {

        return accountDao.updateIsDisable(idslist, isDisable);

    }

    @Override
    public int initalingPassword(Account account) {
        return accountDao.initalingPassword(account);
    }

    @Override
    public List<Account> queryListByRole(Map<String, Object> map) {
        return accountDao.queryListByRole(map);
    }

}
