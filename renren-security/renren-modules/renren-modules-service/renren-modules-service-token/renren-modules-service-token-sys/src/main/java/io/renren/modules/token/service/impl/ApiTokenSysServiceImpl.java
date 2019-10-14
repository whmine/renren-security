package io.renren.modules.token.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.CodeUtils;
import io.renren.common.utils.DateUtils;
import io.renren.framework.utils.Producer;
import io.renren.modules.token.request.LoginSysRequest;
import io.renren.modules.token.service.ApiTokenSysService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("apiTokenSysService")
public class ApiTokenSysServiceImpl implements ApiTokenSysService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;


    /***
     *登录-本地账号
     * @param form
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Map loginSys(LoginSysRequest form) throws RRException {
        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        String token = "";
        String jsonUser = stringRedisTemplate.opsForValue().get(Constants.SYS_USER_NAME + form.getUsername());
        if (!StringUtils.isBlank(jsonUser)) {

            Map sysUserEntity = gson.fromJson(jsonUser, Map.class);
            if (!sysUserEntity.get("isLocked").equals(Constants.STATE_YES)) {
                throw new RRException("账号被锁定", 2);
            }

            //密码错误
            if (!sysUserEntity.get("sysPasswd").equals(DigestUtils.sha256Hex(form.getPassword()))) {
                throw new RRException("手机号或密码错误", 3);
            }
            String sysUserId = sysUserEntity.get("id") + "";

            //创建更新
            token = oauthToken(gson, sysUserId);

            //授权
            authorizationInfo(gson, sysUserId);


            map.put("token", token);

        } else {
            throw new RRException("用户不存在", 4);
        }

        return map;
    }


    /***
     * 创建更新TOKEN
     * @param gson
     * @param sysUserId
     * @return
     * @throws Exception
     */
    private String oauthToken(Gson gson, String sysUserId) throws RRException {
        String token = "";
        Map oauthTokenEntity = null;

        String jsonToken = stringRedisTemplate.opsForValue().get(Constants.X_OAUTH_TOKEN_USERID + sysUserId);

        if (!StringUtils.isBlank(jsonToken)) {
            //未超期
            oauthTokenEntity = gson.fromJson(jsonToken, Map.class);
            token = oauthTokenEntity.get("token") + "";
        } else {
            //超期
            oauthTokenEntity = new HashMap();
            token = CodeUtils.generateID();
            oauthTokenEntity.put("sysUserId", sysUserId);
            oauthTokenEntity.put("token", token);

            //获取全局超期时间
            String expiresIn = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.EXPIRES_IN);
            if (StringUtils.isBlank(expiresIn)) {
                throw new RRException("全局超期时间不存在", 5);
            }
            Map mapExpiresIn = new Gson().fromJson(expiresIn, Map.class);
            if (StringUtils.isBlank(mapExpiresIn.get("value") + "")) {
                throw new RRException("全局超期时间不存在", 5);
            }
            Integer expiresInTime = new Integer(mapExpiresIn.get("value") + "");
            Date expireTime = new Date(System.currentTimeMillis() + expiresInTime * Constants.MILLISECOND);
            oauthTokenEntity.put("expireTime", DateUtils.format(expireTime, DateUtils.DATE_TIME_PATTERN));
            oauthTokenEntity.put("updateTime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

            stringRedisTemplate.opsForValue().set(Constants.X_OAUTH_TOKEN_USERID + sysUserId, gson.toJson(oauthTokenEntity), expiresInTime, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set(Constants.X_OAUTH_TOKEN_TOKEN + token, gson.toJson(oauthTokenEntity), expiresInTime, TimeUnit.SECONDS);
            //保存数据
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.oauth.oauthToken"), oauthTokenEntity);

        }
        return token;
    }

    /***
     * 授权
     * @param gson
     * @param userId
     * @throws Exception
     */
    private void authorizationInfo(Gson gson, String userId) throws RRException {

        String jsonOauth = stringRedisTemplate.opsForValue().get(Constants.SYS_RESOURCES + userId);
        if (StringUtils.isBlank(jsonOauth)) {
            throw new RRException("权限不足", 6);
        }
//        StringBuffer jsonOauthTemp = new StringBuffer();
        List<String> jsonOauthTemp = new ArrayList<>();
        List mapOauthList = gson.fromJson(jsonOauth, ArrayList.class);
        if (mapOauthList != null && mapOauthList.size() > 0) {
            for (int i = 0; i < mapOauthList.size(); i++) {
                Map maptemp = (Map) mapOauthList.get(i);
                //设置服务权限编码
                if (Constants.SYS_RESOURCES_TYPE_SERVICE.equals(maptemp.get("type"))) {
                    String jsonOauthR = stringRedisTemplate.opsForValue().get(Constants.SYS_SERVICE + maptemp.get("value"));
                    if (!StringUtils.isBlank(jsonOauthR)) {
                        Map jsonOauthRMap = gson.fromJson(jsonOauthR, Map.class);
                        jsonOauthTemp.add(jsonOauthRMap.get("authzCode") + "");
                    }
                }
            }

        }
        stringRedisTemplate.opsForValue().set(Constants.X_AUTHZ_CODE + userId, gson.toJson(jsonOauthTemp));

    }

}
