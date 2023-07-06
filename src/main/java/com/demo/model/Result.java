package com.demo.model;

import com.demo.data.DemoData;
import lombok.Data;

import java.util.HashMap;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 * <p>
 * encapsulation
 */
@Data
public class Result<TargetClass> {

    // data
    private TargetClass data;

    // code For example, 200 means success, 500 means business error
    private int code;

    // business message
    private String message;

    public Result(int code, String message) {
        this(null, code, message);
    }

    public Result(TargetClass data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <TargetClass> Result<TargetClass> success(TargetClass data) {
        return new Result<>(data, 200, "成功");
    }

    public static Result fail(String message) {
        return new Result(500, message);
    }

    public static Result success() {
        return new Result(null, 200, "成功");
    }
}
