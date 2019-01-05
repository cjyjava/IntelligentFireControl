package cn.com.bgy.ifc.domain.impl.fireinspection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.bgy.ifc.dao.fireinspection.FirePlanTestDao;
import cn.com.bgy.ifc.dao.system.UserGroupItemsDao;
import cn.com.bgy.ifc.domain.interfaces.fireinspection.FirePlanTestDomain;
import cn.com.bgy.ifc.entity.po.fireinspection.FirePlanTest;
import cn.com.bgy.ifc.entity.po.system.Account;
/**
 * 
 * @author 设施故障检测工作计划（主表）
 *
 */
@Service
public class FirePlanTestDomainImpl implements FirePlanTestDomain {
	@Resource
	private FirePlanTestDao dao;
	@Resource
	private UserGroupItemsDao userDao;
	@Override
	public List<FirePlanTest> queryListByParam(FirePlanTest t) {
		return dao.queryListByParam(t);
	}

	@Override
	public List<FirePlanTest> queryListByMap(Map<String, Object> map) {
		return dao.queryListByMap(map);
	}

	@Override
	public FirePlanTest findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public int insert(FirePlanTest t) {
		return dao.insert(t);
	}

	@Override
	public int insertSelective(FirePlanTest t) {
		return dao.insert(t);
	}

	@Override
	public int update(FirePlanTest t) {
		return dao.update(t);
	}

	@Override
	public int updateSelective(FirePlanTest t) {
		return dao.updateSelective(t);
	}

	@Override
	public int deleteBatch(List<Long> ids) {
		return dao.deleteBatch(ids);
	}

	@Override
	public PageInfo<FirePlanTest> getPageList(Page<FirePlanTest> page, FirePlanTest t, Account user) {
		if(user!=null) {
			List<Long>regionIds = userDao.queryRegionIdByUserId(user.getId());
			List<Long>projectIds = userDao.queryProjectIdByUserId(user.getId());
			page = PageHelper.startPage(page.getPageNum(), page.getPages(), page.getOrderBy());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("regionIds", regionIds);
			map.put("projectIds", projectIds);
			map.put("bean", t);
		}
		 List<FirePlanTest>list = dao.queryListByParam(t);//dao.queryListByMap(map);
		 PageInfo<FirePlanTest> info= new PageInfo<FirePlanTest>(list);
		return info;
	}

}