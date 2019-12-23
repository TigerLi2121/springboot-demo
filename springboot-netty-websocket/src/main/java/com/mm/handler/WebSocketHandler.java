package com.mm.handler;

import com.mm.config.NettyConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/webSocket";

    /**
     * 服务端处理客户端websocket请求的核心方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(ctx, (FullHttpRequest) msg);
            // WebSocket接入
        } else if (msg instanceof WebSocketFrame) {
            handWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * 处理客户端向服务端发起http握手请求业务
     *
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        //判断是否http握手请求
        if (!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = factory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 服务端想客户端发送响应消息
     *
     * @param ctx
     * @param req
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = ctx.channel().writeAndFlush(res);
        if (res.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }

    /**
     * 处理客户端与服务端之间的websocket业务
     *
     * @param ctx
     * @param frame
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否是关闭websocket的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否是二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            log.debug("不支持二进制消息");
            throw new RuntimeException(this.getClass().getName());
        }
        //返回应答消息
        //获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame) frame).text();
        log.debug("服务端收到客户端的消息：" + request);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(ctx.channel().id() + ":" + request);
        //服务端向每个连接上来的客户端发送消息
        NettyConfig.group.writeAndFlush(textWebSocketFrame);
    }

    /**
     * 客户端加入连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端加入连接：" + ctx.channel());
        NettyConfig.group.add(ctx.channel());
    }

    /**
     * 客户端断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端断开连接：" + ctx.channel());
        NettyConfig.group.remove(ctx.channel());
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 工程出现异常的时候调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
