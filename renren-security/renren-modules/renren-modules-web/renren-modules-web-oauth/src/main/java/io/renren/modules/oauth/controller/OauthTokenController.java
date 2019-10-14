package io.renren.modules.oauth.controller;

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

import io.renren.modules.oauth.request.OauthTokenPageRequest;
import io.renren.modules.oauth.request.OauthTokenInfoRequest;
import io.renren.modules.oauth.request.OauthTokenSaveRequest;
import io.renren.modules.oauth.request.OauthTokenUpdateRequest;
import io.renren.modules.oauth.request.OauthTokenDelRequest;
import io.renren.modules.oauth.entity.OauthTokenEntity;
import io.renren.modules.oauth.service.OauthTokenService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 授权TOKEN
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:28:01
 */
@Slf4j
@Api(tags = "授权TOKEN")
@RestController
@RequestMapping("/oauth/oauthtoken")
public class OauthTokenController extends AbstractController {
    @Autowired
    private OauthTokenService oauthTokenService;

    /**
     * 列表
     */
    @Login
    @SysLog("授权TOKEN-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_token",
            authoritiesCnName = "授权TOKEN权限",
            name = "oauth_oauthtoken_list",
            cnName = "授权TOKEN-列表",
            describe = "授权TOKEN-列表",
            authzCode = "oauth:oauthtoken:list"
    )
    @AuthzCode(authzCode = "oauth:oauthtoken:list")
    public R list(@Valid @RequestBody OauthTokenPageRequest request) {
        Map page = oauthTokenService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("授权TOKEN-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_token",
            authoritiesCnName = "授权TOKEN权限",
            name = "oauth_oauthtoken_info",
            cnName = "授权TOKEN-信息",
            describe = "授权TOKEN-信息",
            authzCode = "oauth:oauthtoken:info"
    )
    @AuthzCode(authzCode = "oauth:oauthtoken:info")
    public R info(@Valid @RequestBody OauthTokenInfoRequest request) {
        OauthTokenEntity oauthToken = oauthTokenService.getById(request.getId());

        return R.ok().put("data", oauthToken);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("授权TOKEN-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_token",
            authoritiesCnName = "授权TOKEN权限",
            name = "oauth_oauthtoken_save",
            cnName = "授权TOKEN-保存",
            describe = "授权TOKEN-保存",
            authzCode = "oauth:oauthtoken:save"
    )
    @AuthzCode(authzCode = "oauth:oauthtoken:save")
    public R save(@Valid @RequestBody OauthTokenSaveRequest request) {

        OauthTokenEntity entity = new OauthTokenEntity();

        entity.setToken(request.getToken());
        entity.setSysUserId(request.getSysUserId());
        entity.setExpireTime(request.getExpireTime());
        entity.setUpdateTime(request.getUpdateTime());

        oauthTokenService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("授权TOKEN-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_token",
            authoritiesCnName = "授权TOKEN权限",
            name = "oauth_oauthtoken_update",
            cnName = "授权TOKEN-修改",
            describe = "授权TOKEN-修改",
            authzCode = "oauth:oauthtoken:update"
    )
    @AuthzCode(authzCode = "oauth:oauthtoken:update")
    public R update(@Valid @RequestBody OauthTokenUpdateRequest request) {
        OauthTokenEntity entity = new OauthTokenEntity();


        entity.setId(request.getId());
        entity.setToken(request.getToken());
        entity.setSysUserId(request.getSysUserId());
        entity.setExpireTime(request.getExpireTime());
        entity.setUpdateTime(request.getUpdateTime());

        oauthTokenService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("授权TOKEN-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "oauth_token",
            authoritiesCnName = "授权TOKEN权限",
            name = "oauth_oauthtoken_delete",
            cnName = "授权TOKEN-删除",
            describe = "授权TOKEN-删除",
            authzCode = "oauth:oauthtoken:delete"
    )
    @AuthzCode(authzCode = "oauth:oauthtoken:delete")
    public R delete(@Valid @RequestBody OauthTokenDelRequest request) {
        oauthTokenService.removeById(request.getId());
        return R.ok();
    }

}
