package cn.com.bgy.ifc.service.interfaces.inner.alarm;

import cn.com.bgy.ifc.entity.po.alarm.AlarmCount;
import cn.com.bgy.ifc.entity.po.alarm.RealTimeAlarm;
import cn.com.bgy.ifc.entity.po.equipment.EquipmentEvent;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.alarm.AlarmVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


/**
 * @author: ZhangCheng
 * @description:实时告警
 * @date: 2019-01-18 10:33
 **/
public interface AlarmService {

    /**
     * 实时告警列表分页查询
     * @param page
     * @param type
     * @return
     */
    PageInfo<AlarmVo> queryListByPage(Page page, Integer type);

    /**
     * 告警数据统计
     * @return
     */
    AlarmCount queryAlarmCount();

    /**
     * 处理实时告警
     * @param id
     * @return
     */
    ResponseVO<Object> editAlarm(Long id);

    /**
     * 批量处理设备告警
     * @param ids
     * @return
     */
    ResponseVO<Object> handleDataList(String ids);
}
