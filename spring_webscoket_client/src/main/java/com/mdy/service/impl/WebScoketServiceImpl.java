package com.mdy.service.impl;

import com.mdy.service.WebScoketService;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebScoketServiceImpl implements WebScoketService {

    @Autowired
    private WebSocketClient webSocketClient;

    @Override
    public void groupSending(String message) {
        webSocketClient.send(message);
    }

    @Override
    public void appointSending(String name, String message) {
        webSocketClient.send("TOUSER" + name + ";" + message);
    }
}
