package cn.com.bgy.ifc.service.interfaces.inner.report;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @description:
 * @param: 
 * @return: 报表管理，告警管理
 * @auther: chenlie
 * @date: 2019/2/12 15:51
 */

public interface AlarmReportService {
    //告警总数和火警总数
    Map<String, Object> getAlarmCount(Map<String, Object> map);
    //同比
    Map<String, Object> getAlarmCountYearOnYear(Map<String, Object> map);
    //环比
    Map<String, Object> getAlarmCountChainRatio(Map<String, Object> map);


    //告警分系统统计
    Map<String, Object> getAlarmBySystem(Map<String, Object> map);

    //告警分项统计
    Map<String, Object> getAlarmByItem(Map<String, Object> map);

    //告警处理情况统计
    Map<String, Object> getAlarmHandle(Map<String, Object> map);

    //传感器告警数量统计
    Map<String, Object> getAlarmSensor(Long regionId, Long projectId);

    //告警分系统排名
    Map<String, Object> getAlarmBySystemRank(Map<String, Object> map);


    //各区域告警数量排名
    Map<String, Object> getAlarmByRegionRank(Map<String, Object> map);


    //火警趋势图
    Map<String, Object> getAlarmFireChart(Map<String, Object> map);

    //故障趋势图
    Map<String, Object> getAlarmHitchChart(Map<String, Object> map);




}
