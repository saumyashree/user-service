package com.mycompany.userservice.exception;

import com.mycompany.userservice.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorDTO>> handleBusinessException(BusinessException bex){
        return new ResponseEntity<List<ErrorDTO>>(bex.getErrors(), HttpStatus.UNAUTHORIZED);
    }
}
