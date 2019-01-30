package com.mm.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class ClientConfig implements CommandLineRunner {

    ExecutorService es = Executors.newFixedThreadPool(1);

    public static WebSocketClient client;

    public static void sendText(String text){
        client.send("{\"msg\":\""+text+"\",\"toUser\":\"\",\"type\":0}");
    }

    @Override
    public void run(String... args) throws Exception {
        es.submit(() -> connnection());
    }

    private void connnection(){
        String nickname = "haha";
        try {
            client = new WebSocketClient(new URI("ws://localhost:8080/websocket/" + nickname), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("握手成功");
                }

                @Override
                public void onMessage(String message) {
                    log.info("收到消息==========" + message);
                    if (message.equals("over")) {
                        client.close();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("链接已关闭");
                }

                @Override
                public void onError(Exception ex) {
                    log.info("发生错误已关闭");
                }
            };
            client.connect();
            log.info("draft:{}", client.getDraft());
            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                log.info("正在连接...");
            }
            //连接成功,发送信息
            client.send("{\"msg\":\"哈喽,连接一下啊\",\"toUser\":\"\",\"type\":0}");
            /*while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(client.getReadyState());
            }*/
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
