package io.renren.modules.sys.dao;

import io.renren.common.exception.RRException;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统用户角色关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */

@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /***
     *系统用户角色关系-待选列表(右)
     * @param map
     * @return
     * @throws RRException
     */
    List<SysRoleEntity> rightList(Map map) throws RRException;

    /***
     * 系统用户角色关系-已选列表(左)
     * @param map
     * @return
     * @throws RRException
     */
    List<SysRoleEntity> leftList(Map map) throws RRException;

}
