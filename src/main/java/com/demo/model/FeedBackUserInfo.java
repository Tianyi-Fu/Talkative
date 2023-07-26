package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName: FeedBackUserInfo
 * @Description:
 * @author: Wei Peng
 * @date: 2023-07-24 23:41
 * @version: 1.0
 */

@Data
public class FeedBackUserInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String agentName;

    private String chatRecordId;

    private Timestamp createdAt;
}
