package io.renren.modules.sys.controller;

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

import io.renren.modules.sys.request.SysDictDataPageRequest;
import io.renren.modules.sys.request.SysDictDataInfoRequest;
import io.renren.modules.sys.request.SysDictDataSaveRequest;
import io.renren.modules.sys.request.SysDictDataUpdateRequest;
import io.renren.modules.sys.request.SysDictDataDelRequest;
import io.renren.modules.sys.entity.SysDictDataEntity;
import io.renren.modules.sys.service.SysDictDataService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 数据字典类型
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:49:21
 */
@Slf4j
@Api(tags = "数据字典类型")
@RestController
@RequestMapping("/sys/sysdictdata")
public class SysDictDataController extends AbstractController {
    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 列表
     */
    @Login
    @SysLog("数据字典类型-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_data",
            authoritiesCnName = "数据字典类型权限",
            name = "sys_sysdictdata_list",
            cnName = "数据字典类型-列表",
            describe = "数据字典类型-列表",
            authzCode = "sys:sysdictdata:list"
    )
    @AuthzCode(authzCode = "sys:sysdictdata:list")
    public R list(@Valid @RequestBody SysDictDataPageRequest request) {
        Map page = sysDictDataService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("数据字典类型-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_data",
            authoritiesCnName = "数据字典类型权限",
            name = "sys_sysdictdata_info",
            cnName = "数据字典类型-信息",
            describe = "数据字典类型-信息",
            authzCode = "sys:sysdictdata:info"
    )
    @AuthzCode(authzCode = "sys:sysdictdata:info")
    public R info(@Valid @RequestBody SysDictDataInfoRequest request) {
            SysDictDataEntity sysDictData = sysDictDataService.getById(request.getId());

        return R.ok().put("data", sysDictData);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("数据字典类型-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_data",
            authoritiesCnName = "数据字典类型权限",
            name = "sys_sysdictdata_save",
            cnName = "数据字典类型-保存",
            describe = "数据字典类型-保存",
            authzCode = "sys:sysdictdata:save"
    )
    @AuthzCode(authzCode = "sys:sysdictdata:save")
    public R save(@Valid @RequestBody SysDictDataSaveRequest request) {

            SysDictDataEntity entity = new SysDictDataEntity();

                                                        entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setDictLabel(request.getDictLabel());
                                                entity.setDictValue(request.getDictValue());
                                                entity.setDictType(request.getDictType());

            sysDictDataService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("数据字典类型-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_data",
            authoritiesCnName = "数据字典类型权限",
            name = "sys_sysdictdata_update",
            cnName = "数据字典类型-修改",
            describe = "数据字典类型-修改",
            authzCode = "sys:sysdictdata:update"
    )
    @AuthzCode(authzCode = "sys:sysdictdata:update")
    public R update(@Valid @RequestBody SysDictDataUpdateRequest request) {
            SysDictDataEntity entity = new SysDictDataEntity();



                                    entity.setId(request.getId());
                                                entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setDictLabel(request.getDictLabel());
                                                entity.setDictValue(request.getDictValue());
                                                entity.setDictType(request.getDictType());

            sysDictDataService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("数据字典类型-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_dict_data",
            authoritiesCnName = "数据字典类型权限",
            name = "sys_sysdictdata_delete",
            cnName = "数据字典类型-删除",
            describe = "数据字典类型-删除",
            authzCode = "sys:sysdictdata:delete"
    )
    @AuthzCode(authzCode = "sys:sysdictdata:delete")
    public R delete(@Valid @RequestBody SysDictDataDelRequest request) {
            sysDictDataService.removeById(request.getId());
        return R.ok();
    }

}
