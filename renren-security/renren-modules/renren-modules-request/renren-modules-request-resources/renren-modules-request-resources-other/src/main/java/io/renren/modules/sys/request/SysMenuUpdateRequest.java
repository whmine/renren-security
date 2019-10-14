package io.renren.modules.sys.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api("系统菜单-修改")
public class SysMenuUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 名称
             */
                    @ApiModelProperty(value = "名称")
    private String name;
                                /**
             * 中文名称
             */
                    @ApiModelProperty(value = "中文名称")
    private String cnName;
                                /**
             * 图标
             */
                    @ApiModelProperty(value = "图标")
    private String icon;
                                /**
             * 授权码
             */
                    @ApiModelProperty(value = "授权码")
    private String perms;
                                /**
             * 菜单路径
             */
                    @ApiModelProperty(value = "菜单路径")
    private String pathUrl;
                                /**
             * 是否显示
             */
                    @ApiModelProperty(value = "是否显示")
    private String isShow;
                                /**
             * 父节点
             */
                    @ApiModelProperty(value = "父节点")
    private String parentId;
                                /**
             * 菜单级数
             */
                    @ApiModelProperty(value = "菜单级数")
    private String menuNum;
                                                                                                                                                                                                
}
