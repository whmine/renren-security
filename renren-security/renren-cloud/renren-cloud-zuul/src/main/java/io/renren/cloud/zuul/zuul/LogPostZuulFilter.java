package io.renren.cloud.zuul.zuul;

//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import io.renren.common.constant.Constants;
//import io.renren.common.utils.FileUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Collection;
//import java.util.Enumeration;
//import java.util.List;
//
///**
// * 可以在请求被路由之前调用
// */
//@Slf4j
//@Component
//public class LogPostZuulFilter extends ZuulFilter {
//    @Override
//    public String filterType() {
//        return "post";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        try {
//            //获取上下文
//            RequestContext requestContext = RequestContext.getCurrentContext();
//            InputStream stream = requestContext.getResponseDataStream();
//            String result = FileUtil.readInputStream2String(stream);
//            log.debug("网关拦截器请求参数：" + result);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.debug(e.getMessage());
//        }
//////        if (true) {
//////            requestContext.setSendZuulResponse(false);
//////            requestContext.setResponseBody("test");
//////            requestContext.setResponseStatusCode(401);
//////        }
////        request.setAttribute("X-Token","123456");
//
//        return null;
//    }
//}
