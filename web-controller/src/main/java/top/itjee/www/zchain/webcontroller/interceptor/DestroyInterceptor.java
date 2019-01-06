package top.itjee.www.zchain.webcontroller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import top.itjee.www.zchain.utils.ThreadLocalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DestroyInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.empty();
    }
}
