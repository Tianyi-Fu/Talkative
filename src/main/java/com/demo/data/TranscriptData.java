package com.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TranscriptData {
    @JsonProperty("message")
    private String message;

    @JsonProperty("sender_name")
    private String senderName;
}
