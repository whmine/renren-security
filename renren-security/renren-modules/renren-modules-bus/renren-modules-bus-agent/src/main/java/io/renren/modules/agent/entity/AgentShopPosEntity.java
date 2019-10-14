package io.renren.modules.agent.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理商商户
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Data
@TableName("agent_shop_pos")
public class AgentShopPosEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 商户编码
	 */
				private String posSn;
			/**
	 * 商户名称
	 */
				private String posName;
			/**
	 * 商户管理员
	 */
				private String posAdmin;
			/**
	 * 所属业务员
	 */
				private String posBus;
			/**
	 * 业务员佣金百分比
	 */
				private String posBusRatio;
			/**
	 * 商铺佣金百分比
	 */
				private String posRatio;
			/**
	 * 商铺LOGO
	 */
				private String posLogo;
			/**
	 * 商铺图片
	 */
				private String posImg;
			/**
	 * 省
	 */
				private String province;
			/**
	 * 市
	 */
				private String city;
			/**
	 * 区
	 */
				private String district;
			/**
	 * 详细地址
	 */
				private String address;
			/**
	 * 经度
	 */
				private String warp;
			/**
	 * 纬度
	 */
				private String weft;
			/**
	 * 计费方式
	 */
				private String chargingType;
			/**
	 * 计费单位
	 */
				private String chargingCompany;
			/**
	 * 计费标准
	 */
				private String chargingStandard;
			/**
	 * 免费时长
	 */
				private String freeTime;
			/**
	 * 每日最高消费
	 */
				private String consume;
			/**
	 * 密码线
	 */
				private String cipherLine;
			/**
	 * 店铺电话
	 */
				private String shopPhone;
			/**
	 * 营业时间开始
	 */
				private Date startTime;
			/**
	 * 营业时间结束
	 */
				private Date endTime;
			/**
	 * 人均消费
	 */
				private String money;
																												
}
