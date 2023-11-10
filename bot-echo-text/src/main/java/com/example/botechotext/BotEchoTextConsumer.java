package com.example.botechotext;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.chatbot.BotReplier;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BotEchoTextConsumer implements OpenDingTalkCallbackListener<ChatbotMessage, Void> {

    @Override
    public Void execute(ChatbotMessage message) {
        String response = String.format("echo received message: [%s]", message.getText().getContent().trim());
        try {
            BotReplier.fromWebhook(message.getSessionWebhook()).replyText(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
