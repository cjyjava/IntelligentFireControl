package cn.com.bgy.ifc.domain.impl.project;

import cn.com.bgy.ifc.dao.project.RegionBuildingDao;
import cn.com.bgy.ifc.dao.project.RegionComputerRoomDao;
import cn.com.bgy.ifc.dao.project.RegionCourtDao;
import cn.com.bgy.ifc.dao.project.RegionStreetDao;
import cn.com.bgy.ifc.domain.interfaces.project.RegionCourtDomain;
import cn.com.bgy.ifc.entity.po.project.RegionCourt;
import cn.com.bgy.ifc.entity.vo.project.RegionCourtVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author huxin
 * @Date 2018/12/19 9:20
 * @Description 区域苑区信息DomainImpl
 **/
@Service
public class RegionCourtDomainImpl implements RegionCourtDomain {
    @Resource
    private RegionCourtDao regionCourtDao;

    @Resource
    private RegionStreetDao regionStreetDao;

    @Resource
    private RegionBuildingDao regionBuildingDao;

    @Resource
    private RegionComputerRoomDao regionComputerRoomDao;

    /**
     * @Author huxin
     * @Description 查
     * @Date 2018/12/19 15:18
     */
    @Override
    public List<RegionCourtVo> queryListRegionCourt( RegionCourtVo regionCourtVo ) {
        return regionCourtDao.queryListRegionCourt(regionCourtVo);
    }
    /**
     * @Author huxin
     * @Description 增
     * @Date 2018/12/19 15:18
     */
    @Override
    public int insert( RegionCourt record ) {
        record.setCreateTime(new Date());
        record.setLogicRemove(false);
        return regionCourtDao.insert(record);
    }
    /**
     * @Author huxin
     * @Description 改
     * @Date 2018/12/19 15:18
     */
    @Override
    public int updateRegionCourt( RegionCourt record ) {
        return regionCourtDao.updateRegionCourt(record);

    }
    /**
     * @Author huxin
     * @Description 删除
     * @Date 2018/12/19 15:32
     */
    @Override
    public int deleteRegionCourt( List<Long> list ) {

        if(list.size()>0){
            //删除机房
            regionComputerRoomDao.deleteRegionComputerRoomBySuperId(list);
            //删除楼栋
            regionBuildingDao.deleteRegionBuildingBySuperId(list);
            //删除街道
            regionStreetDao.deleteRegionStreetBySuperId(list);
            //删除苑区
            return regionCourtDao.deleteRegionCourt(list);
        }
        return 0;
    }
    /**
     * @Author huxin
     * @Description 根据父级id查询所有苑区名
     * @Date 2018/12/20 18:26
     */
    @Override
    public List<Map<String, Object>> queryRegionCourtNameBySuperId( Long id ) {
        return regionCourtDao.queryRegionCourtNameBySuperId(id);
    }
}
