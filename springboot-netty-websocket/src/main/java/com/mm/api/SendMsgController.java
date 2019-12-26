package com.mm.api;


import com.mm.config.NettyConfig;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SendMsgController {

    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    @GetMapping("/sendMsg")
    public ResponseEntity<String> sendMsg(String msg, String username) {
        log.debug("sendMsg msg:{}, username:{}", msg, username);
        if (username != null) {
            if (NettyConfig.userMap.get(username) == null) {
                log.debug("{}:不在线", username);
                return ResponseEntity.ok(username + ":不在线");
            }
            NettyConfig.userMap.get(username).channel().writeAndFlush(new TextWebSocketFrame(msg));
        } else {
            //服务端向每个连接上来的客户端发送消息
            NettyConfig.group.writeAndFlush(new TextWebSocketFrame(msg));
        }
        return ResponseEntity.ok("success");
    }

}
