package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统服务依赖
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:45
 */
@Data
@TableName("sys_service_dependency")
public class SysServiceDependencyEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 服务ID
	 */
				private String sysServiceId;
			/**
	 * 依赖服务ID
	 */
				private String sysServiceDependencyId;
																												
}
