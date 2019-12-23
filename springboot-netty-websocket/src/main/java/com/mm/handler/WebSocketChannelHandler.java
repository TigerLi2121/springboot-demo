package com.mm.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                //设置log监听器，并且日志级别为debug，方便观察运行流程
                .addLast("logging", new LoggingHandler("DEBUG"))
                //编解码http请求
                .addLast("http-codec", new HttpServerCodec())
                //聚合解码HttpRequest/HttpContent/LastHttpContent到FullHttpRequest
                //保证接收的Http请求的完整性
                .addLast("aggregator", new HttpObjectAggregator(65536))
                //用于大数据的分区传输
                .addLast("http-chunked", new ChunkedWriteHandler())
                //自定义的业务handler
                .addLast("handler", new WebSocketHandler());
    }
}
