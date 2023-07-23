package com.demo.serivce;

import com.demo.data.Data;
import com.demo.error.Exception;
import com.demo.mapper.ChatRecordMapper;
import com.demo.mapper.Mapper;
import com.demo.model.ChatRecord;
import com.demo.model.FeedbackRecord;
import com.demo.util.BeanUtil;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

//    private final ChatRecordMapper chatRecordMapper;
    private final Mapper mapper;
    static int id=0;
    public void create(List<Data.FeedbackSaveParam> param, String agentName) throws Exception {

        List<FeedbackRecord> feedbackRecord = BeanUtil.convertToList(param, FeedbackRecord.class);
        for (FeedbackRecord record : feedbackRecord) {
            record.setQuestionId(id);
            record.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            mapper.insert(record);
        }
//        //id=feedBackRecord id
//        ChatRecord chatRecord=new ChatRecord();
//        chatRecord.setAgentName(agentName);
////        chatRecord.setFeedbackRecordId(id);
//        chatRecordMapper.insert(chatRecord);
//        id++;

    }

}
