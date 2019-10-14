package io.renren.modules.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.oauth.dao.OauthTokenDao;
import io.renren.modules.oauth.entity.OauthTokenEntity;
import io.renren.modules.oauth.service.OauthTokenService;
import io.renren.modules.oauth.request.OauthTokenPageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("oauthTokenService")
public class OauthTokenServiceImpl extends ServiceImpl<OauthTokenDao, OauthTokenEntity> implements OauthTokenService {

    @Override
    public Map queryPage(OauthTokenPageRequest request) {
        Page<OauthTokenEntity> page = new Page<OauthTokenEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<OauthTokenEntity> queryWrapper = new QueryWrapper<OauthTokenEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<OauthTokenEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 更新token
     * @param oauthTokenEntity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createToken(OauthTokenEntity oauthTokenEntity) {

        QueryWrapper<OauthTokenEntity> eWrapper = new QueryWrapper<>();
        eWrapper.eq("SYS_USER_ID", oauthTokenEntity.getSysUserId());
        OauthTokenEntity oauthTokenEntityTemp = baseMapper.selectOne(eWrapper);

        if (oauthTokenEntityTemp == null) {
            oauthTokenEntityTemp = oauthTokenEntity;
            baseMapper.insert(oauthTokenEntityTemp);
        } else {
            oauthTokenEntityTemp.setToken(oauthTokenEntity.getToken());
            oauthTokenEntityTemp.setSysUserId(oauthTokenEntity.getSysUserId());
            oauthTokenEntityTemp.setExpireTime(oauthTokenEntity.getExpireTime());
            oauthTokenEntityTemp.setUpdateTime(oauthTokenEntity.getUpdateTime());
            baseMapper.updateById(oauthTokenEntityTemp);
        }


    }

    /***
     * 注销token
     * @param oauthTokenEntity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void outToken(OauthTokenEntity oauthTokenEntity) {
        QueryWrapper<OauthTokenEntity> eWrapper = new QueryWrapper<>();
        eWrapper.eq("SYS_USER_ID", oauthTokenEntity.getSysUserId());
        OauthTokenEntity oauthTokenEntityTemp = baseMapper.selectOne(eWrapper);
        if (oauthTokenEntity != null) {
            oauthTokenEntityTemp.setExpireTime(new Date());
            baseMapper.updateById(oauthTokenEntityTemp);
        }
    }

    /***
     * 缓存
     * @return
     */
    @Override
    public List<OauthTokenEntity> oauthTokenCacheManager() {
        QueryWrapper<OauthTokenEntity> eWrapper = new QueryWrapper<>();
        eWrapper.ge("EXPIRE_TIME", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        List<OauthTokenEntity> oauthTokenEntityList = baseMapper.selectList(eWrapper);
        return oauthTokenEntityList;
    }

}
