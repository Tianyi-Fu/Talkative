package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName: FeedbackAnalysis
 * @author: Wei Peng
 * @date: 2023-08-18 14:05
 * @version: 1.0
 * @Description:
 */

@Data
public class FeedbackAnalysis {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String question;
    private String suggestion;
    private Timestamp createdAt;
    private String feedbackRecordId;
}
