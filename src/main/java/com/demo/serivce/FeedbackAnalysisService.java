package com.demo.serivce;

import com.alibaba.fastjson.JSONObject;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.demo.mapper.FeedbackAnalysisMapper;
import com.demo.model.FeedbackAnalysis;
import com.demo.util.AIUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackAnalysisService {

    @Resource
    FeedbackAnalysisMapper feedbackAnalysisMapper;

    @Resource
    FeedBackRecordAndUserInfoService feedBackRecordAndUserInfoService;

    public String selectAnalysis(String feedbackRecordId) {
        return feedbackAnalysisMapper.selectAnalysis(feedbackRecordId);
    }


    @Transactional(rollbackFor = Exception.class)
    public String analysis(FeedbackAnalysis feedbackAnalysis) {
        String feedbackRecordId = feedbackAnalysis.getFeedbackRecordId();
        List<Map<String, Object>> maps = feedBackRecordAndUserInfoService.selectFeedBackRecordByRecordId(feedbackRecordId);
        if (null == maps || maps.size() <= 0) {
            return null;
        }
        List<JSONObject> jsons = new ArrayList<>();
        for (Map<String, Object> e : maps) {
            JSONObject jsonObject = new JSONObject();
            int questionId = (int) e.get("question_id");
            if (questionId > 2) {
                jsonObject.put("question", e.get("question"));
                jsonObject.put("answer", e.get("answer"));
                jsons.add(jsonObject);
            }
        }
        String content = jsons.toString();
        String result = sendAnalysis(content);
        feedbackAnalysis.setQuestion(content);
        feedbackAnalysis.setSuggestion(result);
        feedbackAnalysis.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (1 != feedbackAnalysisMapper.insert(feedbackAnalysis)) {
            throw new RuntimeException("insert file");
        }
        return result;
    }

    public String sendAnalysis(String content) {
        String json;
        try {
            OpenAIClient client = new OpenAIClientBuilder()
                    // OpenAI key
                    .credential(new NonAzureOpenAIKeyCredential(
                            "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"))
                    .buildClient();
            //Add dialogue and six requirements.
            List<ChatMessage> chatMessages = new ArrayList<>();
            chatMessages.add(new ChatMessage(ChatRole.USER).setContent(content));
            chatMessages.add(new ChatMessage(ChatRole.USER).setContent("Please analyze the emotions of the person during the above questions and answers in JSON format."));

            //Add ASSISTANT response.
            String aiMessage = AIUtil.chatWithAI(client, chatMessages);
            chatMessages.add(new ChatMessage(ChatRole.ASSISTANT).setContent(aiMessage));

            //Have the AI output in JSON format.
            chatMessages.add(new ChatMessage(ChatRole.USER).setContent("" +
                    "Based on the above questions and answers and the emotions participants, please qualitatively analyse the quizzes, extract and briefly summarise the suggestions inside the answers and generate to show the suggestions of the Customer . Please output in JSON format according to the following requirements:" +
                    "\n 1. There should be a short summary suggestion." +
                    "\n 2. The elements should be of string type." +
                    "\n 3. Only output in JSON format." +
                    "\n 4、Give a data in json format, and the key of json is suggestions , and the value is the data array you parsed."));
            //convert to json
            json = AIUtil.chatWithAI(client, chatMessages);
            System.out.println("--------------------");
            System.out.println(json);
            JSONObject parse = JSONObject.parseObject(json);
            json = parse.getJSONArray("suggestions").toJSONString();

        } catch (Exception e) {
            e.printStackTrace();
            json = "this is test";
        }

        return json;
    }

}
