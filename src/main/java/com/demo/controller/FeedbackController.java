package com.demo.controller;

import com.demo.error.DemoException;
import com.demo.model.ChatRecord;
import com.demo.model.Result;
import com.demo.serivce.TranscriptService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    private final TranscriptService transcriptService;

    @PostMapping("/read_transcript")
    public Result query(@RequestBody String param) throws DemoException {
        JSONObject obj = new JSONObject(param);

        System.out.println(obj.getString("agent_name"));
        System.out.println(obj.getJSONArray("transcript"));

        String agentName = obj.getString("agent_name");
        String transcript = String.valueOf(obj.getJSONArray("transcript"));

        transcriptService.create(transcript, agentName);

        return Result.success();
    }
}
