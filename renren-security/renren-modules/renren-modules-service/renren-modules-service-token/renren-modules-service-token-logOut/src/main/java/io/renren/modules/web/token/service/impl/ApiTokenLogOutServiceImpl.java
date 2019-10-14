package io.renren.modules.web.token.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.framework.utils.Producer;
import io.renren.modules.token.request.LogOutRequest;
import io.renren.modules.web.token.service.ApiTokenLogOutService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("apiTokenLogOutService")
public class ApiTokenLogOutServiceImpl implements ApiTokenLogOutService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    /***
     *登录-TOKEN退出
     * @param request
     * @return
     */
    @Override
    public void logOut(LogOutRequest request) throws RRException {
        String jsonToken = stringRedisTemplate.opsForValue().get(Constants.X_OAUTH_TOKEN_USERID + request.getUserId());
        if (!StringUtils.isBlank(jsonToken)) {
            stringRedisTemplate.delete(Constants.X_OAUTH_TOKEN_USERID + request.getUserId());
            //未超期
            Map oauthTokenEntity = new Gson().fromJson(jsonToken, Map.class);
            String token = oauthTokenEntity.get("token") + "";
            stringRedisTemplate.delete(token);
            stringRedisTemplate.delete(Constants.X_AUTHZ_CODE + request.getUserId());
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.oauth.outToken"), oauthTokenEntity);
        }

    }
}
