package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysSmsCodeEntity;
import io.renren.modules.sys.request.SysSmsCodePageRequest;
import io.renren.modules.sys.request.SysSmsCodeSendMailRequest;
import io.renren.modules.sys.request.SysSmsCodeSendRequest;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 短信验证码
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:12:24
 */
public interface SysSmsCodeService extends IService<SysSmsCodeEntity> {

    Map queryPage(SysSmsCodePageRequest request);

    /***
     * 发送短信
     * @param request
     * @throws RRException
     * @throws UnsupportedEncodingException
     */
    void send(SysSmsCodeSendRequest request) throws RRException, UnsupportedEncodingException;


    /***
     * 发送短信
     * @param request
     * @throws RRException
     * @throws UnsupportedEncodingException
     */
    void sendRedis(SysSmsCodeSendRequest request) throws RRException, UnsupportedEncodingException;

    /***
     * 发送邮件
     * @param request
     * @throws RRException
     * @throws UnsupportedEncodingException
     */
    void sendMail(SysSmsCodeSendMailRequest request) throws RRException, UnsupportedEncodingException;

}

