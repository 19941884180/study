package com.mdy.controller;

import com.mdy.service.WebScoketService;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class WebSocketController {

    @Autowired
    private WebScoketService webScoketService;

    @RequestMapping("/subscribe")
    public String subscribe() {
        webScoketService.groupSending("hello sever，i want subscribe data A");
        return "发送订阅成功！！！";
    }

    public static void main(String[] args) {
        try {
            URI serverUri = new URI("ws://localhost:8081/spring_webscoket_server/websocket");
            WebSocketClient client = new WebSocketClient(serverUri) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println(handshakedata.getHttpStatus());
                }

                @Override
                public void onMessage(String message) {

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            client.connect();
//            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
//                System.out.println("还没有打开");
//            }
            System.out.println("建立websocket连接");
            client.send("asd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
