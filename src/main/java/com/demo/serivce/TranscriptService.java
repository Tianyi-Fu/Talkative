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
    public void create(String transcript, String agentName) throws Exception {

        ChatRecord chatRecord = new ChatRecord();

        chatRecord.setTranscript(transcript);
        chatRecord.setAgentName(agentName);
//        chatRecord.setFeedbackRecordId(id);
        transcriptMapper.insert(chatRecord);
    }
}
