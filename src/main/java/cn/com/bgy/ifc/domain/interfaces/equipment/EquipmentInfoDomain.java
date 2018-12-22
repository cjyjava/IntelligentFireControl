package cn.com.bgy.ifc.domain.interfaces.equipment;

import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.equipment.BgyEquipmentBrandVo;
import cn.com.bgy.ifc.entity.vo.equipment.BgyEquipmentVo;

import java.util.List;

/**
 * @author: ZhangCheng
 * @description:设备信息
 * @date: 2018-12-22 16:34
 **/
public interface EquipmentInfoDomain {

    /**
     * @author: ZhangCheng
     * @description:集成平台全量增加设备品牌信息（全量）
     * @param: [list, orgId]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    ResponseVO<Object> saveBgyEquipmentInfo(List<BgyEquipmentVo> list, Long orgId);

    /**
     * @author: ZhangCheng
     * @description:集成平台增量增加设备品牌信息（增量）
     * @param: [list, orgId]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    ResponseVO<Object> alterBgyEquipmentInfo(List<BgyEquipmentVo> list, Long orgId);
}
