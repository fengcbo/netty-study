package com.shiluns.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;


/**
 * @author fengchuanbo
 */
public class WebsocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final HandlerEventExecutorGroup eventExecutorGroup = new HandlerEventExecutorGroup(500, 1500, 60 * 1000);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        // Handler的泛型将变为 FullHttpRequest 或 FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(8096));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(eventExecutorGroup,new WebsocketServerHandler());
    }
}
