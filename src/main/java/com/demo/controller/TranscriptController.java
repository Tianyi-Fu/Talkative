package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.TranscriptService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class TranscriptController {
    private final TranscriptService transcriptService;

    @PostMapping("/read_transcript")
    public Result<String> query(@RequestBody String param) throws Exception {
        JSONObject obj = new JSONObject(param);

//        System.out.println(obj.getString("agent_name"));
//        System.out.println(obj.getJSONArray("transcript"));

        String agentName = obj.getString("agent_name");
        String chatRecordId = String.valueOf(obj.getInt("chatRecordId"));
        String transcript = String.valueOf(obj.getJSONArray("transcript"));

        return Result.success(transcriptService.create(transcript, agentName, chatRecordId));
    }
}
