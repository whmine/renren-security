package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信验证码
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:12:24
 */
@Data
@TableName("sys_sms_code")
public class SysSmsCodeEntity   extends BaseEntity {
	private static final long serialVersionUID = 1L;

		/**
	 * 短信ID
	 */
			@TableId(value = "ID", type = IdType.UUID)
			private String id;
			/**
	 * 验证码类型1:注册 2:忘记密码 3修改登陆密码 4.修改支付密码
	 */
				private Integer codeType;
			/**
	 * 手机号
	 */
				private String codeMobile;
			/**
	 * 短信验证码
	 */
				private String codeCode;
			/**
	 * 验证码是否有效
	 */
				private String codeState;
			/**
	 * 验证码内容
	 */
				private String codeText;
			/**
	 * 验证码生成时间
	 */
				private Date codeCreateTime;
			/**
	 * 验证码过期时间
	 */
				private Date codeExpirationTime;
																												
}
