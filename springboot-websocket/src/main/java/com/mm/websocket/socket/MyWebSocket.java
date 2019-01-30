package com.mm.websocket.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.websocket.entity.SocketMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket具体实现类
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{nickname}")
@Component
public class MyWebSocket {

    // 用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //昵称
    private String nickname;

    //用来记录sessionId和该session进行绑定
    private static Map<String, Session> map = new HashMap<String, Session>();

    /**
     * 连接建立成功调用
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname) {
        this.session = session;
        this.nickname = nickname;
        map.put(session.getId(), session);
        webSocketSet.add(this); // 加入set中
        log.info("有新用户({})加入！当前在线人数为：{}", nickname, webSocketSet.size());
        broadcast(nickname + "上线了,（我的频道号是" + session.getId() + "）");
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        map.remove(session.getId());
        log.info("{}已关闭连接！当前在线人数为：{}", nickname, webSocketSet.size());
    }

    /**
     * 收到客户端端调用
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("nickname") String nickname) {
        log.info("来自客户端的消息：{}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        SocketMsg socketMsg;
        try {
            socketMsg = objectMapper.readValue(message, SocketMsg.class);
            if (1 == socketMsg.getType()) {
                //单聊需要找到发送者和接受者
                socketMsg.setFromUser(session.getId());
                Session fromSession = map.get(socketMsg.getFromUser());
                Session toSession = map.get(socketMsg.getToUser());
                if (toSession != null) {
                    // 发送消息
                    fromSession.getAsyncRemote().sendText(nickname + "：" + socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(nickname + "：" + socketMsg.getMsg());
                } else {
                    // 发送给发送者.
                    fromSession.getAsyncRemote().sendText("系统消息：对方不在线或者您输入的频道号不对");
                }
            } else {
                //群发消息
                broadcast(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发生错误", throwable);
    }

    /**
     * 自定义群发消息
     *
     * @param message
     */
    private void broadcast(String message) {
        for (MyWebSocket item : webSocketSet) {
            item.session.getAsyncRemote().sendText(message);  //异步发送消息
        }
    }
}
