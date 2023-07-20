package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
 * @author Tianyi Fu
 * @since 2023/7/20
 */
@Data
public class ChatRecord {
    @TableId(value = "chat_record_id ", type = IdType.AUTO)
    private int chatRecordId;
    private String transcript;
    private int agentId;
    private String agentName;
    private int feedbackRecordId;

}
