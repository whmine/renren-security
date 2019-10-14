package io.renren.modules.sys.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
    import java.io.Serializable;
import java.util.Date;

/**
 * 系统公告表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
@Data
@Api("系统公告表-分页查询")
public class SysNoticePageRequest extends PageRequest {


}
