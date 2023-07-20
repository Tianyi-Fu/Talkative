package com.demo.error;

import lombok.Data;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 *
 * exception
 */

@Data
public class Exception extends java.lang.Exception {

    private String msg;

    public Exception(String msg) {
        this.msg = msg;
    }

}
