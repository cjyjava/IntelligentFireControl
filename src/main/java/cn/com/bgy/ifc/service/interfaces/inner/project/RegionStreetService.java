package cn.com.bgy.ifc.service.interfaces.inner.project;

import cn.com.bgy.ifc.entity.po.project.RegionStreet;
import cn.com.bgy.ifc.entity.vo.project.RegionStreetVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author huxin
 * @Date 2018/12/19 10:39
 * @Description 街道信息
 **/

public interface RegionStreetService {

    PageInfo queryListRegionStreet( Page page, RegionStreetVo record );

    int insert( RegionStreet record );

    int updateRegionStreet( RegionStreet record );

    int deleteRegionStreet( String str );

    List<Map<String,Object>> queryRegionStreetNameBySuperId( Long id);

    Map<String,Object> findById( Long id );
}
