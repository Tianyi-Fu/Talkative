package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.FeedbackRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackRecordController {

    private final FeedbackRecordService service;

    /**
     * count
     */
    @PostMapping("/feedbackrecord/count")
    public Result count(@RequestBody Map<String,Object> json) throws Exception {
        return Result.success(service.gelTotal());
    }
}
