package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Data
@TableName("sys_dict_data")
public class SysDictDataEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * id
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 名称
	 */
				private String name;
			/**
	 * 中文名称
	 */
				private String cnName;
			/**
	 * 字典标签
	 */
				private String dictLabel;
			/**
	 * 字典键值
	 */
				private String dictValue;
			/**
	 * 字典类型
	 */
				private String dictType;
																												
}
