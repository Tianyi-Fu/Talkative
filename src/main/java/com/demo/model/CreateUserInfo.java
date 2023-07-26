package com.demo.model;

import com.demo.data.Data;

import java.util.List;

/**
 * @ClassName: CreateUserInfo
 * @Description:
 * @author: Wei Peng
 * @date: 2023-07-24 23:41
 * @version: 1.0
 */
@lombok.Data
public class CreateUserInfo {

    private String firstName;
    private String lastName;
    private String email;
    private List<Data.FeedbackSaveParam> array;
    private List<String> chatRecordIdList;
    private String chatRecordId;
}
