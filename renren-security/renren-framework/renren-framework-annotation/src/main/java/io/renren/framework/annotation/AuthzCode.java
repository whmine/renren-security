package io.renren.framework.annotation;

import java.lang.annotation.*;

/**
 * 系统服务授权认证，切面处理类
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthzCode {
    /**
     * 授权码
     */
    String authzCode() default "";
}
