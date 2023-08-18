package com.demo.serivce;

import com.demo.data.Data;
import com.demo.error.Exception;
import com.demo.mapper.Mapper;
import com.demo.model.FeedbackAnalysis;
import com.demo.model.FeedbackRecord;
import com.demo.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class FeedbackService {

    @Autowired
    FeedbackAnalysisService analysisService;

    //    private final ChatRecordMapper chatRecordMapper;
    private final Mapper mapper;
    static int id = 0;

    @Transactional(rollbackFor = Exception.class)
    public void create(List<Data.FeedbackSaveParam> param, String agentName, String chatRecordId, String feedbackRecordId) throws Exception {

        List<FeedbackRecord> feedbackRecord = BeanUtil.convertToList(param, FeedbackRecord.class);

        int questionId = 1;
        for (FeedbackRecord record : feedbackRecord) {
            record.setQuestionId(id);
            record.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            record.setFeedbackRecordId(chatRecordId);
            record.setQuestionId(questionId++);
            record.setFeedbackRecordId(feedbackRecordId);
            mapper.insert(record);
        }
        FeedbackAnalysis feedbackAnalysis = new FeedbackAnalysis();
        feedbackAnalysis.setFeedbackRecordId(feedbackRecordId);
        analysisService.analysis(feedbackAnalysis);
//        //id=feedBackRecord id
//        ChatRecord chatRecord=new ChatRecord();
//        chatRecord.setAgentName(agentName);
////        chatRecord.setFeedbackRecordId(id);
//        chatRecordMapper.insert(chatRecord);
//        id++;

    }

}
