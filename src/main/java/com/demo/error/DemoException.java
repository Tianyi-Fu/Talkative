package com.demo.error;

import lombok.Data;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 *
 * exception
 */

@Data
public class DemoException extends Exception {

    private String msg;

    public DemoException(String msg) {
        this.msg = msg;
    }

}
