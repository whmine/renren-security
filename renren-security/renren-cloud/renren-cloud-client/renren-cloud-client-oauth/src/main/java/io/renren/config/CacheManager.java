package io.renren.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.modules.oauth.entity.OauthTokenEntity;
import io.renren.modules.oauth.service.OauthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CacheManager implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private OauthTokenService oauthTokenService;

    @Override
    public void run(String... args) throws Exception {
        log.debug("缓存管理-->缓存初始化:服务启动!");
        log.debug("缓存管理-->缓存初始化:初始化已登录TOKEN用户信息!");

        List<OauthTokenEntity> oauthTokenEntityList = oauthTokenService.oauthTokenCacheManager();
        if (oauthTokenEntityList != null && oauthTokenEntityList.size() > 0) {
            for (OauthTokenEntity entity : oauthTokenEntityList) {
                long expireTime = (entity.getExpireTime().getTime() - System.currentTimeMillis()) / Constants.MILLISECOND;
                stringRedisTemplate.opsForValue().set(Constants.X_OAUTH_TOKEN_TOKEN + entity.getToken(), new Gson().toJson(entity), expireTime, TimeUnit.SECONDS);
                stringRedisTemplate.opsForValue().set(Constants.X_OAUTH_TOKEN_USERID + entity.getSysUserId(), new Gson().toJson(entity), expireTime, TimeUnit.SECONDS);
            }

        }

        log.debug("缓存管理-->缓存初始化:初始化完成!");
    }
}
