package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.framework.utils.Producer;
import io.renren.modules.sys.dao.SysResourcesDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dao.SysUserRoleDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.request.SysOauthCodePushRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysOauthCodeDao;
import io.renren.modules.sys.entity.SysOauthCodeEntity;
import io.renren.modules.sys.service.SysOauthCodeService;
import io.renren.modules.sys.request.SysOauthCodePageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("sysOauthCodeService")
public class SysOauthCodeServiceImpl extends ServiceImpl<SysOauthCodeDao, SysOauthCodeEntity> implements SysOauthCodeService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysResourcesDao sysResourcesDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(SysOauthCodePageRequest request) {
        Page<SysOauthCodeEntity> page = new Page<SysOauthCodeEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysOauthCodeEntity> queryWrapper = new QueryWrapper<SysOauthCodeEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysOauthCodeEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 初次登录保存数据
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createSysOauthCode(Map entity) {
        SysOauthCodeEntity sysOauthCodeEntity = new SysOauthCodeEntity();
        sysOauthCodeEntity.setOpenId(entity.get("openId") + "");
        sysOauthCodeEntity.setSysUserId(entity.get("sysUserId") + "");
        baseMapper.insert(sysOauthCodeEntity);
        //创建系统账号
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(entity.get("sysUserId") + "");
        sysUserEntity.setName(entity.get("openId") + "");
        sysUserEntity.setCnName(entity.get("name") + "");
        sysUserEntity.setSysPasswd(DigestUtils.sha256Hex(Constants.WEIXIN_OPEN_PASSWD));
        sysUserDao.insert(sysUserEntity);

        //添加默认角色
        String roleValue = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.WEIXIN_OPEN_ROLE_ID);
        String roleId = "1";
        if (StringUtils.isBlank(roleValue)) {
            roleId = "1";
        }
        Map roleValueMap = new Gson().fromJson(roleValue, Map.class);
        roleId = roleValueMap.get("value") + "";
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setSysRoleId(roleId);
        sysUserRoleEntity.setSysUserId(sysUserEntity.getId());
        sysUserRoleDao.insert(sysUserRoleEntity);


//        Map map = new HashMap();
//        map.put("sysUserId", entity.get("sysUserId") + "");
//        List<Map> sysResourcesEntityList = sysResourcesDao.selectByUserId(map);
//        stringRedisTemplate.opsForValue().set(Constants.SYS_RESOURCES + entity.get("sysUserId") + "", new Gson().toJson(sysResourcesEntityList));
//        authorizationInfo(new Gson(), entity.get("sysUserId") + "");
    }

    /***
     *修改数据
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateSysOauthCode(Map entity) {


        //创建系统账号
        String phone = entity.get("openId") + "";

        QueryWrapper<SysUserEntity> qw = new QueryWrapper<>();
        qw.eq("NAME", phone);
        SysUserEntity sysUserEntity = sysUserDao.selectOne(qw);
        if (sysUserEntity == null) {
            sysUserEntity = new SysUserEntity();
            sysUserEntity.setName(entity.get("openId") + "");
            sysUserEntity.setCnName(entity.get("name") + "");
            sysUserEntity.setSysPasswd(DigestUtils.sha256Hex(Constants.WEIXIN_OPEN_PASSWD));
            sysUserDao.insert(sysUserEntity);
        } else {
            sysUserEntity.setName(entity.get("openId") + "");
            sysUserEntity.setCnName(entity.get("name") + "");
            sysUserEntity.setSysPasswd(DigestUtils.sha256Hex(Constants.WEIXIN_OPEN_PASSWD));
            sysUserDao.updateById(sysUserEntity);
        }


        //添加默认角色
        String roleId = entity.get("roleid") + "";
        QueryWrapper<SysUserRoleEntity> rqw = new QueryWrapper<>();
        rqw.eq("SYS_USER_ID", sysUserEntity.getId());
        rqw.eq("SYS_ROLE_ID", roleId);


        SysUserRoleEntity sysUserRoleEntity = sysUserRoleDao.selectOne(rqw);
        if (sysUserRoleEntity == null) {
            sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setSysRoleId(roleId);
            sysUserRoleEntity.setSysUserId(sysUserEntity.getId());
            sysUserRoleDao.insert(sysUserRoleEntity);
        }


        //回写客户编码
        Map callbackMap = new HashMap();
        callbackMap.put("roleId", roleId);
        callbackMap.put("customerId", sysUserRoleEntity.getId());
        callbackMap.put("phone", phone);
        if (roleId.equals("cebb923eeef39272e571f8911657ced8")) {//代理商业务员
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.agent.bus.customer"), new Gson().toJson(callbackMap));
        } else if (roleId.equals("a30ccf9275a03f9a0f8b9d1c6a6ab66c")) {//代理商管理员
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.agent.admin.customer"), new Gson().toJson(callbackMap));
        } else if (roleId.equals("6dc2c8887b8b482430566716da4502bb")) {//代理商
            producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.agent.customer"), new Gson().toJson(callbackMap));
        }

        //初始化客户信息
        entity.put("sysUserId", sysUserEntity.getId());
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer"), new Gson().toJson(entity));
    }

    /***
     * 授权CODE-发布redis
     * @param request
     */
    @Override
    public void push(SysOauthCodePushRequest request) {

    }

}
