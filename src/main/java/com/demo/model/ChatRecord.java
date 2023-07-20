package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class ChatRecord {
    // Primary key
    @TableId(value = "chat_record_id", type = IdType.AUTO)
    private String id;

    private String transcript;

    private int agentId;

    private String agentName;

//    private int feedbackRecordId;
}
