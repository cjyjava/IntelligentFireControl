package cn.com.bgy.ifc.domain.interfaces.system;

import cn.com.bgy.ifc.entity.po.system.SystemOperationLog;
import cn.com.bgy.ifc.entity.vo.system.SystemOperationLogVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @Author huxin
 * @Date 2018/12/6 10:36
 * @Description 系统日志domain
 **/

public interface SystemLogDomain {

    public PageInfo<SystemOperationLog> queryListByParam(Page<SystemOperationLog> page, SystemOperationLogVo systemOperationLogVo);

    public void addSystemLogInfo(SystemOperationLog systemOperationLog);

}
