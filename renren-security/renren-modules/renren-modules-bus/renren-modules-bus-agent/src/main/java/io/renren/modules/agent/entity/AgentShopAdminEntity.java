package io.renren.modules.agent.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理商管理员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Data
@TableName("agent_shop_admin")
public class AgentShopAdminEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 管理员编码
	 */
				private String adminSn;
			/**
	 * 管理员名称
	 */
				private String adminName;
			/**
	 * 管理员手机
	 */
				private String adminPhone;
			/**
	 * 所属代理商
	 */
				private String adminParent;
			/**
	 * 所属业务员
	 */
				private String adminBusParent;
			/**
	 * 佣金百分比
	 */
				private String adminRatio;
			/**
	 * 可打开所属机柜
	 */
				private String adminOpen;
			/**
	 * 客户编码
	 */
				private String customerId;
																												
}
