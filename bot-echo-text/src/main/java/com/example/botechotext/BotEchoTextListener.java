package com.example.botechotext;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BotEchoTextListener {
    @Value("${dingtalk.app.client-id}")
    private String clientId;

    @Value("${dingtalk.app.client-secret}")
    private String clientSecret;

    private final BotEchoTextConsumer botEchoTextConsumer;

    @Autowired
    public BotEchoTextListener(BotEchoTextConsumer botEchoTextConsumer) {
        this.botEchoTextConsumer = botEchoTextConsumer;
    }

    @PostConstruct
    public void init() throws Exception {
        // init stream client
        OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential(clientId, clientSecret))
                .registerCallbackListener("/v1.0/im/bot/messages/get", botEchoTextConsumer)
                .build();
        client.start();
    }
}
