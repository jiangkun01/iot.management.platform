package co.fy.core.server.interceptor;

import co.fy.core.server.custom.annotation.PageAnnotated;
import co.fy.core.server.utils.Result;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joker on 9/7/17.
 */
public class PageInterceptor implements HandlerInterceptor {


    private boolean isSupported(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.getMethod().getReturnType().isAssignableFrom( Result.class)) {
                PageAnnotated pager = method.getMethodAnnotation(PageAnnotated.class);
                System.out.print(pager);
                if (pager != null) {
                    return true;
                }
            }
        }

        return false;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (this.isSupported(handler)) {
            HandlerMethod method = (HandlerMethod) handler;
            PageAnnotated pager = method.getMethodAnnotation(PageAnnotated.class);

            // 从请求中获取 page 参数
            String pageString = request.getParameter("pageNum");
            if (Strings.isNullOrEmpty ( pageString )) {
                pageString = "1";
            }
            // 从请求中读取每页的结果数
            String pageSizeString = request.getParameter("pageSize");
            if (Strings.isNullOrEmpty(pageSizeString)) {
                pageSizeString = String.valueOf(pager.pageSize ());
            }

            // 将两个值转为 int
            int page = Integer.parseInt(pageString);
            int pageSize = Integer.parseInt(pageSizeString);

            // 限制每页结果数的最大最小值
            if (pageSize > pager.pageSize ()) {
                pageSize = pager.pageSize ();
            }
            PageHelper.startPage(page, pageSize);
        }else{
            System.out.print ( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
