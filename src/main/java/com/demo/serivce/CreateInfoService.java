package com.demo.serivce;

import com.demo.data.Data;
import com.demo.mapper.CreateInfoMapper;
import com.demo.model.FeedbackUserInfo;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName: CreateInfoService
 * @Description:
 * @author: Wei Peng
 * @date: 2023-07-24 23:41
 * @version: 1.0
 */

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CreateInfoService {

    private final CreateInfoMapper mapper;

    public void createInfo(List<Data.FeedbackSaveParam> param, List<String> chatRecordIdList, String agentName,
                           String firstName, String lastName, String email, String chatRecordId) {
        FeedbackUserInfo createInfoFeedBack = new FeedbackUserInfo();
        createInfoFeedBack.setAgentName(agentName);

        createInfoFeedBack.setChatRecordId(chatRecordId);
        createInfoFeedBack.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        createInfoFeedBack.setEmail(email);
        createInfoFeedBack.setFirstName(firstName);
        createInfoFeedBack.setLastName(lastName);
        mapper.insert(createInfoFeedBack);
    }
}
