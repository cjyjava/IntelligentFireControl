package cn.com.bgy.ifc.domain.impl.project;

import cn.com.bgy.ifc.bgy.constant.ExternalConstant;
import cn.com.bgy.ifc.bgy.utils.DbUtil;
import cn.com.bgy.ifc.bgy.utils.ListUtil;
import cn.com.bgy.ifc.bgy.utils.StringUtil;
import cn.com.bgy.ifc.bgy.utils.TimeUtil;
import cn.com.bgy.ifc.dao.project.*;
import cn.com.bgy.ifc.domain.interfaces.project.RegionProjectDomain;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceMsgDomain;
import cn.com.bgy.ifc.entity.po.project.RegionProject;
import cn.com.bgy.ifc.entity.po.synchro.BgyRegionProject;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.project.BgyProjectVo;
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
 * @Author huxin
 * @Date 2018/12/18 17:18
 * @Description
 **/
@Service
public class RegionProjectDomainImpl implements RegionProjectDomain {
    private static Logger logger = LoggerFactory.getLogger(RegionProjectDomainImpl.class);

    @Autowired
    private ExternalInterfaceMsgDomain externalInterfaceMsgDomain;

    @Resource
    private RegionProjectDao regionProjectDao;


    /**
     * @Author huxin
     * @Description 查
     * @Date 2018/12/18 17:31
     */
    @Override
    public PageInfo queryListRegionProject(Page page, Long id, String keyword) {
        Map<String, Object> map = new HashMap<>();
        map.put("regionId", id);
        map.put("keyword", keyword);
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<Map<String, Object>> list = regionProjectDao.queryListRegionProject(map);
        return new PageInfo(list);
    }

    /**
     * @Author huxin
     * @Description 增加
     * @Date 2018/12/18 17:31
     */
    @Override
    public int insert(RegionProject record) {
        record.setCreateTime(new Date());
        record.setLogicRemove(false);
        return regionProjectDao.insert(record);
    }

    /**
     * @Author huxin
     * @Description 修改
     * @Date 2018/12/18 17:31
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int updateRegionProject(RegionProject record) {
        if (record.getId() != null && record.getId()>0) {
            record.setCreateTime(new Date());
            return regionProjectDao.updateSelective(record);
        }

        return 0;

    }

    /**
     * @Author huxin
     * @Description 删除
     * @Date 2018/12/18 17:31
     */
    @Override
    public int deleteRegionProject(String str) {
        List<Long> list = ListUtil.getListId(str);

        if (list.size() > 0) {
            return regionProjectDao.deleteRegionProject(list);
        }
        return 0;
    }

    /**
     * @Author huxin
     * @Description 根据父级id查询所有项目名
     * @Date 2018/12/20 18:26
     */
    @Override
    public List<Map<String, Object>> queryRegionProjectNameBySuperId(Long id) {
        return regionProjectDao.queryRegionProjectNameBySuperId(id);
    }

