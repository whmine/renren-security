package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysAuthoritiesResourcesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysResourcesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统权限资源关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */

@Mapper
public interface SysAuthoritiesResourcesDao extends BaseMapper<SysAuthoritiesResourcesEntity> {

    List<SysResourcesEntity> rightList(Map map);

    List<SysResourcesEntity> leftList(Map map);

}
