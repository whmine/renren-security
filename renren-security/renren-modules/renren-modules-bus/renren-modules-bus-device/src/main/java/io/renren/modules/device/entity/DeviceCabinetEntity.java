package io.renren.modules.device.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 机柜
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Data
@TableName("device_cabinet")
public class DeviceCabinetEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 机柜编码
	 */
				private String cabinetSn;
			/**
	 * 二维码编号
	 */
				private String qrCode;
			/**
	 * 设备型号
	 */
				private String deviceType;
			/**
	 * 充电槽数量
	 */
				private String deviceNum;
			/**
	 * 所属代理商
	 */
				private String agent;
			/**
	 * 所属业务员
	 */
				private String bus;
			/**
	 * 所属商户
	 */
				private String pos;
			/**
	 * 是否可以租借
	 */
				private String lease;
			/**
	 * 设备状态
	 */
				private String state;
																												
}
