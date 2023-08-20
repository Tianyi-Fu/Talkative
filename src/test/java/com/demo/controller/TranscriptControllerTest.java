package com.demo.controller;

import com.demo.serivce.TranscriptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TranscriptController.class)
class TranscriptControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private TranscriptController transcriptController;

    @Mock
    private TranscriptService transcriptService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transcriptController).build();
    }

    @Test
    public void shouldBeAbleToCreateTranscript() throws Exception {
        String requestJson = "{\"agent_name\":\"Bobo\",\"chatRecordId\":1,\"transcript\":\"[{\\\"Bobo\\\": \\\"Hello\\\"}, {\\\"Customer\\\": \\\"Hi there\\\"}]\"}";

        // Mock the behavior of the transcriptService
        when(transcriptService.create(anyString(), anyString(), anyString())).thenReturn("Transcript created successfully");

        // Perform the POST request to the "/read_transcript"
        mockMvc.perform(post("/read_transcript")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value("Transcript created successfully"));

        // Verify that the transcriptService's create method was called with the correct arguments
        verify(transcriptService, times(1)).create("[{\"Bobo\": \"Hello\"}, {\"Customer\": \"Hi there\"}]", "Bobo", "1");
    }
}