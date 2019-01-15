package cn.com.bgy.ifc.service.impl.inner.equipment;

import cn.com.bgy.ifc.bgy.helper.HttpHelper;
import cn.com.bgy.ifc.bgy.utils.*;
import cn.com.bgy.ifc.dao.equipment.EquipmentStateDao;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceConfigDomain;
import cn.com.bgy.ifc.entity.po.equipment.EquipmentState;
import cn.com.bgy.ifc.entity.po.system.ExternalInterfaceConfig;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.equipment.EquipmentStateVo;
import cn.com.bgy.ifc.service.interfaces.inner.equipment.EquipmentStateService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ZhangCheng
 * @description:设备状态信息
 * @date: 2019-01-10 19:27
 **/
@Service
public class EquipmentStateServiceImpl implements EquipmentStateService {

    private static Logger logger = LoggerFactory.getLogger(EquipmentConfigServiceImpl.class);

    @Resource
    private EquipmentStateDao equipmentStateDao;

    @Autowired
    private ExternalInterfaceConfigDomain externalInterfaceConfigDomain;

    @Override
    public EquipmentState findById(Long id) {
        return equipmentStateDao.findById(id);
    }

    @Override
    public int insert(EquipmentState equipmentState) {
        return 0;
    }

    @Override
    public int insertSelective(EquipmentState equipmentState) {
        return 0;
    }

    @Override
    public int update(EquipmentState equipmentState) {
        return 0;
    }

    @Override
    public int updateSelective(EquipmentState equipmentState) {
        return 0;
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return 0;
    }

    @Override
    public PageInfo<EquipmentState> queryListByPage(Page page, EquipmentStateVo equipmentStateVo) {
        page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<EquipmentState> list = equipmentStateDao.queryListByParam(equipmentStateVo);
        PageInfo<EquipmentState> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public ResponseVO<Object> synchroEquipmentState(int pageNum, int pageSize) {
        try {
            List<ExternalInterfaceConfig> list = externalInterfaceConfigDomain.queryInternetThingConfig();
            if (list.size() != 0) {
                ExternalInterfaceConfig config = list.get(0);
                String baseUrl = config.getUrl() + "/device/devices";
                String url = baseUrl + "?page=" + pageNum + "&size" + pageSize;
                JSONObject response = HttpHelper.httpGet(url, null);
                if (response != null) {
                    List<Map<String, Object>> configList = new ArrayList();
                    ResponseUtil.getResultListMap(configList, response, "data", "list");
                    // 总页数
                    int pageCount = ResponseUtil.getPages(response, "pages");
                    if (pageCount > 1) {
                        ResponseUtil.getListMapByPage(pageNum, pageSize, pageCount, baseUrl, configList, "data", "list");
                    }
                    int totalCount = configList.size();
                    if (totalCount > 0) {
                        String executeName="REPLACE INTO ";
                        int size = configList.size();
                        List<Map<String, Object>> datas = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            Map<String,Object> newValueMap=new HashMap<>();
                            Map<String, Object> valueMap = configList.get(i);
                            valueMap.forEach((key,value)->{
                                if("lastOnlineTime".equals(key)){
                                    if(value!=null){
                                        value=TimeUtil.formatDateOfTimestamp(Long.valueOf(value.toString()));
                                    }
                                }
                                if("statusChangedTime".equals(key)){
                                    if(value!=null){
                                        value=TimeUtil.formatDateOfTimestamp(Long.valueOf(value.toString()));
                                    }
                                }
                                newValueMap.put(EntityUtil.humpToLine(key),value);
                            });
                            datas.add(newValueMap);
                        }
                        int result=DbUtil.insertAll("equipment_state",executeName,datas);
                        if (result == totalCount) {
                            return ResponseVO.success().setMsg("设备状态数据同步成功！");
                        } else {
                            return ResponseVO.error().setMsg("设备状态数据同步失败！");
                        }
                    } else {
                        return ResponseVO.success().setMsg("暂无中联永安设备状态数据同步！");
                    }
                } else {
                    return ResponseVO.error().setMsg("获取中联永安设备状态信息列表失败!");
                }
            } else {
                return ResponseVO.error().setMsg("获取中联永安接口配置信息失败！");
            }
        } catch (Exception e) {
            logger.error("获取中联永安设备配置列表接口请求异常：", e);
            return ResponseVO.error().setMsg(ExceptionUtil.getExceptionMsg("获取中联永安设备配置列表接口请求异常！", e));
        }
    }

    @Override
    public ResponseVO<Object> deleteEquipmentState(String ids) {
        try {
            List<ExternalInterfaceConfig> list = externalInterfaceConfigDomain.queryInternetThingConfig();
            if (list.size() != 0) {
                ExternalInterfaceConfig config = list.get(0);
                List<Long> idList = ListUtil.getListId(ids);
                String url = "";
                if (idList.size() > 1) {
                    String newIds = ListUtil.getIdStr(idList);
                    url = config.getUrl() + "/device/devices?ids=" + newIds;
                } else {
                    url = config.getUrl() + "/device/devices/" + idList.get(0);
                }
                JSONObject response = HttpHelper.httpDelete(url, null);
                if (response != null) {
                    //data作为key获取JSONObject
                    Integer statusCode = response.getInteger("code");
                    String message = response.getString("message");
                    if (HttpStatus.SC_OK == statusCode) {
                        int result = equipmentStateDao.deleteBatch(idList);
                        if (result == idList.size()) {
                            return ResponseVO.deleteSuccess();
                        } else {
                            return ResponseVO.deleteError();
                        }
                    } else {
                        return ResponseVO.error().setMsg("刪除失败!错误信息：" + message);
                    }
                } else {
                    return ResponseVO.error().setMsg("中联永安刪除设备状态信息失败!");
                }
            } else {
                return ResponseVO.error().setMsg("获取中联永安接口配置信息失败！");
            }
        } catch (Exception e) {
            logger.error("删除中联永安设备状态信息接口请求异常：", e);
            return ResponseVO.error().setMsg(ExceptionUtil.getExceptionMsg("删除中联永安设备状态信息接口请求异常！", e));
        }
    }
}