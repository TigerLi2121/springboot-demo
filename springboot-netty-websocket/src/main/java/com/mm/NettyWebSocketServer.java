package com.mm;

import com.mm.handler.WebSocketChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class NettyWebSocketServer implements CommandLineRunner {
    static ExecutorService es = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
            new DefaultThreadFactory("netty-thread"));

    public static void main(String[] args) {
        NettyWebSocketServer server = new NettyWebSocketServer();
        es.submit(() -> server.run());
    }

    @Override
    public void run(String... args) throws Exception {
        es.submit(() -> run());
    }

    /**
     * 开启服务端
     */
    private void run() {
        log.info("正在启动websocket服务器");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new WebSocketChannelHandler());
            Channel channel = bootstrap.bind(8888).sync().channel();
            log.info("websocket服务器启动成功：{}", channel);
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("websocket服务器运行出错：", e);
        } finally {
            eventLoopGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            log.info("websocket服务器已关闭");
        }
    }
}
