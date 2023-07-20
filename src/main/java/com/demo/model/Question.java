package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
 * @author Tianyi Fu
 * @since 2023/7/20
 */
@Data
public class Question {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer feedbackRecordId;

    private String question;

    private String answer;
}
