package com.example.log_and_proof_back.exception;

import com.example.log_and_proof_back.model.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
    // 捕获全局的异常 自己写的
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();

        return Result.error(0,"这一步操作失败,请联系管理员",null);
    }
    */


    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Except: ", e);
        return Result.error("系统异常"); // 系统异常
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Object handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException Except: ", e);
        return Result.error("参数不变能为空");
    }

    @ExceptionHandler(MissingRequestValueException.class)
    @ResponseBody
    public Object handleMissingRequestValueExceptionn(MissingRequestValueException e) {
        log.error("MissingRequestValueException Except: ", e);
        return Result.error("参数不能为空");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object handleNullPointerException(NullPointerException e) {
        log.error("NullPointerException Except: ", e);
        return Result.error("参数不能为空");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseBody
    public Object handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        log.error("MissingServletRequestPartException Except: ", e);
        return Result.error("参数错误");
    }

    /*
    // 这里需要其他的类
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Object handleException(SystemException e) {
        log.error("SystemException Except: ", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());
    }
*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(BindException e) {
        log.error("BindException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    private Result handleBindingResult(BindingResult result) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return Result.error("参数错误");
        }
        return Result.error(list.toString());
    }

}
