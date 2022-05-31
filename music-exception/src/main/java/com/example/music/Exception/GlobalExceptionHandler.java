package com.example.music.Exception;

import com.example.music.Entity.CustomzieException;
import com.example.music.Entity.Pojo.ResultObjectModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 自定义全局异常处理类
 */
@Slf4j
@ControllerAdvice("com.example.music.Controller")
public class GlobalExceptionHandler {
    /**
     * 处理参数校验异常
     * @param e
     * @return ResponseData
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultObjectModel bindExceptionHandler(BindException e) {
        log.error("参数错误bind：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResultObjectModel.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return ResponseData
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultObjectModel methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数错误not valid：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResultObjectModel.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResultObjectModel constraintViolationExceptionHandler(ConstraintViolationException e) {
        String returnMessage = null;
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            returnMessage = violation.getMessage();
        }
        return ResultObjectModel.fail(returnMessage);

    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomzieException.class)
    @ResponseBody
    public ResultObjectModel customiseExceptionHandler(CustomzieException e) {
        return ResultObjectModel.fail(e.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    public ResultObjectModel handleBusinessException(InternalAuthenticationServiceException e) {
        return ResultObjectModel.fail(e.getMessage());
    }

}
