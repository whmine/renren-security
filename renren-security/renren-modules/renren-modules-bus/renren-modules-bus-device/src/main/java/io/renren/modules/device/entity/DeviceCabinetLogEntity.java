package io.renren.modules.device.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 机柜租借
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Data
@TableName("device_cabinet_log")
public class DeviceCabinetLogEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 机柜编码
	 */
				private String cabinetId;
			/**
	 * 二维码编号
	 */
				private String qrCode;
			/**
	 * 开始时间
	 */
				private String startTime;
			/**
	 * 结束时间
	 */
				private String endTime;
																												
}
