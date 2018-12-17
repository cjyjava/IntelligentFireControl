package cn.com.bgy.ifc.bgy.aspect;

import cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave;
import cn.com.bgy.ifc.bgy.annotation.SystemLogSave;
import cn.com.bgy.ifc.domain.interfaces.basic.SystemLogDomain;
import cn.com.bgy.ifc.entity.po.basic.SystemOperationLog;
import cn.com.bgy.ifc.entity.vo.ResponseVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author: ZhangCheng
 * @description:方法结束日志
 * @date: 2018-12-12 17:27
 *
 * 使用方式，在方法上使用@SystemLogAfterSave(type=1,description = "描述")
 * 需在方法后增加ResponseVO对象，便于添加日志信息
 * type类型参考SystemConstant.SystemLogType中枚举类型
 **/
@Aspect
@Component
public class SystemLogAfterAspect {

    @Autowired
    private SystemLogDomain systemLogDomain;

    @AfterReturning(returning="result", pointcut="@annotation(cn.com.bgy.ifc.bgy.annotation.SystemLogAfterSave)")
    public void save(JoinPoint joinPoint, ResponseVO<Object> result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //方法如果存在这样的注释，则返回指定类型的元素的注释，否则为null。
        SystemLogAfterSave systemLogAfterSave = method.getAnnotation(SystemLogAfterSave.class);
        SystemOperationLog systemOperationLog = new SystemOperationLog();
        //joinPoint.
        System.out.println("===="+result.getCode());
        System.out.println("===="+result.getMsg());
        System.out.println("===="+result.getData());
        if (systemLogAfterSave != null) {
            //注解上的类型
            systemOperationLog.setLogType(systemLogAfterSave.type());
            //注解上的描述
            systemOperationLog.setOperatorDescribe(systemLogAfterSave.description());
        }
        systemOperationLog.setCreateTime(new Date());
        systemOperationLog.setLogicRemove(false);
        systemLogDomain.addSystemLogInfo(systemOperationLog);
    }
}