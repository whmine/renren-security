package io.renren.modules.oauth.dao;

import io.renren.modules.oauth.entity.OauthTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 授权TOKEN
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 08:49:27
 */

@Mapper
public interface OauthTokenDao extends BaseMapper<OauthTokenEntity> {

}
