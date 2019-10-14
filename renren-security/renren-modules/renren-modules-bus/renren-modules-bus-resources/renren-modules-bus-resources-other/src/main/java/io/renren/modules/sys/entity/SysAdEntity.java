package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 广告轮播图
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
@Data
@TableName("sys_ad")
public class SysAdEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * 广告轮播图ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 广告名字
	 */
				private String adName;
			/**
	 * 链接地址
	 */
				private String adLink;
			/**
	 * 图片地址
	 */
				private String adCode;
			/**
	 * 类型
	 */
				private String type;
			/**
	 * 添加人手机号
	 */
				private String linkPhone;
			/**
	 * 开始时间
	 */
				private Date startTime;
			/**
	 * 结束时间
	 */
				private Date endTime;
			/**
	 * 点击量
	 */
				private Integer clickCount;
			/**
	 * 是否显示 0  1
	 */
				private String enabled;
																												
}
