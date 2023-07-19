package com.demo.controller;

import com.demo.error.DemoException;
import com.demo.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {
    @PostMapping("/read_transcript")
    public Result query(@RequestBody String param) throws DemoException {
        System.out.println(param);
        return Result.success();
    }
}
