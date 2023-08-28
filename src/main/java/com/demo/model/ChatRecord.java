package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ChatRecord {
    // Primary key
    @TableId(value = "feedback_record_id", type = IdType.AUTO)
    private String feedbackRecordId;

    private String chatRecordId;

    private String transcript;

    private int agentId;

    private String agentName;
}
