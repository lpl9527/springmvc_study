package com.lpl.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器（AOP思想）
 *  拦截器与拦截器链作用：与Servlet中的过滤器（Filter）类似，对处理器进行预处理和后处理。
 *  与过滤器、过滤器链区别：
 *      1.过滤器是Servlet规范中的一部分，任何java web工程都可使用；拦截器只有springMvc框架才可使用。
 *      2.过滤器在配置了/*之后，会拦截所有的资源请求；拦截器只会拦截访问的控制器方法，不拦截静态资源请求。
 */
public class CustomInterceptor implements HandlerInterceptor {

    /**
     *  预处理方法，在Controller方法执行之前执行
     *      return true 表示放行，执行下一个拦截器，如果没有则执行Controller中的方法
     *      return false 不放行，可以只用Request或Response对象惊醒资源跳转操作
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("自定义拦截器preHandle()预处理方法执行了！！");
        //服务器内部跳转，不走拦截器
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        return true;
    }
    /**
     *  后处理方法，在控制器Controller中方法执行之后执行，在请求返回视图的请求执行之前执行。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("自定义拦截器postHandle()后处理方法执行了！！");
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
    }

    /**
     *  最终执行的方法，在返回视图之后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("自定义拦截器afterCompletion()最终处理方法执行了！！");
    }
}
