package io.renren.cloud.zuul.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.renren.common.constant.Constants;
import io.renren.common.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 可以在请求被路由之前调用
 */
@Slf4j
@Component
public class LogPreZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {

            //获取上下文
            RequestContext requestContext = RequestContext.getCurrentContext();
            HttpServletRequest request = requestContext.getRequest();
            Enumeration<String> headNames = request.getHeaderNames();
            while (headNames.hasMoreElements()) {
                String headName = headNames.nextElement();
                log.debug("网关拦截器Header请求参数：" + headName + "=" + request.getHeader(headName));
            }

//            ServletInputStream in = request.getInputStream();
//            String result = FileUtil.readInputStream2String(in);
//            log.debug("网关拦截器请求参数：" + result);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return null;
    }
}
