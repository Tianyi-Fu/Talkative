package com.demo.controller;

import com.demo.error.DemoException;
import com.demo.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 *
 */
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = DemoException.class)
    public Result handleError(DemoException e) {
        return Result.fail(e.getMsg());
    }

}
