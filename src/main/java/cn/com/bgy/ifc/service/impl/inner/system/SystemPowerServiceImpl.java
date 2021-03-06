package cn.com.bgy.ifc.service.impl.inner.system;

import cn.com.bgy.ifc.dao.system.SystemPowerDao;
import cn.com.bgy.ifc.entity.po.system.SystemPower;
import cn.com.bgy.ifc.entity.vo.common.SelectVo;
import cn.com.bgy.ifc.service.interfaces.inner.system.SystemPowerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZhangCheng
 * @description:系统权限
 * @date: 2018-12-28 11:17
 **/
@Service
public class SystemPowerServiceImpl implements SystemPowerService {

    @Resource
    private SystemPowerDao systemPowerDao;

    @Override
    public SystemPower findById(Long id) {
        return systemPowerDao.findById(id);
    }

    @Override
    public int insert(SystemPower systemPower) {
        return systemPowerDao.insert(systemPower);
    }

    @Override
    public int insertSelective(SystemPower systemPower) {
        return systemPowerDao.insertSelective(systemPower);
    }

    @Override
    public int update(SystemPower systemPower) {
        return systemPowerDao.update(systemPower);
    }

    @Override
    public int updateSelective(SystemPower systemPower) {
        return systemPowerDao.updateSelective(systemPower);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return systemPowerDao.deleteBatch(ids);
    }

    @Override
    public PageInfo<SystemPower> queryListByPage(Page<SystemPower> page, String keywords) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<SystemPower> list = systemPowerDao.queryListByWhere(keywords);
        return new PageInfo<SystemPower>(list);
    }

    @Override
    public List<SystemPower> queryListByUserId(Long userId) {
        return systemPowerDao.queryListByUserId(userId);
    }

    @Override
    public List<SystemPower> queryListByWhere(String keyWords) {
        return systemPowerDao.queryListByWhere(keyWords);
    }

    @Override
    public List<SelectVo> getPowerConfig() {
        List<SelectVo> selectVos = new ArrayList<>();
        List<SystemPower> list = systemPowerDao.queryListByWhere(null);
        for (SystemPower systemPower : list) {
            SelectVo selectVo = new SelectVo();
            if (systemPower.getSystemMenu() == null){
                continue;
            }
            String name = "[" + systemPower.getSystemMenu().getName() + "]" + systemPower.getDisplayName();
            selectVo.setValue(String.valueOf(systemPower.getId()));
            selectVo.setName(name);
            selectVos.add(selectVo);
        }
        return selectVos;
    }
}
