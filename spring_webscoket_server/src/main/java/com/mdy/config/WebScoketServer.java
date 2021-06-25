package com.mdy.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint("/webscoket")
@Component
public class WebScoketServer {

    private Session session;

    private String name;

    private static ConcurrentHashMap<String, WebScoketServer> webScoketSet = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String name) {
        this.session = session;
        this.name = name;
        webScoketSet.put(name, this);
        log.info("[WebSocket] 连接成功，当前连接人数为：={}", webScoketSet.size());
    }

    @OnClose
    public void onClose() {
        webScoketSet.remove(this.name);
        log.info("[WebSocket] 退出成功，当前连接人数为：={}", webScoketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("[WebSocket] 收到消息：{}", message);
        //判断是否需要指定发送，具体规则自定义
        if (message.indexOf("TOUSER") == 0) {
            String name = message.substring(message.indexOf("TOUSER") + 6, message.indexOf(";"));
            AppointSending(name, message.substring(message.indexOf(";") + 1, message.length()));
        } else {
            GroupSending(message);
        }
    }

    public void GroupSending(String message) {
        for (String name : webScoketSet.keySet()) {
            try {
                webScoketSet.get(name).session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     *
     * @param name
     * @param message
     */
    public void AppointSending(String name, String message) {
        try {
            webScoketSet.get(name).session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
