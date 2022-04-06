package com.example.mytest.common.config;

import com.example.mytest.common.UnifyResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Slf4j
@RestController
@RequestMapping(value = "/error")
public class BasicExceptionController implements ErrorController {
	
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping
	public ResponseEntity<UnifyResp<Serializable>> handleError(HttpServletRequest request, HttpServletResponse response) {
		Object code = request.getAttribute("javax.servlet.error.status_code");
		Object message = request.getAttribute("javax.servlet.error.message");
		Object requestUri = request.getAttribute("javax.servlet.error.request_uri");
		log.error("request Failed :{}, {}, {}", code, message, requestUri);

		String c = String.valueOf(code);

//		if(NumericUtils.isNumeric(c) && Integer.valueOf(c) == 404) {
//			return new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.error(BaseUnifyCode.ERROR_404), HttpStatus.OK);
//		}
		
		return new ResponseEntity<UnifyResp<Serializable>>(UnifyResp.error(), HttpStatus.OK);
	}
	
}
