package com.example.event;

import com.dingtalk.open.app.api.GenericEventListener;
import com.dingtalk.open.app.api.message.GenericOpenDingTalkEvent;
import com.dingtalk.open.app.stream.protocol.event.EventAckStatus;

public class EventConsumer implements GenericEventListener {
    @Override
    public EventAckStatus onEvent(GenericOpenDingTalkEvent event) {
        System.out.println(String.format("received event,\n" +
                        "  delay=%dms,\n" +
                        "  eventType=%s,\n" +
                        "  eventId=%s,\n" +
                        "  eventBornTime=%s,\n" +
                        "  eventCorpId=%s,\n" +
                        "  eventUnifiedAppId=%s,\n" +
                        "  data=%s\n",
                (System.currentTimeMillis() - event.getEventBornTime().longValue()),
                event.getEventType(),
                event.getEventId(),
                event.getEventBornTime(),
                event.getEventCorpId(),
                event.getEventUnifiedAppId(),
                event.getData()));
        return EventAckStatus.SUCCESS;
    }
}
