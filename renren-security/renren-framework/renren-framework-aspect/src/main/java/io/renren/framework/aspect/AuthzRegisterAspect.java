package io.renren.framework.aspect;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.utils.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统服务注册，切面处理类
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class AuthzRegisterAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    @Pointcut("@annotation(io.renren.framework.annotation.AuthzRegister)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        //授权认证
        return authzCode(point, beginTime);
    }

    private Object authzCode(ProceedingJoinPoint joinPoint, long beginTime) {
        log.debug("系统服务注册，开始时间：" + beginTime);
        String isAuto = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.IS_AUTO);
        if (!StringUtils.isBlank(isAuto)) {
            Map mapExpiresIn = new Gson().fromJson(isAuto, Map.class);
            if (!StringUtils.isBlank(mapExpiresIn.get("value") + "") && Constants.STATE_YES.equals(mapExpiresIn.get("value") + "")) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                AuthzRegister authzRegister = method.getAnnotation(AuthzRegister.class);
                if (authzRegister == null) {
                    long endTime = System.currentTimeMillis();
                    log.debug("系统服务注册，结束时间：" + endTime);
                    return R.error(500, "异常:获取注解异常");
                }
                String className = joinPoint.getTarget().getClass().getName();
                String methodName = signature.getName();


                Map map = new HashMap();
                map.put("roleName", authzRegister.roleName());
                map.put("roleCnName", authzRegister.roleCnName());
                map.put("authoritiesName", authzRegister.authoritiesName());
                map.put("authoritiesCnName", authzRegister.authoritiesCnName());
                map.put("name", authzRegister.name());
                map.put("cnName", authzRegister.cnName());
                map.put("icon", authzRegister.icon());
                map.put("describe", authzRegister.describe());
                map.put("authzCode", authzRegister.authzCode());
                map.put("perms", authzRegister.perms());
                map.put("isShow", authzRegister.isShow());
                map.put("startUpType", authzRegister.startUpType());
                map.put("state", authzRegister.state());
                map.put("method", className + Constants.CHAR_SPOT + methodName);

                producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysService"), map);

            }

        }


        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            long endTime = System.currentTimeMillis();
            log.debug("系统服务注册，结束时间：" + endTime);
            return R.error(500, "异常[AuthzRegisterAspect]:" + throwable.getMessage());
        }
        long endTime = System.currentTimeMillis();
        log.debug("系统服务注册，结束时间：" + endTime);
        return result;
    }
}
