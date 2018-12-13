package com.atjhoendz.catcare;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;


public class Events {
    public String type;
    public String replyToken;
    public Source source;
    public long timestamp;
    public MessageEvent<TextMessageContent> txtmessage;
}
