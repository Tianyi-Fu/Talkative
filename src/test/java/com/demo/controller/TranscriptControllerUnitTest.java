package com.demo.controller;

import com.demo.serivce.TranscriptService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TranscriptControllerUnitTest {

    @InjectMocks
    private TranscriptController transcriptController;

    @Mock
    private TranscriptService transcriptService;

    private MockMvc mockMvc;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transcriptController).build();
    }

    @Test
    public void testPostRequestToReadTranscript() throws Exception {
        // Transcript JSON array
        JSONArray jsonArray = new JSONArray();
        JSONObject agentMessage = new JSONObject();
        agentMessage.put("Bobo", "Hello");
        jsonArray.put(agentMessage);
        JSONObject customerMessage = new JSONObject();
        customerMessage.put("Customer", "Hi there");
        jsonArray.put(customerMessage);

        // Create a JSON object with the transcript
        JSONObject requestJson = new JSONObject();
        requestJson.put("agent_name", "Agent");
        requestJson.put("chatRecordId", 001);
        requestJson.put("transcript", jsonArray);

        // Mock transcriptService.create
        when(transcriptService.create(requestJson.toString(), "Agent", "001")).thenReturn("456");

        // Perform a POST request to /read_transcript with the JSON payload
        mockMvc.perform(post("/read_transcript")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson.toString()))
                .andExpect(status().isOk()); // Assuming HTTP 200
    }
}