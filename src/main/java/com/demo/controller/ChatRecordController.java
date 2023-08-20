package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.ChatRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ChatRecordController {

    @Autowired
    private final ChatRecordService service;

    /**
     * count
     */
    @PostMapping("/chatrecord/count")
    public Result count(@RequestBody Map<String,Object> json) throws Exception {
        return Result.success(service.gelTotal());
    }
}
