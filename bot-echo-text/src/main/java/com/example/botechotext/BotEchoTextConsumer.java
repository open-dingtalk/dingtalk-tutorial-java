package com.example.botechotext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import org.springframework.stereotype.Component;

@Component
public class BotEchoTextConsumer implements OpenDingTalkCallbackListener<JSONObject, JSONObject> {

    @Override
    public JSONObject execute(JSONObject msg) {
        String text = msg.getJSONObject("text").getString("content");
        return new JSONObject();
    }
}
