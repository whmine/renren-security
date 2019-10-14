/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.framework.aspect;


import com.google.gson.Gson;
import io.renren.common.utils.HttpContextUtils;
import io.renren.common.utils.IPUtils;
import io.renren.common.utils.R;
import io.renren.framework.annotation.SysLog;
import io.renren.framework.utils.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统日志，切面处理类
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Slf4j
@Aspect
@Component
@Order(4)
public class SysLogAspect {

    @Autowired
    private Producer producer;

    @Pointcut("@annotation(io.renren.framework.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = null;
        try {
            log.debug("系统日志，SysLogAspect开始：" + System.currentTimeMillis());
            result = point.proceed();
            log.debug("系统日志，SysLogAspect结束：" + System.currentTimeMillis());
        } catch (Throwable throwable) {
            return R.error(500, "异常[SysLogAspect]:" + throwable.getMessage());
        }
        //保存日志
        saveSysLog(point, beginTime);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        Map map = new HashMap();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            map.put("operation", syslog.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request);

        map.put("method", className + "." + methodName);
//        map.put("text", new Gson().toJson(args[0]));
        map.put("ip", ip);
//        map.put("serviceId", time + "");

        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysLog"), map);
        log.debug("记录日志SysLogAspect");
    }
}