    /**
     * @author: ZhangCheng
     * @description:
     * @param: [list, orgId]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @Override
    public ResponseVO<Object> saveBgyRegionProject(List<BgyProjectVo> list, Long orgId) {
        try {
            List<BgyRegionProject> infoList = new ArrayList<>();
            Date createTime = new Date();
            for (BgyProjectVo bgyProjectVo : list) {
                BgyRegionProject project = new BgyRegionProject();
                project.setId(bgyProjectVo.getId());
                project.setOrganizationId(orgId);
                project.setRegionId(bgyProjectVo.getAreaId());
                project.setCode(bgyProjectVo.getCode());
                project.setName(bgyProjectVo.getName());
                String locationStr = bgyProjectVo.getLocationStr();
                if (locationStr.contains(",")) {
                    project.setLongitude(locationStr.substring(0, locationStr.indexOf(",")));
                    project.setLatitude(locationStr.substring(locationStr.indexOf(",") + 1, locationStr.length()));
                }
                project.setCreateTime(createTime);
                project.setLogicRemove(false);
                infoList.add(project);
            }
            int totalCount = DbUtil.insertByList("region_project", infoList);
            if (totalCount != infoList.size()) {
                return ResponseVO.error().setMsg("同步集成平台项目异常");
            } else {
                externalInterfaceMsgDomain.successInterfaceMsg(orgId, ExternalConstant.MsgTypeValue.BGY_PROJECT_OBTAIN.getValue(), totalCount);
                return ResponseVO.success().setMsg("同步集成平台项目总条数：" + totalCount + "，新增条数：" + totalCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
            }
        } catch (Exception e) {
            logger.error("同步集成平台项目doMain异常:", e);
            return ResponseVO.error().setMsg("同步集成平台项目异常");
        }
    }

    /**
     * @author: ZhangCheng
     * @description:同步项目全量
     * @param: [list, orgId]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @Override
    public ResponseVO<Object> alterBgyRegionProject(List<BgyProjectVo> list, Long orgId) {
        int addType = ExternalConstant.OperationType.ADD.getValue();
        int updateType = ExternalConstant.OperationType.UPDATE.getValue();
        int deleteType = ExternalConstant.OperationType.DELETE.getValue();
        int totalCount = list.size();
        int addCount = 0;
        int updateCount = 0;
        int deleteCount = 0;
        Date createTime = new Date();
        for (BgyProjectVo bgyProjectVo : list) {
            RegionProject project = new RegionProject();
            project.setId(bgyProjectVo.getId());
            project.setOrganizationId(orgId);
            project.setRegionId(bgyProjectVo.getAreaId());
            project.setCode(bgyProjectVo.getCode());
            project.setName(bgyProjectVo.getName());
            String locationStr = bgyProjectVo.getLocationStr();
            if (locationStr.contains(",")) {
                project.setLongitude(locationStr.substring(0, locationStr.indexOf(",")));
                project.setLatitude(locationStr.substring(locationStr.indexOf(",") + 1, locationStr.length()));
            }
            project.setCreateTime(createTime);
            project.setLogicRemove(false);
            int operType = bgyProjectVo.getOperType();
            //新增
            if (operType == addType) {
                int count = regionProjectDao.insertSelective(project);
                if (count == 1) {
                    addCount++;
                }
            }
            //修改
            if (operType == updateType) {
                int count = regionProjectDao.updateSelective(project);
                if (count == 1) {
                    updateCount++;
                }
            }
            //删除
            if (operType == deleteType) {
                project.setLogicRemove(true);
                int count = regionProjectDao.updateSelective(project);
                if (count == 1) {
                    deleteCount++;
                }
            }
        }
        if (addCount + updateCount + deleteCount != totalCount) {
            throw new RuntimeException("批量同步项目增量数据失败!");
        } else {
            Date updateTime = new Date();
            if (list.get(totalCount - 1).getOperTime() != null) {
                updateTime = TimeUtil.parseStrToDateTime(list.get(totalCount - 1).getOperTime());
            }
            int msgType = ExternalConstant.MsgTypeValue.BGY_PROJECT_OBTAIN.getValue();
            externalInterfaceMsgDomain.alterInterfaceMsg(orgId, msgType, totalCount, addCount, updateCount, deleteCount,updateTime);
            return ResponseVO.success().setMsg("同步集成平台项目增量总条数：" + totalCount + "，新增条数：" + addCount + ",修改条数：" + updateCount + ",删除条数：" + deleteCount + ",成功条数：" + totalCount + "，失败条数" + 0 + "");
        }
    }

    /**
     * @Author huxin
     * @Description根据id查询当前项目信息
     * @Date 2019/1/2 9:31
     */
    @Override
    public Map<String, Object> findById(Long id) {
        if (id != null || id > 0) {
            return (Map<String, Object>) regionProjectDao.findById(id);
        }
        return null;
    }

    /**
     * @description:根据区域id获取区域项目
     * @param:
     * @return:
     * @auther: chenlie
     * @date: 2019/1/8 16:04
     */
    @Override
    public  List<RegionProject> queryRegionProjectByRegionId(Long regionId){
        return regionProjectDao.queryRegionProjectByRegionId(regionId);
    }
    @Override
    public List<Map<String, Object>> queryByCodeSort(Long id) {
        List<RegionProject> allList = this.queryRegionProjectByRegionId(id);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (int i = 65; i < 91; i++) {

            List<RegionProject> result = new ArrayList<RegionProject>();
            char alphabet = (char) i;
            String alphabets = new Character(alphabet).toString();
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("key", alphabets);
            for (RegionProject regionProject : allList) {
                System.out.println(StringUtil.getFirstSpell(regionProject.getName()));
                if (StringUtil.getFirstSpell(regionProject.getName()).equals(alphabets)) {
                    result.add(regionProject);
                }
            }
            map1.put("data", result);
            resultList.add(map1);
        }
        return resultList;
    }


}
