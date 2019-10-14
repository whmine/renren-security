package io.renren.modules.sys.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.renren.modules.sys.entity.SysSmsCodeEntity;
import io.renren.modules.sys.service.SysSmsCodeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 短信验证码
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:12:24
 */
@Slf4j
@Api(tags = "短信验证码")
@RestController
@RequestMapping("/sys/syssmscode")
public class SysSmsCodeController extends AbstractController {
    @Autowired
    private SysSmsCodeService sysSmsCodeService;

    /**
     * 列表
     */
    @Login
    @SysLog("短信验证码-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_list",
            cnName = "短信验证码-列表",
            describe = "短信验证码-列表",
            authzCode = "sys:syssmscode:list"
    )
    @AuthzCode(authzCode = "sys:syssmscode:list")
    public R list(@Valid @RequestBody SysSmsCodePageRequest request) {
        Map page = sysSmsCodeService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("短信验证码-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_info",
            cnName = "短信验证码-信息",
            describe = "短信验证码-信息",
            authzCode = "sys:syssmscode:info"
    )
    @AuthzCode(authzCode = "sys:syssmscode:info")
    public R info(@Valid @RequestBody SysSmsCodeInfoRequest request) {
        SysSmsCodeEntity sysSmsCode = sysSmsCodeService.getById(request.getId());

        return R.ok().put("data", sysSmsCode);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("短信验证码-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_save",
            cnName = "短信验证码-保存",
            describe = "短信验证码-保存",
            authzCode = "sys:syssmscode:save"
    )
    @AuthzCode(authzCode = "sys:syssmscode:save")
    public R save(@Valid @RequestBody SysSmsCodeSaveRequest request) {

        SysSmsCodeEntity entity = new SysSmsCodeEntity();

        entity.setCodeType(request.getCodeType());
        entity.setCodeMobile(request.getCodeMobile());
        entity.setCodeCode(request.getCodeCode());
        entity.setCodeState(request.getCodeState());
        entity.setCodeText(request.getCodeText());
        entity.setCodeCreateTime(request.getCodeCreateTime());
        entity.setCodeExpirationTime(request.getCodeExpirationTime());

        sysSmsCodeService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("短信验证码-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_update",
            cnName = "短信验证码-修改",
            describe = "短信验证码-修改",
            authzCode = "sys:syssmscode:update"
    )
    @AuthzCode(authzCode = "sys:syssmscode:update")
    public R update(@Valid @RequestBody SysSmsCodeUpdateRequest request) {
        SysSmsCodeEntity entity = new SysSmsCodeEntity();



        entity.setId(request.getId());
        entity.setCodeType(request.getCodeType());
        entity.setCodeMobile(request.getCodeMobile());
        entity.setCodeCode(request.getCodeCode());
        entity.setCodeState(request.getCodeState());
        entity.setCodeText(request.getCodeText());
        entity.setCodeCreateTime(request.getCodeCreateTime());
        entity.setCodeExpirationTime(request.getCodeExpirationTime());

        sysSmsCodeService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("短信验证码-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_delete",
            cnName = "短信验证码-删除",
            describe = "短信验证码-删除",
            authzCode = "sys:syssmscode:delete"
    )
    @AuthzCode(authzCode = "sys:syssmscode:delete")
    public R delete(@Valid @RequestBody SysSmsCodeDelRequest request) {
        sysSmsCodeService.removeById(request.getId());
        return R.ok();
    }

    /**
     * 发送
     */
    @Login
    @SysLog("短信验证码-发送")
    @PostMapping("send")
    @ApiOperation("短信验证码-发送")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_send",
            cnName = "短信验证码-发送",
            describe = "短信验证码-发送",
            authzCode = "sys:syssmscode:send"
    )
    @AuthzCode(authzCode = "sys:syssmscode:send")
    public R send(@Valid @RequestBody SysSmsCodeSendRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            sysSmsCodeService.sendRedis(request);
            return R.ok();
        } catch (Exception e) {
            log.debug("异常：" + e.getMessage());
            return R.error(e.getMessage());
        }

    }

    /**
     * 发送Mail
     */
    @Login
    @SysLog("短信验证码-发送Mail")
    @PostMapping("sendMail")
    @ApiOperation("发送Mail")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_sms_code",
            authoritiesCnName = "短信验证码权限",
            name = "sys_syssmscode_sendMail",
            cnName = "短信验证码-发送Mail",
            describe = "短信验证码-发送Mail",
            authzCode = "sys:syssmscode:sendMail"
    )
    @AuthzCode(authzCode = "sys:syssmscode:sendMail")
    public R sendMail(@Valid @RequestBody SysSmsCodeSendMailRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            sysSmsCodeService.sendMail(request);
            return R.ok();
        } catch (Exception e) {
            log.debug("异常：" + e.getMessage());
            return R.error(e.getMessage());
        }

    }


}
