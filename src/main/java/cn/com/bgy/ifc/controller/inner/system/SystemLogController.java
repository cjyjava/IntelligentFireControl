package cn.com.bgy.ifc.controller.inner.system;

import cn.com.bgy.ifc.bgy.constant.SystemConstant;
import cn.com.bgy.ifc.domain.interfaces.system.SystemLogDomain;
import cn.com.bgy.ifc.entity.po.system.SystemOperationLog;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import cn.com.bgy.ifc.entity.vo.common.SelectVo;
import cn.com.bgy.ifc.entity.vo.system.SystemOperationLogVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: ZhangCheng
 * @description:系统日志查询
 */
@Controller
@RequestMapping(value = "/basic/systemLog")
public class SystemLogController {

    @Autowired
    SystemLogDomain systemLogDomain;

    /**
     * @author: ZhangCheng
     * @description:系统日志分页查询
     * @param: [page, systemOperationLogVo, token]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<com.github.pagehelper.PageInfo<cn.com.bgy.ifc.entity.po.system.SystemOperationLog>>
     */
    @PostMapping("/query")
    @ResponseBody
    public ResponseVO<PageInfo<SystemOperationLog>> querySystemLogInfo(Page<SystemOperationLog> page, SystemOperationLogVo systemOperationLogVo,String token){
        PageInfo<SystemOperationLog> pageInfo = systemLogDomain.queryListByParam(page,systemOperationLogVo);
        return ResponseVO.<PageInfo<SystemOperationLog>>success().setData(pageInfo);
    }

    /**
     * @author: ZhangCheng
     * @description:获取日志类型枚举
     * @param: [token]
     * @return: cn.com.bgy.ifc.entity.vo.ResponseVO<java.lang.Object>
     */
    @GetMapping("/getLogType")
    @ResponseBody
    public ResponseVO<Object> getLogType(String token){
        List<SelectVo> list=SystemConstant.SystemLogType.getSelectList();
        return ResponseVO.success().setData(list);
    }
}