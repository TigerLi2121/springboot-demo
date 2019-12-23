package com.mm;

import com.mm.handler.WebSocketChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyWebSocketApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyWebSocketApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //开启服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup,workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new WebSocketChannelHandler());
            System.out.println("服务端开启等待客户端连接..");
            Channel channel = serverBootstrap.bind(8888).sync().channel();
            channel.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //退出程序
            eventLoopGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
