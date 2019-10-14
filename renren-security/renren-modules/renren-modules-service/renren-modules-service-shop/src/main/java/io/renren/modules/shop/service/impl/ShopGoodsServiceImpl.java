package io.renren.modules.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.shop.dao.ShopMyGoodsDao;
import io.renren.modules.shop.dao.ShopOrderDao;
import io.renren.modules.shop.dao.ShopPayDao;
import io.renren.modules.shop.entity.ShopMyGoodsEntity;
import io.renren.modules.shop.entity.ShopOrderEntity;
import io.renren.modules.shop.entity.ShopPayEntity;
import io.renren.modules.shop.request.*;
import io.renren.modules.sys.dao.SysAttachmentDao;
import io.renren.modules.sys.entity.SysAttachmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.shop.dao.ShopGoodsDao;
import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.service.ShopGoodsService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Service("shopGoodsService")
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsDao, ShopGoodsEntity> implements ShopGoodsService {
    private static final String ORDER_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ShopMyGoodsDao shopMyGoodsDao;

    @Autowired
    private ShopOrderDao shopOrderDao;

    @Autowired
    private ShopPayDao shopPayDao;

    @Autowired
    private SysAttachmentDao sysAttachmentDao;

    @Override
    public Map queryPage(ShopGoodsPageRequest request) {
        Page<ShopGoodsEntity> page = new Page<ShopGoodsEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<ShopGoodsEntity> queryWrapper = new QueryWrapper<ShopGoodsEntity>();
        if (!"0".equals(request.getType())) {
            queryWrapper.eq("CAT_ID", request.getType());
        }
        queryWrapper.orderByDesc("SORT");
        IPage<ShopGoodsEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        List<ShopGoodsEntity> list = pageInfo.getRecords();
        List<Map> mapList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShopGoodsEntity shopGoodsEntity = list.get(i);
                Map maptemp = new HashMap();
                maptemp.put("ShopGoodsEntity", shopGoodsEntity);

                SysAttachmentEntity sysAttachmentEntity = sysAttachmentDao.selectById(shopGoodsEntity.getOriginalImg());
                maptemp.put("SysAttachmentEntity", sysAttachmentEntity);


                QueryWrapper<ShopMyGoodsEntity> qw = new QueryWrapper<>();
                qw.eq("SYS_USER_ID", request.getUserId());
                qw.eq("GOODS_ID", shopGoodsEntity.getId());
                int count = shopMyGoodsDao.selectCount(qw);
                if (count > 0) {
                    maptemp.put("isBuy", true);
                } else {
                    maptemp.put("isBuy", false);
                }
                mapList.add(maptemp);
            }

        }

        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", mapList);

        return map;
    }

    @Override
    public Map info(ShopGoodsInfoRequest request) throws RRException {
        ShopGoodsEntity shopGoodsEntity = baseMapper.selectById(request.getId());
        if (shopGoodsEntity == null) {
            throw new RRException("商品不存在");
        }

        Map maptemp = new HashMap();
        maptemp.put("ShopGoodsEntity", shopGoodsEntity);

        SysAttachmentEntity sysAttachmentEntity = sysAttachmentDao.selectById(shopGoodsEntity.getOriginalImg());
        maptemp.put("SysAttachmentEntity", sysAttachmentEntity);

        QueryWrapper<ShopMyGoodsEntity> qw = new QueryWrapper<>();
        qw.eq("SYS_USER_ID", request.getUserId());
        qw.eq("GOODS_ID", shopGoodsEntity.getId());
        int count = shopMyGoodsDao.selectCount(qw);
        if (count > 0) {
            maptemp.put("isBuy", true);
        } else {
            maptemp.put("isBuy", false);
        }

        return maptemp;
    }

    /***
     * 商品-发布redis
     * @param request
     */
    @Override
    public void push(ShopGoodsPushRequest request) {

    }

    /**
     * 购买
     *
     * @param request
     * @throws RRException
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public Map<String, String> buy(ShopGoodsBuyRequest request, HttpServletRequest httpRequest) throws RRException {
        try {
            ShopGoodsEntity shopGoodsEntity = baseMapper.selectById(request.getId());
            QueryWrapper<ShopMyGoodsEntity> qw = new QueryWrapper<>();
            qw.eq("GOODS_ID", request.getId());
            qw.eq("SYS_USER_ID", request.getUserId());
            int count = shopMyGoodsDao.selectCount(qw);
            if (count > 0) {
                throw new RRException("您已购买此商品");

            }

            //获取appid
            String appConfigJson = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.PAY_CODE);
            if (StringUtils.isBlank(appConfigJson)) {
                throw new RRException("PAY_CODE配置");
            }
            Map appConfigMap = new Gson().fromJson(appConfigJson, Map.class);
            String appConfigVualeJson = appConfigMap.get("value") + "";
            Map appConfigVualeMap = new Gson().fromJson(appConfigVualeJson, Map.class);


            Map parm = new HashMap();
            Map<String, String> restmap = null;

            String payCode = UUID.randomUUID().toString().replaceAll("-", "");
            BigDecimal total_fee = shopGoodsEntity.getShopPrice().multiply(new BigDecimal(100));

            String openJson = stringRedisTemplate.opsForValue().get(Constants.SYS_OAUTH_CODE_USERID + request.getUserId());
            Map openMap = new Gson().fromJson(openJson, Map.class);


            parm.put("appid", appConfigVualeMap.get("appid"));
            parm.put("mch_id", appConfigVualeMap.get("mchid"));
            parm.put("nonce_str", PayUtil.getNonceStr());
            parm.put("body", "小鲟视频购买");
            parm.put("out_trade_no", payCode);
            parm.put("openid", openMap.get("openId"));


            if (null != total_fee) {
                parm.put("total_fee", total_fee.intValue() + "");
            } else {
                parm.put("total_fee", "1");
            }
            parm.put("spbill_create_ip", PayUtil.getRemoteAddrIp(httpRequest));
//            parm.put("notify_url", "https://sharkapp.natapp4.cc/api-shop/shop/shopgoods/notify/");
            parm.put("notify_url", "http://wx.wuhanxiaoxun.com/api-shop/shop/shopgoods/notify/");

//            parm.put("notify_url", "http://eurekaserver:8200/shop/shopgoods/notify/");
            parm.put("trade_type", "JSAPI");
            parm.put("sign_type", "MD5");
            parm.put("sign", PayUtil.getSign(parm, appConfigVualeMap.get("apisecret").toString()));

            String restxml = HttpUtils.post(ORDER_PAY, XmlUtil.xmlFormat(parm, false));
            restmap = XmlUtil.doXMLParse(restxml);
            log.debug("restmap:{}", restmap);

            Map<String, String> payMap = new HashMap<>();
            if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {

                ShopPayEntity shopPayEntity = new ShopPayEntity();
                shopPayEntity.setSysUserId(request.getUserId());
                shopPayEntity.setGoodsId(request.getId());
                shopPayEntity.setPayCode(payCode);
                shopPayEntity.setPrice(shopGoodsEntity.getShopPrice());
                shopPayEntity.setPayStatus("0");
                shopPayDao.insert(shopPayEntity);

                ShopOrderEntity shopOrderEntity = new ShopOrderEntity();
                shopOrderEntity.setSysUserId(request.getUserId());
                shopOrderEntity.setGoodsId(request.getId());
                shopOrderEntity.setOrderSn(PayUtil.getTradeNo());
                shopOrderEntity.setOrderAmount(shopGoodsEntity.getShopPrice());
                shopOrderEntity.setOrderStatus(0);
                shopOrderEntity.setPayStatus(0);
                shopOrderEntity.setPayCode(payCode);
                shopOrderEntity.setAddTime(DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

                shopOrderDao.insert(shopOrderEntity);


                payMap.put("appId", appConfigVualeMap.get("appid") + "");
                payMap.put("package", "prepay_id=" + restmap.get("prepay_id"));
                payMap.put("nonceStr", PayUtil.getNonceStr());
                payMap.put("timeStamp", PayUtil.payTimestamp());
                payMap.put("signType", "MD5");
                log.debug("支付成功restmap:" + restmap.toString());
                payMap.put("paySign", PayUtil.getSign(payMap, appConfigVualeMap.get("apisecret") + ""));
                log.debug("支付成功");
                return payMap;
            } else {
                throw new RRException("统一下单失败，异常信息：" + restxml);
            }
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    private String createQrCode(String url) throws IOException {
        url = java.net.URLDecoder.decode(url, "UTF-8");
        return url;
    }


    public JSONObject getJsapiUrl(String accessToken) throws Exception {
        String getJsapiUrl = UseValue.getJsapiUrl.replace("ACCESS_TOKEN", accessToken);
        log.info("getOAuthUserNews Value:" + getJsapiUrl);
        HttpGet get = HttpClientConnectionManager.getGetMethod(getJsapiUrl);
        HttpResponse response = httpclient.execute(get);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        return jsonObject;
    }

    /**
     * 微信OAuth2.0授权（目前微信只支持在微信客户端发送连接，实现授权）
     */
    public Map getTokenUrl(String appid, String appsecret) throws Exception {
        appsecret = URLEncoder.encode(appsecret, "utf-8");
        String getTokenUrl = UseValue.getTokenUrl.replace("APPID", appid).replace("APPSECRET", appsecret);
        log.debug("OAuth2.0授权:" + getTokenUrl);
        HttpPost get = HttpClientConnectionManager.getPostMethod(getTokenUrl);
        HttpResponse response = httpclient.execute(get);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (!StringUtils.isBlank(jsonObject.getString("errcode")) && !"0".equals(jsonObject.getString("errcode"))) {
            throw new RRException("OAuth2.0授权异常：" + jsonObject.toString());
        }

        log.info("OAuth2.0授权jsonObject:" + jsonObject);
        Map accessToken = new HashMap();
        accessToken.put("access_token", jsonObject.getString("access_token"));
        accessToken.put("expires_in", jsonObject.getIntValue("expires_in"));
        accessToken.put("refresh_token", jsonObject.getString("refresh_token"));
        accessToken.put("openid", jsonObject.getString("openid"));
        accessToken.put("scope", jsonObject.getString("scope"));
        return accessToken;
    }


    /**
     * 获取access_token 字符串
     * 微信服务器会返回json格式的数据：{"access_token":"ACCESS_TOKEN","expires_in":7200}
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public String getAccessTokenToStr(String appid, String appsecret) throws Exception {
        log.info("getAccessTokenToStr------------");
        Map tokenMap = getTokenUrl(appid, appsecret);
        //查询数据库里面是否有token
        return tokenMap.get("access_token") + "";
    }

    /***
     * 购买初始化
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public Map<String, String> configInfo(ShopGoodsConfigInfoRequest request) throws RRException {
        try {

            //获取appid
            String appConfigJson = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.PAY_CODE);
            if (StringUtils.isBlank(appConfigJson)) {
                throw new RRException("PAY_CODE配置");
            }
            Map appConfigMap = new Gson().fromJson(appConfigJson, Map.class);
            String appConfigVualeJson = appConfigMap.get("value") + "";
            Map appConfigVualeMap = new Gson().fromJson(appConfigVualeJson, Map.class);

            String url = createQrCode(request.getUrl());
            String accessTokenToStr = getAccessTokenToStr(appConfigVualeMap.get("appid") + "", appConfigVualeMap.get("appsecret") + "");
            JSONObject jsapiUrl = getJsapiUrl(accessTokenToStr);
            log.debug("jsapiUrl:" + jsapiUrl.toString());
            String ticket = (String) jsapiUrl.get("ticket");
            log.debug("ticket:" + ticket);
            String no = PayUtil.getNonceStr();
            log.debug("" + no);
            String timestamp = String.valueOf((new Date().getTime() / 1000));
            log.debug("timestamp：" + timestamp);
            String str = "jsapi_ticket=" + ticket + "&noncestr=" + no + "&timestamp=" + timestamp + "&url=" + url;
            SortedMap<String, String> sm = new TreeMap<String, String>();
            String signature = SignatureUtil.sha1Encrypt(str);
            log.debug("str：" + str);
            log.debug("url：" + url);
            log.debug("signature:" + signature);
            sm.put("noncestr", no);
            sm.put("jsapi_ticket", ticket);
            sm.put("timestamp", timestamp);
            sm.put("url", url);
            sm.put("sign", signature);
            sm.put("appid", appConfigVualeMap.get("appid") + "");
//            String header = request.getHeader("user-agent");
            return sm;
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /***
     * 回调
     * @param request
     * @param response
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public String notify(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        log.info("第一步");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        try {
            log.info("第二步");
            log.info("支付回调已进入");
            ServletInputStream in = request.getInputStream();
            String resxml = FileUtil.readInputStream2String(in);
            log.info("支付返回结果：" + resxml);
            Map<String, String> restmap = XmlUtil.doXMLParse(resxml);
            log.info("支付结果通知：" + restmap);
            if ("SUCCESS".equals(restmap.get("result_code"))) {
                // 订单支付成功 业务处理
                String out_trade_no = restmap.get("out_trade_no"); // 商户订单号
                QueryWrapper<ShopPayEntity> qw = new QueryWrapper<>();
                qw.eq("PAY_CODE", out_trade_no);
                ShopPayEntity shopPayEntity = shopPayDao.selectOne(qw);
                if (shopPayEntity == null) {
                    throw new RRException("订单不存在");
                }
                shopPayEntity.setPayStatus("1");
                shopPayDao.updateById(shopPayEntity);

                QueryWrapper<ShopOrderEntity> qwo = new QueryWrapper<>();
                qwo.eq("PAY_CODE", out_trade_no);
                ShopOrderEntity shopOrderEntity = shopOrderDao.selectOne(qwo);
                if (shopOrderEntity == null) {
                    throw new RRException("订单不存在");
                }
                shopOrderEntity.setOrderStatus(1);
                shopOrderEntity.setPayStatus(1);
                shopOrderDao.updateById(shopOrderEntity);

                ShopMyGoodsEntity shopMyGoodsEntity = new ShopMyGoodsEntity();
                shopMyGoodsEntity.setGoodsId(shopPayEntity.getGoodsId());
                shopMyGoodsEntity.setSysUserId(shopPayEntity.getSysUserId());
                shopMyGoodsDao.insert(shopMyGoodsEntity);

                ShopGoodsEntity eGoods = baseMapper.selectById(shopPayEntity.getGoodsId());
                eGoods.setSalesSum(eGoods.getSalesSum() + 1);
                baseMapper.updateById(eGoods);


            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        log.info("第三步");
        // 处理成功后相应给响应xml
        Map<String, String> respMap = new HashMap<>();
        respMap.put("return_code", "SUCCESS"); //相应给微信服务器
        respMap.put("return_msg", "OK");
        String resXml = XmlUtil.xmlFormat(respMap, true);
        return resXml;
    }

}
