package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.FeedBackRecordAndUserInfoService;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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

        Object[] chatRecordIds = feedBackRecordAndUserInfoService.selectFeedBackRecordAndUserInfo((String) map.get("chatRecordID"), page, size,
                (String) map.get("satisfaction"), (String) map.get("agentName"), (String) map.get("beginTime"),
                (String) map.get("endTime"));
        map.clear();
        map.put("list", chatRecordIds[0]);
        map.put("total", chatRecordIds[1]);
        map.put("page", chatRecordIds[2]);
        map.put("size", chatRecordIds[1]);
        map.put("all_total", feedBackRecordAndUserInfoService.selectCountCharRecord());
        map.put("unsatisfiedCount", feedBackRecordAndUserInfoService.selectAnswerCountByQuestionId1(1));
        map.put("neutralCount", feedBackRecordAndUserInfoService.selectAnswerCountByQuestionId1(2));
        map.put("satisfiedCount", feedBackRecordAndUserInfoService.selectAnswerCountByQuestionId1(3));
        return Result.success(map);
    }

    @PostMapping("/question/list")
    public Result<Map<String, Object>> getFeedBackRecordByRecordId(@RequestBody Map<String, Object> map) {
        int id = (Integer) map.get("feedbackRecordId");
        List<Map<String, Object>> feedbackRecordId = feedBackRecordAndUserInfoService.selectFeedBackRecordByRecordId(String.valueOf(id));
        map.put("list", feedbackRecordId);
        return Result.success(map);
    }

    @PostMapping("/transcript/list")
    public Result<Map<String, Object>> getChatRecordByRecordId(@RequestBody Map<String, Object> map) {
        int id = (Integer) map.get("feedbackRecordId");
        Map<String, Object> map1 = feedBackRecordAndUserInfoService.selectChatRecordByRecordId(String.valueOf(id));
        map1.remove("chat_record_id");
        String transcript = (String) map1.get("transcript");
        return Result.success(map1);
    }

    @PostMapping("/all/del")
    public Result del(@RequestBody Map<String, Object> map) {
        feedBackRecordAndUserInfoService.del((int) map.get("feedbackRecordId"));
        return Result.success();
    }

    @PostMapping("/all/upd")
    public Result upd(@RequestBody Map<String,Object> json) {
        System.out.println(json);
        feedBackRecordAndUserInfoService.upd((String) json.get("feedbackRecordId"), (String)json.get("firstName"),
                (String)json.get("lastName"), (String)json.get("email"), (String)json.get("agentName"));
        return Result.success();
    }
}
