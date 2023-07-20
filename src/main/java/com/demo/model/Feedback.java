package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Feedback {
    // Primary key
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private int id;
    private String question;
    private String answer;
    private Timestamp createdAt;
    private int chatRecordId;
}
