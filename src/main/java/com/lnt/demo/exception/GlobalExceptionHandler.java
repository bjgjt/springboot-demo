package com.lnt.demo.exception;

import com.lnt.demo.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiResponse<?> handlingRuntimeException(Exception exception) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setCode(ExceptionCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.setMessage(ExceptionCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return response;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<?> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String exceptionCodeKey = exception.getAllErrors().get(0).getDefaultMessage();
        ExceptionCode exceptionCode = ExceptionCode.valueOf(exceptionCodeKey);

        ApiResponse<?> response = new ApiResponse<>();
        response.setCode(exceptionCode.getCode());
        response.setMessage(exceptionCode.getMessage());

        return response;
    }

    @ExceptionHandler(value = AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<?> handlingAppException(AppException exception) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setCode(exception.getExceptionCode().getCode());
        response.setMessage(exception.getExceptionCode().getMessage());
        return response;
    }
}
