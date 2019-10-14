package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * ID
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
	 * 图标
	 */
				private String icon;
			/**
	 * 授权码
	 */
				private String perms;
			/**
	 * 菜单路径
	 */
				private String pathUrl;
			/**
	 * 是否显示
	 */
				private String isShow;
			/**
	 * 父节点
	 */
				private String parentId;
			/**
	 * 菜单级数
	 */
				private String menuNum;
																												
}
