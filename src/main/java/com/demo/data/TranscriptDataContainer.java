package com.demo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class TranscriptDataContainer {
    @JsonProperty("id")
    private int id;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("feedback")
    private Object feedback;

    @JsonProperty("handled")
    private boolean handled;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("ended_at")
    private String endedAt;

    @JsonProperty("messages")
    private List<TranscriptData> messages;

    // Default constructor (no-argument constructor)
    public TranscriptDataContainer() {
        messages = new ArrayList<>();
    }

    @JsonSetter("messages")
    public void setMessages(List<TranscriptData> messages) {
        if (messages == null) {
            this.messages = new ArrayList<>();
        } else {
            this.messages = messages;
        }
    }
}
