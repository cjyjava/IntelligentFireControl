package cn.com.bgy.ifc.service.impl.inner.projects;

import cn.com.bgy.ifc.bgy.utils.ListUtil;
import cn.com.bgy.ifc.dao.project.RegionCourtDao;
import cn.com.bgy.ifc.domain.interfaces.project.RegionCourtDomain;
import cn.com.bgy.ifc.entity.po.project.RegionCourt;
import cn.com.bgy.ifc.entity.vo.project.RegionCourtVo;
import cn.com.bgy.ifc.service.interfaces.inner.project.RegionCourtService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author huxin
 * @Date 2018/12/19 10:39
 * @Description 苑区信息
 **/
@Service
public class RegionCourtServiceImpl implements RegionCourtService {
    @Autowired
    private RegionCourtDomain regionCourtDomain;

    @Resource
    private RegionCourtDao regionCourtDao;

    /**
     * @Author huxin
     * @Description 查
     * @Date 2018/12/19 10:43
     */
    @Override
    public PageInfo queryListRegionCourt( Page page, RegionCourtVo record ) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Map<String,Object>> list=  regionCourtDomain.queryListRegionCourt(record);
        return  new PageInfo(list);
    }
    /**
     * @Author huxin
     * @Description 增
     * @Date 2018/12/19 10:43
     */
    @Override
    public int insert( RegionCourt record ) {
        return regionCourtDomain.insert(record);
    }
    /**
     * @Author huxin
     * @Description 修改
     * @Date 2018/12/19 10:43
     */
    @Override
    public int updateRegionCourt( RegionCourt record ) {
        record.setCreateTime(new Date());
        return regionCourtDomain.updateRegionCourt(record);
    }
    /**
     * @Author huxin
     * @Description 删除
     * @Date 2018/12/19 10:43
     */
    @Override
    public int deleteRegionCourt( String str ) {

        List<Long> list = ListUtil.getListId(str);

        if(list.size()>0){
            return regionCourtDomain.deleteRegionCourt(list);
        }
        return 0;
    }
    /**
     * @Author huxin
     * @Description 根据父id查询所有苑区名
     * @Date 2018/12/20 19:34
     */
    @Override
    public List<Map<String, Object>> queryRegionCourtNameBySuperId( Long id ) {
        return regionCourtDomain.queryRegionCourtNameBySuperId(id);
    }
    /**
     * @Author huxin
     * @Description 根据id查询
     * @Date 2019/1/2 9:46
     */
    @Override
    public Map<String, Object> findById( Long id ) {
        if(id != null || id>0){
           return  (Map<String, Object>) regionCourtDao.findById(id);
        }
        return null;
    }
}
