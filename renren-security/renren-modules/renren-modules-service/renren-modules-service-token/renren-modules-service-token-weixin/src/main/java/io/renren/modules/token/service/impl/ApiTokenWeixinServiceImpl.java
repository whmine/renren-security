package io.renren.modules.token.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.utils.CodeUtils;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.HttpClientWeixinUtils;
import io.renren.framework.utils.Producer;
import io.renren.modules.token.request.LoginWeixinRequest;
import io.renren.modules.token.service.ApiTokenWeixinService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("apiTokenWeixinService")
public class ApiTokenWeixinServiceImpl implements ApiTokenWeixinService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    /***
     *登录-微信账号
     * @param form
     * @return
     */
    @Override
    public Map loginWeixin(LoginWeixinRequest form) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        String token = "";

        Map sysOauthCodeEntity = null;
        String weixinLoginConfig = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.WEIXIN_LOGIN_CONFIG);

        if (StringUtils.isBlank(weixinLoginConfig)) {
            throw new Exception("微信配置不存在");
        }
        Map weixinLoginConfigMap = gson.fromJson(weixinLoginConfig, Map.class);
        String value = weixinLoginConfigMap.get("value") + "";
        if (StringUtils.isBlank(value)) {
            throw new Exception("微信配置错误");
        }
        Map weixinLoginConfigMapValue = gson.fromJson(value, Map.class);

        String url = weixinLoginConfigMapValue.get(Constants.WEIXIN_URL)
                + "?" + Constants.WEIXIN_APPID + "=" + weixinLoginConfigMapValue.get(Constants.WEIXIN_APPID)
                + "&" + Constants.WEIXIN_SECRET + "=" + weixinLoginConfigMapValue.get(Constants.WEIXIN_SECRET)
                + "&" + Constants.WEIXIN_JS_CODE + "=" + form.getCode()
                + "&" + Constants.WEIXIN_GRANT_TYPE + "=" + weixinLoginConfigMapValue.get(Constants.WEIXIN_GRANT_TYPE);


        String openJson = HttpClientWeixinUtils.httpRequest(url, Constants.HTTP_GET, null);
        Map openMap = gson.fromJson(openJson, Map.class);//json.getString("openid");
        String openid = openMap.get(Constants.WEIXIN_OPEN_ID) + "";
        //绑定授权
        String jsonOpen = stringRedisTemplate.opsForValue().get(Constants.SYS_OAUTH_CODE_OPENID + openid);
        String sysUserId = "";
        if (StringUtils.isBlank(jsonOpen)) {
            sysUserId = CodeUtils.generateID();
            //第一次登录
            sysOauthCodeEntity = new HashMap();
            sysOauthCodeEntity.put("openId", openid);
            sysOauthCodeEntity.put("sysUserId", sysUserId);
            sysOauthCodeEntity.put("name", form.getName());
            sysOauthCodeEntity.put("url", form.getUrl());
            stringRedisTemplate.opsForValue().set(Constants.SYS_OAUTH_CODE_OPENID + openid, gson.toJson(sysOauthCodeEntity));
            stringRedisTemplate.opsForValue().set(Constants.SYS_OAUTH_CODE_USERID + sysUserId, gson.toJson(sysOauthCodeEntity));

            //配置默认角色
            String roleValue = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.WEIXIN_OPEN_ROLE_ID);
            String roleId = "1";
            if (StringUtils.isBlank(roleValue)) {
                roleId = "1";
            } else {
                Map roleValueMap = new Gson().fromJson(roleValue, Map.class);
                roleId = roleValueMap.get("value") + "";
            }
            stringRedisTemplate.opsForValue().set(Constants.SYS_RESOURCES + sysUserId + "", stringRedisTemplate.opsForValue().get(Constants.SYS_ROLE_RESOURCES + roleId));



            //初始化客户数据
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode"), gson.toJson(sysOauthCodeEntity));
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer"), gson.toJson(sysOauthCodeEntity));


        } else {
            sysOauthCodeEntity = gson.fromJson(jsonOpen, Map.class);
            sysUserId = sysOauthCodeEntity.get("sysUserId") + "";
        }
        //创建更新
        token = oauthToken(gson, sysUserId);
        //授权
        authorizationInfo(gson, sysUserId);
        map.put("token", token);
        return map;
    }


    /***
     * 创建更新TOKEN
     * @param gson
     * @param sysUserId
     * @return
     * @throws Exception
     */
    private String oauthToken(Gson gson, String sysUserId) throws Exception {
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
            Map expiresInMap = gson.fromJson(expiresIn, Map.class);

            Integer expiresInTime = new Integer(expiresInMap.get("value") + "");
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
    private void authorizationInfo(Gson gson, String userId) throws Exception {

        String jsonOauth = stringRedisTemplate.opsForValue().get(Constants.SYS_RESOURCES + userId);
        if (StringUtils.isBlank(jsonOauth)) {
            throw new Exception("权限不足");
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
