package io.renren.config;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.entity.CuCountEntity;
import io.renren.modules.cu.entity.CuInfoEntity;
import io.renren.modules.cu.service.CuAccountService;
import io.renren.modules.cu.service.CuCountService;
import io.renren.modules.cu.service.CuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class CacheManager implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CuInfoService cuInfoService;

    @Autowired
    private CuAccountService cuAccountService;

    @Autowired
    private CuCountService cuCountService;

    @Override
    public void run(String... args) throws Exception {
        log.debug("缓存管理-->缓存初始化:服务启动!");
        log.debug("缓存管理-->缓存初始化:初始化用户信息!");
        List<CuInfoEntity> cuInfoList = cuInfoService.list();
        if (cuInfoList != null && cuInfoList.size() > 0) {
            for (CuInfoEntity entity : cuInfoList) {
                stringRedisTemplate.opsForValue().set(Constants.CU_INFO + entity.getId(), new Gson().toJson(entity));
            }
        }

        log.debug("缓存管理-->缓存初始化:初始化用户账户信息!");
        List<CuAccountEntity> cuAccountList = cuAccountService.list();
        if (cuAccountList != null && cuAccountList.size() > 0) {
            for (CuAccountEntity entity : cuAccountList) {
                stringRedisTemplate.opsForValue().set(Constants.CU_ACCOUNT + entity.getId(), new Gson().toJson(entity));
            }
        }

        log.debug("缓存管理-->缓存初始化:初始化用户统计信息!");
        List<CuCountEntity> cuCountList = cuCountService.list();
        if (cuCountList != null && cuCountList.size() > 0) {
            for (CuCountEntity entity : cuCountList) {
                stringRedisTemplate.opsForValue().set(Constants.CU_COUNT + entity.getId(), new Gson().toJson(entity));
            }
        }

        log.debug("缓存管理-->缓存初始化:初始化完成!");
    }
}
