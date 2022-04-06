package com.example.mytest.common.config;

import com.example.mytest.common.BaseUnifyCode;
import com.example.mytest.common.UnifyResp;
import com.example.mytest.common.utils.BindingResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.annotation.Priority;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@Priority(1)
public class BasicExceptionHandler {

    /**
     * 参数验证失败
     */
    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UnifyResp<Serializable> badRequestException(ValidationException ex) {
        log.error("ValidationException :", ex);

        return UnifyResp.error(BaseUnifyCode.ERROR_400.getCode(), ex.getMessage());
    }


//    /**
//     * 自定义通用异常处理
//     */
//    @ExceptionHandler(value = LiveException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public UnifyResp<Serializable> liveException(LiveException ex) {
//        log.error("LiveException :", ex);
//
//        return UnifyResp.error(ex.getResultCode(), ex.getMessage());
//    }

    /**
     * 暂时未处理的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<UnifyResp<Serializable>> exception(Exception ex, HttpServletResponse response) {
        log.error("Response :{}; Exception :", response.getStatus(), ex);

        ResponseEntity<UnifyResp<Serializable>> resp = null;
        if (ex instanceof ServletRequestBindingException || ex instanceof BindException) {// MissingRequestHeaderException
            // 参数映射失败
            resp = new ResponseEntity<UnifyResp<Serializable>>(
                    UnifyResp.error(BaseUnifyCode.ERROR_400.getCode(), ex.getMessage()), HttpStatus.OK);
        } else if (ex instanceof MethodArgumentNotValidException) {
            // 参数校验
            resp = new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.error(BaseUnifyCode.ERROR_400.getCode(),
                            BindingResultUtil.getMessage(((MethodArgumentNotValidException) ex).getBindingResult())), HttpStatus.OK);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            // 请求方式不支持
            resp = new ResponseEntity<UnifyResp<Serializable>>(
                    UnifyResp.error(BaseUnifyCode.ERROR_401.getCode(), ex.getMessage()), HttpStatus.OK);
        }  else if (ex instanceof HttpMediaTypeException) {
            // 参数提交格式不正确
            resp = new ResponseEntity<UnifyResp<Serializable>>(
                    UnifyResp.error(BaseUnifyCode.ERROR_400.getCode(), ex.getMessage()), HttpStatus.OK);
        } else if (Objects.isNull(resp)) {
            resp = new ResponseEntity<UnifyResp<Serializable>>(
                    UnifyResp.error(BaseUnifyCode.ERROR_500.getCode(), ex.getMessage()), HttpStatus.OK);
        }

        return resp;
    }
}
