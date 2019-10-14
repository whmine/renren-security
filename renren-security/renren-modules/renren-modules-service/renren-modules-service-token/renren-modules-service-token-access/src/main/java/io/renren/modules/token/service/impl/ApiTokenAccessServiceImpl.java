package io.renren.modules.token.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.CodeUtils;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.HttpClientWeixinUtils;
import io.renren.framework.utils.Producer;
import io.renren.modules.token.request.LoginAccessRequest;
import io.renren.modules.token.request.LoginWeixinRequest;
import io.renren.modules.token.service.ApiTokenAccessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("apiTokenAccessService")
public class ApiTokenAccessServiceImpl implements ApiTokenAccessService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    /***
     *登录-微信公众账号
     * @param form
     * @return
     */
    @Override
    public Map loginAccess(LoginAccessRequest form) throws RRException {
        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        String token = "";

        Map sysOauthCodeEntity = null;
        String weixinLoginConfig = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.ACCESS_LOGIN_CONFIG);

        if (StringUtils.isBlank(weixinLoginConfig)) {
            throw new RRException("微信配置不存在", 1);
        }
        Map weixinLoginConfigMap = gson.fromJson(weixinLoginConfig, Map.class);
        String value = weixinLoginConfigMap.get("value") + "";
        if (StringUtils.isBlank(value)) {
            throw new RRException("微信配置错误", 1);
        }
        Map weixinLoginConfigMapValue = gson.fromJson(value, Map.class);
        log.debug("公众号code：" + form.getCode());
        String url = weixinLoginConfigMapValue.get(Constants.WEIXIN_URL)
                + "?" + Constants.WEIXIN_APPID + "=" + weixinLoginConfigMapValue.get(Constants.WEIXIN_APPID)
                + "&" + Constants.WEIXIN_SECRET + "=" + weixinLoginConfigMapValue.get(Constants.WEIXIN_SECRET)
                + "&" + Constants.WEIXIN_CODE + "=" + form.getCode()
                + "&" + Constants.WEIXIN_GRANT_TYPE + "=" + weixinLoginConfigMapValue.get(Constants.WEIXIN_GRANT_TYPE);

        log.debug("公众号URL：" + url);
        String openJson = HttpClientWeixinUtils.httpRequest(url, Constants.HTTP_GET, null);
        log.debug("公众号登录返回值：" + openJson);
        Map openMap = gson.fromJson(openJson, Map.class);//json.getString("openid");
        String openid = openMap.get(Constants.WEIXIN_OPEN_ID).toString();
        String access_token = openMap.get(Constants.WEIXIN_ACCESS_TOKEN) + "";
        log.debug("公众号登录返回值===errcode=" + openMap.get("errcode"));
        log.debug("公众号登录返回值===openid=" + openid + "========access_token=====：" + access_token);
        if (openMap.get("errcode") != null) {
            throw new RRException("openid错误", 1);
        }

        //绑定授权
        String jsonOpen = stringRedisTemplate.opsForValue().get(Constants.SYS_OAUTH_CODE_OPENID + openid);
        String sysUserId = "";
        if (StringUtils.isBlank(jsonOpen)) {
            sysUserId = CodeUtils.generateID();
            //第一次登录
            sysOauthCodeEntity = new HashMap();
            sysOauthCodeEntity.put("openId", openid);
            sysOauthCodeEntity.put("sysUserId", sysUserId);
            String urlName = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
            String infoJson = HttpClientWeixinUtils.httpRequest(urlName, Constants.HTTP_GET, null);
            log.debug("公众号登录获取用户信息：" + infoJson);
            Map infoMap = gson.fromJson(infoJson, Map.class);
            sysOauthCodeEntity.put("name", infoMap.get("nickname") + "");
            sysOauthCodeEntity.put("url", infoMap.get("headimgurl") + "");
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


            //绑定角色
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode"), gson.toJson(sysOauthCodeEntity));
            //初始化客户数据
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer"), gson.toJson(sysOauthCodeEntity));


        } else {
            sysOauthCodeEntity = gson.fromJson(jsonOpen, Map.class);
            sysUserId = sysOauthCodeEntity.get("sysUserId") + "";
        }
        //创建更新
        token = oauthToken(gson, sysUserId);
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
    private void authorizationInfo(Gson gson, String userId) throws RRException {

        String jsonOauth = stringRedisTemplate.opsForValue().get(Constants.SYS_RESOURCES + userId);
        if (StringUtils.isBlank(jsonOauth)) {
            return;
//            throw new RRException("权限不足");
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
