package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.FeedbackRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Result count(@RequestBody Map<String, Object> json) throws Exception {
        Map<String, Object> info = service.gelTotal();
        String total = info.get("total") + "";
        int totals = Integer.parseInt(total);
        if (totals > 0) totals = totals / 6;
        info.put("total", totals + "");
        return Result.success(info);
    }
}
