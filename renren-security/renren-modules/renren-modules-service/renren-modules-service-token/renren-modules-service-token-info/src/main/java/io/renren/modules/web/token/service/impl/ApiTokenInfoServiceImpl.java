package io.renren.modules.web.token.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.modules.token.request.InfoRequest;
import io.renren.modules.web.token.service.ApiTokenInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("apiTokenInfoService")
public class ApiTokenInfoServiceImpl implements ApiTokenInfoService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map info(InfoRequest request) throws RRException {
        Map resultMap = new HashMap();
        //登录标识信息
        resultMap.put("sysUser", stringRedisTemplate.opsForValue().get(Constants.SYS_USER_ID + request.getUserId()));

        //角色信息
        String sysRoleListStr = stringRedisTemplate.opsForValue().get(Constants.SYS_ROLE_LIST + request.getUserId());
        List list = new ArrayList();
        List sysRoleList = new Gson().fromJson(sysRoleListStr, ArrayList.class);
        if (sysRoleList != null && sysRoleList.size() > 0) {
            for (int i = 0; i < sysRoleList.size(); i++) {
                Map role = (Map) sysRoleList.get(i);
                list.add(role.get("name"));
            }
        }

        resultMap.put("sysRoles", new Gson().toJson(list));
        resultMap.put("authzCodes", stringRedisTemplate.opsForValue().get(Constants.X_AUTHZ_CODE + request.getUserId()));

        //========================================
        //基础信息
        resultMap.put("cuInfo", stringRedisTemplate.opsForValue().get(Constants.CU_INFO + request.getUserId()));
        //统计信息
        resultMap.put("cuCount", stringRedisTemplate.opsForValue().get(Constants.CU_COUNT + request.getUserId()));
        //账号信息
        resultMap.put("cuAccount", stringRedisTemplate.opsForValue().get(Constants.CU_ACCOUNT + request.getUserId()));
        return resultMap;
    }
}
