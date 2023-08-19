package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Tianyi Fu
 * @since 2023/7/19
 */
@Data
public class FeedbackRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer questionId;

    private String question;

    private String answer;

    private Timestamp createdAt;

    private String feedbackRecordId;
}
