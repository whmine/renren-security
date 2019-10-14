package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.renren.modules.app.request.OauthAppDetailsPageRequest;
import io.renren.modules.app.request.OauthAppDetailsInfoRequest;
import io.renren.modules.app.request.OauthAppDetailsSaveRequest;
import io.renren.modules.app.request.OauthAppDetailsUpdateRequest;
import io.renren.modules.app.request.OauthAppDetailsDelRequest;
import io.renren.modules.app.request.OauthAppDetailsPushRequest;
import io.renren.modules.app.entity.OauthAppDetailsEntity;
import io.renren.modules.app.service.OauthAppDetailsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-16 10:49:56
 */
@Slf4j
@Api(tags = "系统信息")
@RestController
@RequestMapping("/app/oauthappdetails")
public class OauthAppDetailsController extends AbstractController {
    @Autowired
    private OauthAppDetailsService oauthAppDetailsService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统信息-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_app_details",
            authoritiesCnName = "系统信息权限",
            name = "app_oauthappdetails_list",
            cnName = "系统信息-列表",
            describe = "系统信息-列表",
            authzCode = "app:oauthappdetails:list"
    )
    @AuthzCode(authzCode = "app:oauthappdetails:list")
    public R list(@Valid @RequestBody OauthAppDetailsPageRequest request) {
        Map page = oauthAppDetailsService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统信息-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_app_details",
            authoritiesCnName = "系统信息权限",
            name = "app_oauthappdetails_info",
            cnName = "系统信息-信息",
            describe = "系统信息-信息",
            authzCode = "app:oauthappdetails:info"
    )
    @AuthzCode(authzCode = "app:oauthappdetails:info")
    public R info(@Valid @RequestBody OauthAppDetailsInfoRequest request) {
            OauthAppDetailsEntity oauthAppDetails = oauthAppDetailsService.getById(request.getId());

        return R.ok().put("data", oauthAppDetails);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统信息-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_app_details",
            authoritiesCnName = "系统信息权限",
            name = "app_oauthappdetails_save",
            cnName = "系统信息-保存",
            describe = "系统信息-保存",
            authzCode = "app:oauthappdetails:save"
    )
    @AuthzCode(authzCode = "app:oauthappdetails:save")
    public R save(@Valid @RequestBody OauthAppDetailsSaveRequest request) {

            OauthAppDetailsEntity entity = new OauthAppDetailsEntity();

                                                        entity.setSysUserId(request.getSysUserId());
                                                entity.setOpenId(request.getOpenId());
                                                entity.setScopes(request.getScopes());
                                                entity.setGrantTypes(request.getGrantTypes());
                                                entity.setRedirectUrl(request.getRedirectUrl());
                                                entity.setAutoApprove(request.getAutoApprove());
                                                entity.setRefreshToken(request.getRefreshToken());
                                                entity.setNoncestr(request.getNoncestr());
                                                entity.setSignature(request.getSignature());
                                                entity.setExpiresIn(request.getExpiresIn());
                                                entity.setOauthTime(request.getOauthTime());
                                                                                                                                                                                                        
            oauthAppDetailsService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统信息-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_app_details",
            authoritiesCnName = "系统信息权限",
            name = "app_oauthappdetails_update",
            cnName = "系统信息-修改",
            describe = "系统信息-修改",
            authzCode = "app:oauthappdetails:update"
    )
    @AuthzCode(authzCode = "app:oauthappdetails:update")
    public R update(@Valid @RequestBody OauthAppDetailsUpdateRequest request) {
            OauthAppDetailsEntity entity = new OauthAppDetailsEntity();



                                    entity.setId(request.getId());
                                                entity.setSysUserId(request.getSysUserId());
                                                entity.setOpenId(request.getOpenId());
                                                entity.setScopes(request.getScopes());
                                                entity.setGrantTypes(request.getGrantTypes());
                                                entity.setRedirectUrl(request.getRedirectUrl());
                                                entity.setAutoApprove(request.getAutoApprove());
                                                entity.setRefreshToken(request.getRefreshToken());
                                                entity.setNoncestr(request.getNoncestr());
                                                entity.setSignature(request.getSignature());
                                                entity.setExpiresIn(request.getExpiresIn());
                                                entity.setOauthTime(request.getOauthTime());
                                                                                                                                                                                                        
            oauthAppDetailsService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统信息-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_app_details",
            authoritiesCnName = "系统信息权限",
            name = "app_oauthappdetails_delete",
            cnName = "系统信息-删除",
            describe = "系统信息-删除",
            authzCode = "app:oauthappdetails:delete"
    )
    @AuthzCode(authzCode = "app:oauthappdetails:delete")
    public R delete(@Valid @RequestBody OauthAppDetailsDelRequest request) {
        oauthAppDetailsService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("系统信息-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_app_details",
            authoritiesCnName = "系统信息权限",
            name = "app_oauthappdetails_push",
            cnName = "系统信息-发布redis",
            describe = "系统信息-发布redis",
            authzCode = "app:oauthappdetails:push"
    )
    @AuthzCode(authzCode = "app:oauthappdetails:push")
    public R push(@Valid @RequestBody OauthAppDetailsPushRequest request) {
        oauthAppDetailsService.push(request);
        return R.ok();
    }

}
