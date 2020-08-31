package com.lpl.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器类
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    /**
     *  处理异常的业务逻辑，并携带数据和视图返回
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @param ex    当前抛出的异常对象
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler, Exception ex) {

        //创建要处理的异常对象
        CustomException exception = null;
        //如果是自定义异常类对其进行处理
        if (ex instanceof CustomException){
            exception = (CustomException) ex;
        }else {
            ex = new Exception("Exception异常！");
        }
        //返回视图和数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg", ex.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
