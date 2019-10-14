package io.renren.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.request.SysRoleCacheRequest;
import io.renren.modules.sys.service.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CacheManager implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private SysServiceService sysServiceService;
    @Autowired
    private SysOauthCodeService sysOauthCodeService;


    @Override
    public void run(String... args) throws Exception {
        log.debug("缓存管理-->缓存初始化:服务启动!");
        log.debug("缓存管理-->缓存初始化:初始化用户信息!");
        List<SysUserEntity> sysUserList = sysUserService.sysUserCacheManager();
        if (sysUserList != null && sysUserList.size() > 0) {
            for (SysUserEntity entity : sysUserList) {
                sysUserService.authCacheManager(entity);
            }
        }
        log.debug("缓存管理-->缓存初始化:初始化角色资源信息!");
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        if (sysRoleEntityList != null && sysRoleEntityList.size() > 0) {
            for (SysRoleEntity entity : sysRoleEntityList) {
                List<SysResourcesEntity> list = sysRoleService.sysRoleResourcesCacheManager(entity);
                stringRedisTemplate.opsForValue().set(Constants.SYS_ROLE_RESOURCES + entity.getId(), new Gson().toJson(list));
            }

        }


        log.debug("缓存管理-->缓存初始化:初始化配置信息!");
        List<SysConfigEntity> sysConfigList = sysConfigService.sysConfigCacheManager();
        if (sysConfigList != null && sysConfigList.size() > 0) {
            for (SysConfigEntity entity : sysConfigList) {
                stringRedisTemplate.opsForValue().set(Constants.SYS_CONFIG + entity.getName(), new Gson().toJson(entity));
            }
        }


        log.debug("缓存管理-->缓存初始化:初始化系统服务信息!");
        List<SysServiceEntity> sysServiceEntityList = sysServiceService.sysServiceCacheManager();
        if (sysServiceEntityList != null && sysServiceEntityList.size() > 0) {
            for (SysServiceEntity entity : sysServiceEntityList) {
                stringRedisTemplate.opsForValue().set(Constants.SYS_SERVICE + entity.getId(), new Gson().toJson(entity));
            }
        }

        log.debug("缓存管理-->缓存初始化:初始化授权CODE信息!");
        List<SysOauthCodeEntity> sysOauthCodeEntityList = sysOauthCodeService.list();
        if (sysOauthCodeEntityList != null && sysOauthCodeEntityList.size() > 0) {
            for (SysOauthCodeEntity entity : sysOauthCodeEntityList) {
                stringRedisTemplate.opsForValue().set(Constants.SYS_OAUTH_CODE_OPENID + entity.getOpenId(), new Gson().toJson(entity));
                stringRedisTemplate.opsForValue().set(Constants.SYS_OAUTH_CODE_USERID + entity.getSysUserId(), new Gson().toJson(entity));


            }

        }


        log.debug("缓存管理-->缓存初始化:初始化完成!");
    }
}
