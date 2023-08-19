package com.demo.controller;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.demo.data.Data;
import com.demo.model.FeedbackAnalysis;
import com.demo.model.Result;
import com.demo.serivce.FeedbackAnalysisService;
import com.demo.util.AIUtil;
import com.demo.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wei Peng
 * @since 2023/8/17
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackAnalysisController {

    @Autowired
    FeedbackAnalysisService service;


    @GetMapping("/qry/analysis/{feedbackRecordId}")
    public Result<String> selectAnalysis(@PathVariable("feedbackRecordId") String feedbackRecordId) {
        String result = service.selectAnalysis(feedbackRecordId);
        return Result.success(result);
    }


    @GetMapping("/analysis/{feedbackRecordId}")
    public Result<String> analysis(@PathVariable("feedbackRecordId") String feedbackRecordId) {
        FeedbackAnalysis feedbackAnalysis = new FeedbackAnalysis();
        feedbackAnalysis.setFeedbackRecordId(feedbackRecordId);
        String result = service.analysis(feedbackAnalysis);
        return Result.success(result);
    }

}