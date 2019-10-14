package io.renren.modules.sys.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * 系统文件管理
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 14:34:35
 */
@Data
@Api("系统文件管理-上传图片")
public class SysAttachmentUploadImageRequest extends BaseRequest {
}
