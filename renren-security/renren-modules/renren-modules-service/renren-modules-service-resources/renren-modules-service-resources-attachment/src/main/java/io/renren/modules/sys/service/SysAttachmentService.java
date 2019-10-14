package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysAttachmentEntity;
import io.renren.modules.sys.request.SysAttachmentDelRequest;
import io.renren.modules.sys.request.SysAttachmentPageRequest;
import io.renren.modules.sys.request.SysAttachmentPlayRequest;
import io.renren.modules.sys.request.SysAttachmentUploadImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 系统文件管理
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 14:34:35
 */
public interface SysAttachmentService extends IService<SysAttachmentEntity> {

    Map queryPage(SysAttachmentPageRequest request);

    /***
     * 图片上传
     * @param file
     * @return
     * @throws Exception
     */
    SysAttachmentEntity uploadImage(SysAttachmentUploadImageRequest request, MultipartFile file) throws RRException;


    void remove(SysAttachmentDelRequest request) throws RRException;

    /***
     * 视频播放
     * @param request
     * @return
     * @throws RRException
     */
    Map play(SysAttachmentPlayRequest request) throws RRException;
}

