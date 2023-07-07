package com.demo.controller;

import com.demo.data.DemoData;
import com.demo.error.DemoException;
import com.demo.model.Result;
import com.demo.serivce.DemoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;

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
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }


    /**
     * Query all
     */
    @GetMapping("/all")
    public Result<List<DemoData.Vo>> queryAll() throws DemoException {
        List<DemoData.Vo> vo = demoService.queryAll();
        return Result.success(vo);
    }

    /**
     * Query
     */
    @GetMapping("/query")
    public Result<DemoData.Vo> query(DemoData.QueryParam param) throws DemoException {
        if (param.getContent()==null || param.getContent().equals(""))throw new DemoException("No values");
        DemoData.Vo vo = demoService.query(param);
        return Result.success(vo);
    }

    /**
     * Create
     */
    @PostMapping("/create")
    public Result create(@RequestBody DemoData.SaveParam param) throws DemoException {
        demoService.create(param);
        return Result.success();
    }

    /**
     * Update
     */
    @PostMapping("/update")
    public Result update(@RequestBody DemoData.SaveParam param) throws DemoException {
        demoService.update(param);
        return Result.success();
    }

    /**
     * Delete
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        demoService.delete(id);
        return Result.success();
    }
//    @GetMapping("/azure-openai-chat")
//    public static String azureOpenaiChat() {
//        OpenAIClient client = new OpenAIClientBuilder()
//                // Azure OpenAI key
//                .credential(new AzureKeyCredential(""))
//                .endpoint("")
//                .buildClient();
//        List<ChatMessage> chatMessages = new ArrayList<>();
//        chatMessages.add(new ChatMessage(ChatRole.SYSTEM).setContent("what's your name"));
//        chatMessages.add(new ChatMessage(ChatRole.USER).setContent("what's your mom"));
//        chatMessages.add(new ChatMessage(ChatRole.ASSISTANT).setContent("Sing something"));
//        chatMessages.add(new ChatMessage(ChatRole.USER).setContent("are you happy?"));
//        ChatCompletions chatCompletions = client.getChatCompletions("ppt-gpt", new ChatCompletionsOptions(chatMessages));
//        StringBuilder sb = new StringBuilder();
//        for (ChatChoice choice : chatCompletions.getChoices()) {
//            ChatMessage message = choice.getMessage();
//            sb.append(message.getContent());
//        }
//        return sb.toString();
//    }

    @GetMapping("/openai-chat")
    public static String openaiChat() {
        OpenAIClient client = new OpenAIClientBuilder()
                // OpenAI key
                .credential(new NonAzureOpenAIKeyCredential("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"))
                .buildClient();

        // message
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(ChatRole.SYSTEM).setContent("what's your name"));
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent("what's your mom"));
        chatMessages.add(new ChatMessage(ChatRole.ASSISTANT).setContent("Sing something"));
        chatMessages.add(new ChatMessage(ChatRole.USER).setContent("are you happy?"));
        ChatCompletions chatCompletions = client.getChatCompletions("gpt-3.5-turbo-16k", new ChatCompletionsOptions(chatMessages));

        StringBuilder sb = new StringBuilder();
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatMessage message = choice.getMessage();
            sb.append(message.getContent());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String openaiChat = openaiChat();
        //String azureOpenaiChat = azureOpenaiChat();
        System.out.println("openaiChat: " + openaiChat);
        //System.out.println("azureOpenaiChat: " + azureOpenaiChat);
    }


}
