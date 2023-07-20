package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Chat_record {
    // Primary key
    @TableId(value = "chat_record_id", type = IdType.AUTO)
    private String id;

    private List transcript;

    private Integer agent_id;

    private String agent_name;
}
