package com.demo.controller;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.demo.data.Data;
import com.demo.model.Result;
import com.demo.serivce.FeedbackService;
import com.demo.util.AIUtil;
import com.demo.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FeedbackController {

    private final FeedbackService service;

    /*    *//**
     * Create
     *//*
    @PostMapping("/create/{agentName}")
    public Result create(@RequestBody List<Data.FeedbackSaveParam> param, @PathVariable String agentName) throws Exception {

        service.create(param, agentName);
        return Result.success();
    }*/
    /**
     * Create
     */
    @PostMapping("/create/{agentName}")
    public Result create(@RequestBody Data.FeedbackSaveParamAndChatId param, @PathVariable String agentName) throws Exception {

        service.create(param.getList(), agentName, param.getChatRecordId(),param.getFeedbackRecordId());
        return Result.success();
    }


    @GetMapping("/openai-chat")
    public Result<Data.QuestionVo> openaiChat(Data.ChatParam param) {
        OpenAIClient client = new OpenAIClientBuilder()
                // OpenAI key
                .credential(new NonAzureOpenAIKeyCredential(
                        "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"))
                .buildClient();
        //Add dialogue and six requirements.
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent(param.getContent()));
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent("Please analyze the emotions of the person during the above conversation in JSON format."));


        //Add ASSISTANT response.
        String aiMessage = AIUtil.chatWithAI(client, chatMessages);
        chatMessages.add(new ChatMessage(ChatRole.ASSISTANT).setContent(aiMessage));

        //Have the AI output in JSON format.
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent(
                "Based on the above dialogue and the emotions of the conversation participants, generate six forms to ask the Customer for their feedback of the conversation. Please output in JSON format according to the following requirements:" +
                        "\n 1. There should be six questions." +
                        "\n 2. The six questions are output in the following types and order: the first question has a question type of Scale (1-5). The second and third questions have a question type of Yes/No. The remaining questions have a question type of Open ended and related to enquiry/issue in the conversation." +
                        "\n 3. The outermost layer should be a 'questions' array, and the inner elements should be of string type." +
                        "\n 4. Only output in JSON format."
        ));
        //convert to json
        String json = AIUtil.chatWithAI(client, chatMessages);
        Data.QuestionVo questionVo = JsonUtil.toClass(json, Data.QuestionVo.class);
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent(

                "Please output the 'sender_name' from this conversation, excluding the 'customer'. Only provide me with this name in string format and only output this single name."
        ));
        String agentName = AIUtil.chatWithAI(client, chatMessages);
        questionVo.setAgentName(agentName);
        return Result.success(questionVo);
    }
}
