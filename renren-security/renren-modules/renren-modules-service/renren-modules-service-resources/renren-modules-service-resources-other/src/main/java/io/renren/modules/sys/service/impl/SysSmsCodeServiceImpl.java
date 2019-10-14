package io.renren.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.modules.sys.request.SysSmsCodeSendMailRequest;
import io.renren.modules.sys.request.SysSmsCodeSendRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.sys.dao.SysSmsCodeDao;
import io.renren.modules.sys.entity.SysSmsCodeEntity;
import io.renren.modules.sys.service.SysSmsCodeService;
import io.renren.modules.sys.request.SysSmsCodePageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("sysSmsCodeService")
public class SysSmsCodeServiceImpl extends ServiceImpl<SysSmsCodeDao, SysSmsCodeEntity> implements SysSmsCodeService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(SysSmsCodePageRequest request) {
        Page<SysSmsCodeEntity> page = new Page<SysSmsCodeEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysSmsCodeEntity> queryWrapper = new QueryWrapper<SysSmsCodeEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysSmsCodeEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 发送短信
     * @param request
     * @throws RRException
     * @throws UnsupportedEncodingException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void send(SysSmsCodeSendRequest request) throws RRException, UnsupportedEncodingException {
        SysSmsCodeEntity param = new SysSmsCodeEntity();
        param.setCodeMobile(request.getPhone());
        param.setCodeState(Constants.STATE_YES);
        param.setCodeType(request.getSmsType());
        QueryWrapper<SysSmsCodeEntity> queryWrapper = new QueryWrapper<SysSmsCodeEntity>(param);
        queryWrapper.orderByAsc("CODE_CREATE_TIME");
        List<SysSmsCodeEntity> list = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            if (null != list.get(0) && list.get(0).getCodeCreateTime().getTime() + 1000 * 60 > System.currentTimeMillis()) {
                throw new RRException("60秒内不能重复发送");
            }
        }

        String flagSms = stringRedisTemplate.opsForValue().get(Constants.FLAG_SMS);
        if (StringUtils.isBlank(flagSms)) {
            SysSmsCodeEntity code = new SysSmsCodeEntity();
            code.setCodeType(request.getSmsType());
            code.setCodeCode("123456");
            code.setCodeCreateTime(new Date());
            code.setCodeMobile(request.getPhone());
            code.setCodeState(Constants.STATE_YES);
            baseMapper.insert(code);
        }
        Map mapProfile = new Gson().fromJson(flagSms, Map.class);
        if (StringUtils.isBlank(mapProfile.get("value") + "")) {
            SysSmsCodeEntity code = new SysSmsCodeEntity();
            code.setCodeType(request.getSmsType());
            code.setCodeCode("123456");
            code.setCodeCreateTime(new Date());
            code.setCodeMobile(request.getPhone());
            code.setCodeState(Constants.STATE_YES);
            baseMapper.insert(code);
        }

        if (Constants.STATE_YES.equals(mapProfile.get("value"))) {
            String code = Md5Util.getCodeByInt(6);
            String result = SmsWhUtil.send(request.getPhone(), code, SmsWhUtil.SMS_MODE);
            if (null != JSONObject.parseObject(result) && "0".equals(JSONObject.parseObject(result).getString("status"))) {
                SysSmsCodeEntity appCode = new SysSmsCodeEntity();
                appCode.setCodeType(request.getSmsType());
                appCode.setCodeCode(code);
                appCode.setCodeCreateTime(new Date());
                appCode.setCodeMobile(request.getPhone());
                appCode.setCodeState(Constants.STATE_YES);
                baseMapper.insert(appCode);
            } else {
                throw new RRException("短信发送失败");
            }
        } else {
            SysSmsCodeEntity code = new SysSmsCodeEntity();
            code.setCodeType(request.getSmsType());
            code.setCodeCode("123456");
            code.setCodeCreateTime(new Date());
            code.setCodeMobile(request.getPhone());
            code.setCodeState(Constants.STATE_YES);
            baseMapper.insert(code);
        }
    }


    /***
     * 发送短信
     * @param request
     * @throws RRException
     * @throws UnsupportedEncodingException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void sendRedis(SysSmsCodeSendRequest request) throws RRException, UnsupportedEncodingException {
        String code = stringRedisTemplate.opsForValue().get(request.getPhone() + Constants.STATE_YES + request.getSmsType());
        if (StringUtils.isBlank(code)) {
            throw new RRException("60秒内不能重复发送");
        }

        String flagSms = stringRedisTemplate.opsForValue().get(Constants.FLAG_SMS);
        if (StringUtils.isBlank(flagSms)) {
            code = "123456";
        }
        Map mapProfile = new Gson().fromJson(flagSms, Map.class);
        if (StringUtils.isBlank(mapProfile.get("value") + "")) {
            code = "123456";
        }

        if (Constants.STATE_YES.equals(mapProfile.get("value"))) {
            code = Md5Util.getCodeByInt(6);
            String result = SmsWhUtil.send(request.getPhone(), code, SmsWhUtil.SMS_MODE);
            if (null != JSONObject.parseObject(result) && "0".equals(JSONObject.parseObject(result).getString("status"))) {
            } else {
                throw new RRException("短信发送失败");
            }
        } else {
            code = "123456";
        }

        stringRedisTemplate.opsForValue().set(request.getPhone() + Constants.STATE_YES + request.getSmsType(), code, 60, TimeUnit.SECONDS);
    }

    /***
     * 发送邮件
     * @param request
     * @throws RRException
     * @throws UnsupportedEncodingException
     */
    @Override
    public void sendMail(SysSmsCodeSendMailRequest request) throws RRException, UnsupportedEncodingException {
        if (!request.getMail().matches("^\\w+@(\\w+\\.)+\\w+$")) {
            throw new RRException("邮箱格式错误");
        }

        MailBean mailBean = new MailBean();
        mailBean.setRecipient(request.getMail());
        mailBean.setSubject("申请资料");
        mailBean.setContent("");
        mailBean.setType(request.getSmsType());
        SendMailUtils.sendTemplateMail(mailBean);
    }

}
