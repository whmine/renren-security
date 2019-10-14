package io.renren.framework.aspect;

import io.renren.common.base.BaseRequest;
import io.renren.common.constant.Constants;
import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzCode;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 系统授权认证，切面处理类
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Slf4j
@Aspect
@Component
@Order(3)
public class AuthzCodeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(io.renren.framework.annotation.AuthzCode)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        //授权认证
        return authzCode(point, beginTime);
    }

    private Object authzCode(ProceedingJoinPoint joinPoint, long beginTime) {
        log.debug("系统授权认证，开始时间：" + beginTime);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuthzCode authzCodeAnnotation = method.getAnnotation(AuthzCode.class);
        if (authzCodeAnnotation == null) {
            long endTime = System.currentTimeMillis();
            log.debug("系统授权认证，结束时间：" + endTime);
            return R.error(500, "异常:获取注解异常");
        }
//
        Object[] args = joinPoint.getArgs();
        BaseRequest arg = (BaseRequest) args[0];
        if (StringUtils.isBlank(arg.getUserId())) {
            long endTime = System.currentTimeMillis();
            log.debug("系统授权认证，结束时间：" + endTime);
            return R.error(1, "异常:您未登录");
        }
        String authzCode = stringRedisTemplate.opsForValue().get(Constants.X_AUTHZ_CODE + arg.getUserId());
        if (StringUtils.isBlank(authzCode)) {
            long endTime = System.currentTimeMillis();
            log.debug("系统授权认证，结束时间：" + endTime);
            return R.error(1, "异常:获取用户权限异常");
        }
        if (authzCode.indexOf(authzCodeAnnotation.authzCode()) > 0) {
            Object result = null;
            try {
                log.debug("系统授权认证，AuthzCodeAspect开始：" + System.currentTimeMillis());
                result = joinPoint.proceed(args);
                log.debug("系统授权认证，AuthzCodeAspect结束" + System.currentTimeMillis());
            } catch (Throwable throwable) {
                long endTime = System.currentTimeMillis();
                log.debug("系统授权认证，结束时间：" + endTime);
                return R.error(500, "异常[AuthzCodeAspect]:" + throwable.getMessage());
            }
            long endTime = System.currentTimeMillis();
            log.debug("系统授权认证，结束时间：" + endTime);
            return result;
        } else {
            return R.error(500, "异常:您没有该服务的访问权限权限不足");
        }
    }
}
