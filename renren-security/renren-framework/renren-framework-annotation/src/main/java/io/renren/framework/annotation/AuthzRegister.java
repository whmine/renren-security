package io.renren.framework.annotation;

import java.lang.annotation.*;

/**
 * 系统服务注册，切面处理类
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthzRegister {


    /**
     * 角色名称
     */
    String roleName() default "";

    /**
     * 角色中文名称
     */
    String roleCnName() default "";

    /**
     * 权限名称
     */
    String authoritiesName() default "";

    /**
     * 权限中文名称
     */
    String authoritiesCnName() default "";

    /**
     * 名称
     */
    String name() default "";

    /**
     * 中文名称
     */
    String cnName() default "";

    /**
     * 图标
     */
    String icon() default "skill";

    /**
     * 描述
     */
    String describe() default "";

    /**
     * 授权码
     */
    String authzCode() default "";

    /**
     * 参数
     */
    String perms() default "";

    /**
     * 是否显示
     */
    String isShow() default "N";

    /**
     * 启动类型
     */
    String startUpType() default "AUTO";

    /**
     * 服务状态
     */
    String state() default "START";

    /**
     * 方法和类名
     */
    String method() default "";

}
