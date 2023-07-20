package com.demo.util;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;

import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/19
 */
public class AIUtil {

    public static String chatWithAI(OpenAIClient client, List<ChatMessage> chatMessages) {
        ChatCompletions chatCompletions = client.getChatCompletions("gpt-3.5-turbo-16k", new ChatCompletionsOptions(chatMessages));
        StringBuilder sb = new StringBuilder();
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatMessage message = choice.getMessage();
            sb.append(message.getContent());
        }
        return sb.toString();
    }

}
