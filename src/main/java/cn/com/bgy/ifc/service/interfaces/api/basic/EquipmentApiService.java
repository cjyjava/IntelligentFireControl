package cn.com.bgy.ifc.service.interfaces.api.basic;

/**
 * @Author huxin
 * @Date 2018/12/11 16:27
 * @Description 消防设备数据调用接口
 **/

public interface EquipmentApiService {
    /**
     * @Author huxin
     * @Description 集成平台获取设备信息列表接口
     * @Date 2018/12/11 16:32
     */
    void obtainEquipment();
    /**
     * @Author huxin
     * @Description 集成平台获取设备信息列表接口（增量）
     * @Date 2018/12/11 16:33
     */
    void obtainListEquipment();
}