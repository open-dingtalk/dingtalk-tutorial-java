package com.example.event;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EventListener {
    @Value("${dingtalk.app.client-id}")
    private String clientId;

    @Value("${dingtalk.app.client-secret}")
    private String clientSecret;

    private final EventConsumer eventConsumer;

    public EventListener() {
        this.eventConsumer = new EventConsumer();
    }
    @PostConstruct
    public void init() throws Exception {
        // init stream client
        OpenDingTalkClient client = OpenDingTalkStreamClientBuilder
                .custom()
                .credential(new AuthClientCredential(clientId, clientSecret))
                .registerAllEventListener(eventConsumer)
                .build();
        client.start();
    }
}
