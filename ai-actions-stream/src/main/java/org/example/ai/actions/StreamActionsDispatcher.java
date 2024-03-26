package org.example.ai.actions;

import com.dingtalk.open.app.api.callback.OpenDingTalkCallbackListener;
import com.dingtalk.open.app.api.graph.GraphAPIMethod;
import com.dingtalk.open.app.api.graph.GraphAPIRequest;
import com.dingtalk.open.app.api.graph.GraphAPIResponse;
import com.dingtalk.open.app.api.graph.StatusLine;
import com.dingtalk.open.app.api.util.GraphUtils;
import org.springframework.stereotype.Service;
import shade.io.netty.handler.codec.http.QueryStringDecoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feiyin
 * @date 2024/3/26
 */
@Service
public class StreamActionsDispatcher implements OpenDingTalkCallbackListener<GraphAPIRequest, GraphAPIResponse> {

    @Override
    public GraphAPIResponse execute(GraphAPIRequest request) {
        String path = request.getRequestLine().getPath();
        //判断path和请求方法
        if (path.equalsIgnoreCase("/v1/actions/example/weather/get") && request.getRequestLine().getMethod() == GraphAPIMethod.GET) {
            //从query中获取参数
            QueryStringDecoder decoder = new QueryStringDecoder(request.getRequestLine().getUri().toString());
            String date = decoder.parameters().get("date") != null ? decoder.parameters().get("date").get(0) : null;
            String location = decoder.parameters().get("location") != null ? decoder.parameters().get("location").get(0) : null;
            return GraphUtils.successJson(getWeather(date, location));
        }
        return GraphUtils.failed(StatusLine.NOT_FOUND);
    }


    /**
     * @param date
     * @param location
     * @return
     */
    public Map<String, Object> getWeather(String date, String location) {

        Map<String, Object> result = new HashMap<>();
        if (location == null || location.isEmpty()) {
            location = "杭州";
        }
        if (date == null || date.isEmpty()) {
            date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        }
        result.put("location", location);
        result.put("dateStr", date);
        result.put("text", "晴天");
        result.put("temperature", 22);
        result.put("humidity", 65);
        result.put("wind_direction", "东南风");
        return result;
    }
}
