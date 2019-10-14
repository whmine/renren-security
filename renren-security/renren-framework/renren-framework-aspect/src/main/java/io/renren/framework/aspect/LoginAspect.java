/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.framework.aspect;


import com.google.gson.Gson;
import io.renren.common.base.BaseRequest;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.HttpContextUtils;
import io.renren.common.utils.IPUtils;
import io.renren.common.utils.R;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
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

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统登录TOKEN与用户ID转换，切面处理类
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Slf4j
@Aspect
@Component
@Order(2)
public class LoginAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("@annotation(io.renren.framework.annotation.Login)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();

        //保存更新登录信息
        return saveLogin(point, beginTime);
    }

    private Object saveLogin(ProceedingJoinPoint joinPoint, long time) {

        Object[] args = joinPoint.getArgs();
        String isToken = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.IS_TOKEN);
        if (StringUtils.isBlank(isToken)) {
            return R.error(1, "系统异常");
        }
        Map isTokenmap = new Gson().fromJson(isToken, Map.class);
        if (Constants.STATE_YES.equals(isTokenmap.get("value"))) {
            BaseRequest arg = (BaseRequest) args[0];

            //获取request
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

            //从header中获取token
            String token = request.getHeader(Constants.X_TOKEN);
            //如果header中不存在token，则从参数中获取token
            if (StringUtils.isBlank(token)) {
                token = request.getParameter(Constants.X_TOKEN);
            }
            //token为空
            if (StringUtils.isBlank(token)) {
//                throw new RRException("token不能为空", 1);
                return R.error(1, "token不能为空");
            }
            String jsonToken = stringRedisTemplate.opsForValue().get(Constants.X_OAUTH_TOKEN_TOKEN + token);
            if (!StringUtils.isBlank(jsonToken)) {
                Map map = new Gson().fromJson(jsonToken, Map.class);
                arg.setUserId(map.get("sysUserId") + "");
                args[0] = arg;
                //执行方法
            } else {
                //超期
                return R.error(1, "token失效");
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            return R.error(500, "异常[LoginAspect]:" + throwable.getMessage());
        }
        return result;

    }
}
