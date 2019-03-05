package com.hblog.exception;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice//定义全局控制器异常处理
public class GlobalExceptionHandler {

    //针对性异常处理，可对每种异常针对性处理
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception e) throws Exception{
        log.error("----------捕捉到全局异常-----",e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",e);
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public R jsonErrorHandler(HttpServletRequest request, MyException e) throws Exception{
        //TODO 错误日志处理

        return R.failed(e.getMessage());
    }
}
