package cn.com.bgy.ifc.dao.firepatrol;

import cn.com.bgy.ifc.dao.base.BaseDao;
import cn.com.bgy.ifc.entity.po.firepatrol.ExtinguisherConfig;

import java.util.List;
import java.util.Map;

public interface ExtinguisherConfigDao extends BaseDao<ExtinguisherConfig> {

    //查询灭火器配置情况列表
    List<Map<String,Object>> listExtinguisherConfig( Map<String,Object> map );
    //根据备案ID删除
    int deleteByRecordId( List<Long> list );
    //根据备案ID查询
    ExtinguisherConfig queryByRecordId( Long recordId );
}