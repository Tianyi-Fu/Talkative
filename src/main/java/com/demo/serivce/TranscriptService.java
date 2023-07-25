package com.demo.serivce;

import com.demo.error.Exception;
import com.demo.mapper.TranscriptMapper;
import com.demo.model.ChatRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranscriptService {
    private final TranscriptMapper transcriptMapper;
    public String create(String transcript, String agentName,String chatRecordId) throws Exception {

        ChatRecord chatRecord = new ChatRecord();

        chatRecord.setTranscript(transcript);
        chatRecord.setAgentName(agentName);
        chatRecord.setChatRecordId(chatRecordId);
//        chatRecord.setFeedbackRecordId(id);
        transcriptMapper.insert(chatRecord);
        return chatRecord.getFeedbackRecordId();
    }
}
