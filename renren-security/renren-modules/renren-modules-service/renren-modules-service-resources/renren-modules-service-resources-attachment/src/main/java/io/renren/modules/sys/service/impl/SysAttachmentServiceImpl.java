package io.renren.modules.sys.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.FileUploadUtils;
import io.renren.modules.sys.request.SysAttachmentDelRequest;
import io.renren.modules.sys.request.SysAttachmentPlayRequest;
import io.renren.modules.sys.request.SysAttachmentUploadImageRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysAttachmentDao;
import io.renren.modules.sys.entity.SysAttachmentEntity;
import io.renren.modules.sys.service.SysAttachmentService;
import io.renren.modules.sys.request.SysAttachmentPageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service("sysAttachmentService")
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentDao, SysAttachmentEntity> implements SysAttachmentService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(SysAttachmentPageRequest request) {
        Page<SysAttachmentEntity> page = new Page<SysAttachmentEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysAttachmentEntity> queryWrapper = new QueryWrapper<SysAttachmentEntity>();
        queryWrapper.eq("SYS_USER_ID", request.getUserId());
        queryWrapper.orderByDesc("SORT");
        IPage<SysAttachmentEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 图片上传
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public SysAttachmentEntity uploadImage(SysAttachmentUploadImageRequest request, MultipartFile file) throws RRException {
        try {
            String profile = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.PROFILE);
            if (StringUtils.isBlank(profile)) {
                throw new RRException("文件服务器域名路径");
            }
            Map mapProfile = new Gson().fromJson(profile, Map.class);
            if (StringUtils.isBlank(mapProfile.get("value") + "")) {
                throw new RRException("文件服务器域名路径");
            }

            String filePath = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.FILE_PATH);
            if (StringUtils.isBlank(filePath)) {
                throw new RRException("文件上传路径");
            }
            Map mapFilePath = new Gson().fromJson(filePath, Map.class);
            if (StringUtils.isBlank(mapFilePath.get("value") + "")) {
                throw new RRException("文件上传路径");
            }

            String path = FileUploadUtils.upload(mapFilePath.get("value") + "", file);

            SysAttachmentEntity entity = new SysAttachmentEntity();
            entity.setName(path);
            entity.setType(FileUploadUtils.IMAGE_JPG_EXTENSION);
            entity.setSysType("0");
//        entity.setUrlPath(mapProfile.get("value") + path);
            entity.setUrlPath(path);
            entity.setLength(file.getSize() + "");
            entity.setSysUserId(request.getUserId());
            baseMapper.insert(entity);
            return entity;
        } catch (IOException e) {
            throw new RRException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void remove(SysAttachmentDelRequest request) throws RRException {
        String filePath = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.FILE_PATH);
        if (StringUtils.isBlank(filePath)) {
            throw new RRException("文件上传路径");
        }
        Map mapFilePath = new Gson().fromJson(filePath, Map.class);
        if (StringUtils.isBlank(mapFilePath.get("value") + "")) {
            throw new RRException("文件上传路径");
        }

        SysAttachmentEntity entity = baseMapper.selectById(request.getId());
        baseMapper.deleteById(request.getId());
        FileUploadUtils.delImgUrl(mapFilePath.get("value") + entity.getUrlPath());

    }

    /***
     * 视频播放
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public Map play(SysAttachmentPlayRequest request) throws RRException {
        Map map = new HashMap();
        SysAttachmentEntity entity = baseMapper.selectById(request.getId());
        map.put("SysAttachmentEntity", entity);
        return map;
    }

}
