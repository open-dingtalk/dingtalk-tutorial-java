package org.example.ai.actions;

import com.dingtalk.open.app.api.OpenDingTalkClient;
import com.dingtalk.open.app.api.OpenDingTalkStreamClientBuilder;
import com.dingtalk.open.app.api.security.AuthClientCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author feiyin
 * @date 2024/3/26
 */
@Configuration
public class StreamClientConfigure {

    @Value("${dingtalk.app.client-id}")
    private String clientId;

    @Value("${dingtalk.app.client-secret}")
    private String clientSecret;

    @Bean(initMethod = "start")
    public OpenDingTalkClient configure(StreamActionsDispatcher dispatcher) {
        return OpenDingTalkStreamClientBuilder.custom()
                .credential(new AuthClientCredential(clientId, clientSecret))
                .registerCallbackListener("/v1.0/graph/api/invoke", dispatcher)
                .build();
    }

}
