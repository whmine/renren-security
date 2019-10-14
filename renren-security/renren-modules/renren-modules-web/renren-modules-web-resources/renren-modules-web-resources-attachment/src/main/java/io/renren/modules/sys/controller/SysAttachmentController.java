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

import io.renren.modules.sys.entity.SysAttachmentEntity;
import io.renren.modules.sys.service.SysAttachmentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 系统文件管理
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 11:55:41
 */
@Slf4j
@Api(tags = "系统文件管理" )
@RestController
@RequestMapping("/sys/sysattachment" )
public class SysAttachmentController extends AbstractController {
    @Autowired
    private SysAttachmentService sysAttachmentService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统文件管理-列表" )
    @PostMapping("list" )
    @ApiOperation("列表" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_list" ,
            cnName = "系统文件管理-列表" ,
            describe = "系统文件管理-列表" ,
            authzCode = "sys:sysattachment:list"
    )
    @AuthzCode(authzCode = "sys:sysattachment:list" )
    public R list(@Valid @RequestBody SysAttachmentPageRequest request) {
        Map page = sysAttachmentService.queryPage(request);
        return R.ok().put("data" , page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统文件管理-信息" )
    @PostMapping("info" )
    @ApiOperation("信息" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_info" ,
            cnName = "系统文件管理-信息" ,
            describe = "系统文件管理-信息" ,
            authzCode = "sys:sysattachment:info"
    )
    @AuthzCode(authzCode = "sys:sysattachment:info" )
    public R info(@Valid @RequestBody SysAttachmentInfoRequest request) {
        SysAttachmentEntity sysAttachment = sysAttachmentService.getById(request.getId());

        return R.ok().put("data" , sysAttachment);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统文件管理-保存" )
    @PostMapping("save" )
    @ApiOperation("保存" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_save" ,
            cnName = "系统文件管理-保存" ,
            describe = "系统文件管理-保存" ,
            authzCode = "sys:sysattachment:save"
    )
    @AuthzCode(authzCode = "sys:sysattachment:save" )
    public R save(@Valid @RequestBody SysAttachmentSaveRequest request) {

        SysAttachmentEntity entity = new SysAttachmentEntity();

        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setSysType(request.getSysType());
        entity.setUrlPath(request.getText());
        entity.setLength(request.getLength());
        entity.setSysUserId(request.getUserId());
        sysAttachmentService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统文件管理-修改" )
    @PostMapping("update" )
    @ApiOperation("修改" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_update" ,
            cnName = "系统文件管理-修改" ,
            describe = "系统文件管理-修改" ,
            authzCode = "sys:sysattachment:update"
    )
    @AuthzCode(authzCode = "sys:sysattachment:update" )
    public R update(@Valid @RequestBody SysAttachmentUpdateRequest request) {
        SysAttachmentEntity entity = new SysAttachmentEntity();


        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setSysType(request.getSysType());
        entity.setUrlPath(request.getText());
        entity.setLength(request.getLength());

        sysAttachmentService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统文件管理-删除" )
    @PostMapping("delete" )
    @ApiOperation("删除" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_delete" ,
            cnName = "系统文件管理-删除" ,
            describe = "系统文件管理-删除" ,
            authzCode = "sys:sysattachment:delete"
    )
    @AuthzCode(authzCode = "sys:sysattachment:delete" )
    public R delete(@Valid @RequestBody SysAttachmentDelRequest request) {
        sysAttachmentService.remove(request);
        return R.ok();
    }

    @Login
    @SysLog("系统文件管理-上传图片" )
    @PostMapping(value = "uploadImage" , consumes = {"multipart/form-data"})
    @ApiOperation("上传图片" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_uploadImage" ,
            cnName = "系统文件管理-上传图片" ,
            describe = "系统文件管理-上传图片" ,
            authzCode = "sys:sysattachment:uploadImage"
    )
    @AuthzCode(authzCode = "sys:sysattachment:uploadImage" )
    public R uploadImage(SysAttachmentUploadImageRequest request, @RequestParam("file" ) MultipartFile file) {

        try {
            return R.ok().put("data" , sysAttachmentService.uploadImage(request, file));
        } catch (Exception e) {
            log.debug("异常：" + e.getMessage());
            return R.error("异常：" + e.getMessage());
        }

    }

    @Login
    @SysLog("系统文件管理-视频播放" )
    @PostMapping(value = "play" )
    @ApiOperation("视频播放" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM" ,
            authoritiesName = "sys_attachment" ,
            authoritiesCnName = "系统文件管理权限" ,
            name = "sys_sysattachment_play" ,
            cnName = "系统文件管理-视频播放" ,
            describe = "系统文件管理-视频播放" ,
            authzCode = "sys:sysattachment:play"
    )
    @AuthzCode(authzCode = "sys:sysattachment:play" )
    public R play(@Valid @RequestBody SysAttachmentPlayRequest request) {

        try {
            return R.ok().put("data" , sysAttachmentService.play(request));
        } catch (Exception e) {
            log.debug("异常：" + e.getMessage());
            return R.error("异常：" + e.getMessage());
        }

    }

}
