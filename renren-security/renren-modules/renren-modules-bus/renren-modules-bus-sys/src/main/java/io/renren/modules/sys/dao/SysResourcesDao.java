package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysResourcesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统资源
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */

@Mapper
public interface SysResourcesDao extends BaseMapper<SysResourcesEntity> {

    /***
     * 根据用户ID查询所有资源数据
     * @param map
     * @return
     */
    List<Map> selectByUserId(Map map);

}
