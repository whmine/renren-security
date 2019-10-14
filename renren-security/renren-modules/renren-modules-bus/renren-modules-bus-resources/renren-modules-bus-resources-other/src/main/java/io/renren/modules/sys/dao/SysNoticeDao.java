package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysNoticeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统公告表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */

@Mapper
public interface SysNoticeDao extends BaseMapper<SysNoticeEntity> {

}
