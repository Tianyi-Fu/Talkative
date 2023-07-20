package com.demo.controller;

import com.demo.error.DemoException;
import com.demo.model.Result;
import com.demo.serivce.TranscriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    private final TranscriptService transcriptService;

    @PostMapping("/read_transcript")
    public Result query(@RequestBody String param) throws DemoException {
        System.out.println(param);

        transcriptService.create(param);

        return Result.success();
    }
}
