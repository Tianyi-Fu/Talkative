package com.demo.service;

import com.demo.error.Exception;
import com.demo.mapper.TranscriptMapper;
import com.demo.model.ChatRecord;
import com.demo.serivce.TranscriptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TranscriptServiceUnitTest {

//    @Mock
//    private TranscriptMapper transcriptMapper;
//
//    @Autowired
//    private TranscriptService transcriptService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Create instance of TranscriptService with the mock
//        transcriptService = new TranscriptService(transcriptMapper);
//    }
//
//    @Test
//    public void testCreateChatRecord() throws Exception {
//        String transcript = "Test transcript";
//        String agentName = "Agent";
//        String chatRecordId = "001";
//        String feedbackRecordId = "456";
//
//        // Create ChatRecord instance
//        ChatRecord expectedChatRecord = new ChatRecord();
//        expectedChatRecord.setTranscript(transcript);
//        expectedChatRecord.setAgentName(agentName);
//        expectedChatRecord.setChatRecordId(chatRecordId);
//        expectedChatRecord.setFeedbackRecordId(feedbackRecordId);
//
//        // Mock transcriptMapper.insert
//        when(transcriptMapper.insert(any(ChatRecord.class))).thenAnswer(invocation -> {
//            ChatRecord chatRecordArgument = invocation.getArgument(0);
//            chatRecordArgument.setFeedbackRecordId(feedbackRecordId);
//            return 1;
//        });
//
//        // Call the service method
//        String actualFeedbackRecordId = transcriptService.create(transcript, agentName, chatRecordId);
//
//        // Verify that transcriptMapper.insert was called with the expected ChatRecord
//        verify(transcriptMapper).insert(expectedChatRecord);
//
//        // Verify that the returned feedbackRecordId matches the expected value
//        assertEquals(feedbackRecordId, actualFeedbackRecordId);
//    }
}
