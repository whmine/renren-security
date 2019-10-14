package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysNoticeEntity;
import io.renren.modules.sys.request.SysNoticePageRequest;
import java.util.*;

/**
 * 系统公告表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
public interface SysNoticeService extends IService<SysNoticeEntity> {

    Map queryPage(SysNoticePageRequest request);
}

