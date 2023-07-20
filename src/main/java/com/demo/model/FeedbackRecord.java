package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Tianyi Fu
 * @since 2023/7/19
 */
@Data
public class FeedbackRecord {

    //@TableId(value = "id", type = IdType.AUTO) 23
    private Integer feedbackRecordId;

    private String question;

    private String answer;

}
