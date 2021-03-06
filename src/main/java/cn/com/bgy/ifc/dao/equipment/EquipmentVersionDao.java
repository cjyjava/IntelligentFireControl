package cn.com.bgy.ifc.dao.equipment;

import cn.com.bgy.ifc.dao.base.BaseDao;
import cn.com.bgy.ifc.entity.po.equipment.EquipmentVersion;

import java.util.List;
import java.util.Map;

public interface EquipmentVersionDao extends BaseDao<EquipmentVersion> {



    List<Map<String,Object>> queryListEquipmentVersion(Map map);

    int updateEquipmentVersion(EquipmentVersion record);

    int deleteEquipmentVersion( List<Long> list );
    //根据品牌id查询所属的所有型号
    List<Map<String,Object>> queryEquipmentVersionByBrandId( Long id );

    //根据品牌id查询所属的所有型号ID
    List<Long> queryEquipmentVersionIdByBrandId(List<Long> list);
}