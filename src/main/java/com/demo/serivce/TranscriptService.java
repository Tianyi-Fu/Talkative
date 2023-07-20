package com.demo.serivce;

import com.demo.error.DemoException;
import com.demo.mapper.TranscriptMapper;
import com.demo.model.ChatRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranscriptService {
    private final TranscriptMapper transcriptMapper;
//    static int id = 1;

    public void create(String transcript, String agentName) throws DemoException {
//        System.out.println(transcript);

//        List<ChatRecord> chat = BeanUtil.convertToList(transcript, ChatRecord.class);

        ChatRecord chatRecord = new ChatRecord();

        chatRecord.setTranscript(transcript);
        chatRecord.setAgentName(agentName);
//        chatRecord.setFeedbackRecordId(id);
        System.out.println(chatRecord);
        transcriptMapper.insert(chatRecord);

//        id++;
    }
}
