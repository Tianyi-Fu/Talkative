package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.FeedBackRecordAndUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FeedBackRecordAndUserInfoController
 * @author: Wei Peng
 * @date: 2023-08-02 22:33
 * @version: 1.0
 * @Description:
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedBackRecordAndUserInfoController {

    @Autowired
    FeedBackRecordAndUserInfoService feedBackRecordAndUserInfoService;


    @PostMapping("/rc/list")
    public Result<Map<String, Object>> getFeedBackRecordAndUserInfo(@RequestBody Map<String, Object> map) {
        Object o = map.get("page");
        Object o1 = map.get("size");
        int page = 1;
        int size = 10;
        if (null != o && null != o1) {
            page = (int) o;
            size = (int) o1;
        }

        Object[] chatRecordIds = feedBackRecordAndUserInfoService.selectFeedBackRecordAndUserInfo((String) map.get("chatRecordId"), page, size);
        map.clear();
        map.put("list", chatRecordIds[0]);
        map.put("total", chatRecordIds[1]);
        return Result.success(map);
    }

    @PostMapping("/question/list")
    public Result<Map<String, Object>> getFeedBackRecordByRecordId(@RequestBody Map<String, Object> map) {
        List<Map<String, Object>> feedbackRecordId = feedBackRecordAndUserInfoService.selectFeedBackRecordByRecordId((String) map.get("feedbackRecordId"));
        map.put("list",feedbackRecordId);
        return Result.success(map);
    }

    @PostMapping("/transcript/list")
    public Result<Map<String, Object>> getChatRecordByRecordId(@RequestBody Map<String, Object> map) {
        Map<String, Object> map1 = feedBackRecordAndUserInfoService.selectChatRecordByRecordId((String) map.get("feedbackRecordId"));
        return Result.success(map1);
    }
}
