package com.example.botechomarkdown;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.chatbot.BotReplier;
import com.dingtalk.open.app.api.models.bot.ChatbotMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class BotEchoMarkdownConsumer implements OpenDingTalkCallbackListener<ChatbotMessage, Void> {

    @Override
    public Void execute(ChatbotMessage message) {
        StringBuilder builder = new StringBuilder();
        builder.append("echo received message:\n");
        for (String line: message.getText().getContent().trim().split("\n")) {
            builder.append("\n>1. ").append(line.trim());
        }
        try {
            BotReplier.fromWebhook(message.getSessionWebhook()).replyMarkdown("dingtalk-tutorial-java", builder.toString(), Arrays.asList(message.getSenderStaffId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
