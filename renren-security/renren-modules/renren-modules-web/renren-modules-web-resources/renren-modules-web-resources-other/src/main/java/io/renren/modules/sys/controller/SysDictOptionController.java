package io.renren.modules.sys.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.renren.modules.sys.entity.SysDictOptionEntity;
import io.renren.modules.sys.service.SysDictOptionService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 数据字典
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:49:21
 */
@Slf4j
@Api(tags = "数据字典")
@RestController
@RequestMapping("/sys/sysdictoption")
public class SysDictOptionController extends AbstractController {
    @Autowired
    private SysDictOptionService sysDictOptionService;

    /**
     * 列表
     */
    @Login
    @SysLog("数据字典-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_option",
            authoritiesCnName = "数据字典权限",
            name = "sys_sysdictoption_list",
            cnName = "数据字典-列表",
            describe = "数据字典-列表",
            authzCode = "sys:sysdictoption:list"
    )
    @AuthzCode(authzCode = "sys:sysdictoption:list")
    public R list(@Valid @RequestBody SysDictOptionPageRequest request) {
        Map page = sysDictOptionService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 类型列表
     */
    @Login
    @SysLog("数据字典-类型列表")
    @PostMapping("typeList")
    @ApiOperation("类型列表(前/后)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_option",
            authoritiesCnName = "数据字典权限",
            name = "sys_sysdictoption_typeList",
            cnName = "数据字典-类型列表",
            describe = "数据字典-类型列表",
            authzCode = "sys:sysdictoption:typeList"
    )
    @AuthzCode(authzCode = "sys:sysdictoption:typeList")
    public R typeList(@Valid @RequestBody QueryDictDataRequest request) {
        return R.ok().put("data", sysDictOptionService.queryDictData(request));
    }


    /**
     * 信息
     */
    @Login
    @SysLog("数据字典-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_option",
            authoritiesCnName = "数据字典权限",
            name = "sys_sysdictoption_info",
            cnName = "数据字典-信息",
            describe = "数据字典-信息",
            authzCode = "sys:sysdictoption:info"
    )
    @AuthzCode(authzCode = "sys:sysdictoption:info")
    public R info(@Valid @RequestBody SysDictOptionInfoRequest request) {
        SysDictOptionEntity sysDictOption = sysDictOptionService.getById(request.getId());

        return R.ok().put("data", sysDictOption);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("数据字典-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_option",
            authoritiesCnName = "数据字典权限",
            name = "sys_sysdictoption_save",
            cnName = "数据字典-保存",
            describe = "数据字典-保存",
            authzCode = "sys:sysdictoption:save"
    )
    @AuthzCode(authzCode = "sys:sysdictoption:save")
    public R save(@Valid @RequestBody SysDictOptionSaveRequest request) {

        SysDictOptionEntity entity = new SysDictOptionEntity();

        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        entity.setType(request.getType());
        entity.setFlagDefault(request.getFlagDefault());
        entity.setValue(request.getValue());
        entity.setMappingValue(request.getMappingValue());

        sysDictOptionService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("数据字典-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_option",
            authoritiesCnName = "数据字典权限",
            name = "sys_sysdictoption_update",
            cnName = "数据字典-修改",
            describe = "数据字典-修改",
            authzCode = "sys:sysdictoption:update"
    )
    @AuthzCode(authzCode = "sys:sysdictoption:update")
    public R update(@Valid @RequestBody SysDictOptionUpdateRequest request) {
        SysDictOptionEntity entity = new SysDictOptionEntity();


        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        entity.setType(request.getType());
        entity.setFlagDefault(request.getFlagDefault());
        entity.setValue(request.getValue());
        entity.setMappingValue(request.getMappingValue());

        sysDictOptionService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("数据字典-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_option",
            authoritiesCnName = "数据字典权限",
            name = "sys_sysdictoption_delete",
            cnName = "数据字典-删除",
            describe = "数据字典-删除",
            authzCode = "sys:sysdictoption:delete"
    )
    @AuthzCode(authzCode = "sys:sysdictoption:delete")
    public R delete(@Valid @RequestBody SysDictOptionDelRequest request) {
        sysDictOptionService.removeById(request.getId());
        return R.ok();
    }

}
