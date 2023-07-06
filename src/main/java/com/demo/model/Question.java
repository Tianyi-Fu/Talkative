package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
@Data
public class Question {
    // Primary key
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    // content
    private String content;
    private Boolean sentiment;
    private String type;
}
