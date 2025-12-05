package com.example.instagram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    //404
    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFound(NoResourceFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException ex, Model model) {
        ErrorCode errorCode = ex.getErrorCode();
        model.addAttribute("message", errorCode.getMessage());
        return switch (errorCode.getHttpStatus()) {
            case HttpStatus.NOT_FOUND -> "error/404";
            case BAD_REQUEST ->  "error/400";
            default -> "error/500";
        };
    }

    //모든 예외 처리
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/500";
    }
}
