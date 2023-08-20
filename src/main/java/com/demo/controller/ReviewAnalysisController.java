package com.demo.controller;

import com.demo.model.Result;
import com.demo.serivce.ReviewAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class ReviewAnalysisController {

    @Autowired
    private final ReviewAnalysisService service;

    /**
     * list
     */
    @PostMapping("/reviewanalysis/list")
    public Result list(@RequestBody Map<String,Object> json) throws Exception {
        //System.out.println(json.get("size"));
        Object o = json.get("page");
        Object o1 = json.get("size");
        int page = 1;
        int size = 10;
        if (null != o && null != o1) {
            page = (int) o;
            size = (int) o1;
        }

        Object[] chatRecordIds = service.selectPageList((String) json.get("chatRecordID"), page, size);
        json.clear();
        json.put("list", chatRecordIds[0]);
        json.put("total", chatRecordIds[1]);
        json.put("page", chatRecordIds[2]);
        json.put("size", chatRecordIds[1]);
        return Result.success(json);
    }

    /**
     * sentiment
     */
    @PostMapping("/reviewanalysis/sentiment")
    public Result sentiment(@RequestBody Map<String,Object> json) throws Exception {
        List<Map<String, Object>> ntlk = service.selectSentimentNtlk();
        List<Map<String, Object>> flair = service.selectSentimentFlair();
        List<Map<String, Object>> bert = service.selectSentimentBert();
        json.clear();
        json.put("sentiment_ntlk", ntlk);
        json.put("sentiment_flair", flair);
        json.put("sentiment_bert", bert);
        return Result.success(json);
    }
    /**
     * topic
     */
    @PostMapping("/reviewanalysis/topic")
    public Result topic(@RequestBody Map<String,Object> json) throws Exception {
        Map<String, Object> topic = service.getTopic();
        String cell[]={"thank","chat","agent","steve","need","work","talk","ye","script","felix","want","integr","great","compani","que","tener","good","time","appl","widget","code","page","ignit","client","access","avail","trial","free","app","video","sie","html","copi","user","bot"};
        json.clear();
        json.put("key", cell);
        json.put("list", topic);
        return Result.success(json);
    }
}
