package com.mdy.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;


@Slf4j
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("----onOpen----");
    }

    @Override
    public void onMessage(String s) {
        log.info("接收到服务器数据：{}",s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("------ MyWebSocket onClose ------{}",s);
    }

    @Override
    public void onError(Exception e) {
        log.info("------ MyWebSocket onError ------{}",e);
    }
}
