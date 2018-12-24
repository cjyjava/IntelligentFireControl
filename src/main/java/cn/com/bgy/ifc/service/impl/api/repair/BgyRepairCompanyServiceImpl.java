package cn.com.bgy.ifc.service.impl.api.repair;

import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.constant.ExternalConstant;
import cn.com.bgy.ifc.bgy.helper.HttpHelper;
import cn.com.bgy.ifc.bgy.utils.ResponseUtil;
import cn.com.bgy.ifc.bgy.utils.SignatureUtil;
import cn.com.bgy.ifc.bgy.utils.TimeUtil;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceConfigDomain;
import cn.com.bgy.ifc.domain.interfaces.system.ExternalInterfaceMsgDomain;
import cn.com.bgy.ifc.entity.po.system.ExternalInterfaceConfig;
import cn.com.bgy.ifc.entity.po.system.ExternalInterfaceMsg;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.common.HttpVo;
import cn.com.bgy.ifc.entity.vo.repair.BgyRepairCompanyVo;
import cn.com.bgy.ifc.service.interfaces.api.repair.BgyRepairCompanyService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: ZhangCheng
 * @description:碧桂园集成平台维保公司
 * @date: 2018-12-24 10:17
 **/
@Service
public class BgyRepairCompanyServiceImpl implements BgyRepairCompanyService {

    private static Logger logger = LoggerFactory.getLogger(BgyRepairCompanyServiceImpl.class);

    @Autowired
    private ExternalInterfaceConfigDomain externalInterfaceConfigDomain;

    @Autowired
    private ExternalInterfaceMsgDomain externalInterfaceMsgDomain;

    @SystemLogAfterSave(type = 7, description = "同步集成平台维保公司数据")
    @Override
    public ResponseVO<Object> baseObtainBgyRepairCompany(int pageNo, int pageSize) {
        try {
            List<ExternalInterfaceConfig> list = externalInterfaceConfigDomain.queryIntegrationConfig();
            if (list.size() != 0) {
                ExternalInterfaceConfig config = list.get(0);
                Long orgId = config.getOrgId();
                List<ExternalInterfaceMsg> msgList = externalInterfaceMsgDomain.queryBgyInterfaceMsg(ExternalConstant.MsgTypeValue.BGY_REPAIR_CONTRACT_OBTAIN.getValue(), orgId);
                if (msgList.size() > 0) {
                    ExternalInterfaceMsg interfaceMsg = msgList.get(0);
                    Date createTime = interfaceMsg.getCreateTime();
                    return obtainBgyRepairCompanyIncrement(pageNo, pageSize, config, createTime);
                } else {
                    return obtainBgyRepairCompany(pageNo, pageSize, config);
                }
            } else {
                logger.info("获取集成平台接口配置数据失败！");
                return ResponseVO.error().setMsg("获取集成平台接口配置数据失败！");
            }
        } catch (Exception e) {
            logger.error("获取集成平台维保公司数据接口请求异常：" + e);
            return ResponseVO.error().setMsg("获取集成平台维保公司数据接口请求异常！");
        }
    }

    @Override
    public ResponseVO<Object> obtainBgyRepairCompany(int pageNo, int pageSize, ExternalInterfaceConfig config) throws Exception {
        String reqUrl = "/api/third/company/getCompanyList";
        long orgId = config.getOrgId();
        // 请求包结构体
        Map<String, Object> data = new HashMap<>();
        data.put("pageNo", pageNo);
        data.put("pageSize", pageSize);
        //集成平台HTTP头部需要数据
        HttpVo httpVo = SignatureUtil.getHttpVo(config, reqUrl, data);
        //调用HTTP请求
        JSONObject response = HttpHelper.httpPost(httpVo.getUrl(), data, httpVo.getHeaderMap());
        // 总页数
        int pageCount = ResponseUtil.getPageCount(response, pageSize);
        List<BgyRepairCompanyVo> oList = new ArrayList<>();
        BgyRepairCompanyVo bgyRepairCompanyVo = new BgyRepairCompanyVo();
        ResponseUtil.getResultList(oList, bgyRepairCompanyVo, response, "data", "list");
        if (pageCount != 0) {
            ResponseUtil.getResultByPage(pageNo, pageSize, pageCount, config, reqUrl, oList, bgyRepairCompanyVo, "data", "list");
        }
        int totalCount = oList.size();
        if (totalCount > 0) {
            // return equipmentInfoDomain.saveBgyEquipmentInfo(oList, orgId);
            return null;
        } else {
            return ResponseVO.success().setMsg("暂无集成平台设备信息数据同步！");
        }

    }

    @Override
    public ResponseVO<Object> obtainBgyRepairCompanyIncrement(int pageNo, int pageSize, ExternalInterfaceConfig config, Date createTime) throws Exception {
        String reqUrl = "/api/third/company/getCompanyIncrement";
        Long orgId = config.getOrgId();
        //格式化时间字符串
        String dateTime = TimeUtil.parseDateToStr(createTime);
        // 请求包结构体
        Map<String, Object> data = new HashMap<>();
        data.put("startTime", dateTime);
        data.put("pageNo", pageNo);
        data.put("pageSize", pageSize);
        //集成平台HTTP头部需要数据
        HttpVo httpVo = SignatureUtil.getHttpVo(config, reqUrl, data);
        //调用HTTP请求
        JSONObject response = HttpHelper.httpPost(httpVo.getUrl(), data, httpVo.getHeaderMap());
        // 总页数
        int pageCount = ResponseUtil.getPageCount(response, pageSize);
        List<BgyRepairCompanyVo> oList = new ArrayList<>();
        BgyRepairCompanyVo bgyRepairCompanyVo = new BgyRepairCompanyVo();
        ResponseUtil.getResultList(oList, bgyRepairCompanyVo, response, "data", "list");
        if (pageCount != 0) {
            ResponseUtil.getIncResultByPage(pageNo, pageSize, dateTime, pageCount, config, reqUrl, oList, bgyRepairCompanyVo, "data", "list");
        }
        int totalCount = oList.size();
        if (totalCount > 0) {
            // return regionComputerRoomDomain.alterBgyComputerRoomList(oList, orgId);
            return null;
        } else {
            return ResponseVO.success().setMsg("暂无集成平台维保公司增量数据同步！");
        }
    }
}