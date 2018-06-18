package com.shiluns.netty.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author fengchuanbo
 * @date 2018/6/18
 */
public class CodecServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
         pipeline.addLast(new CustomCodec());
//        pipeline.addLast(new CustomEncoder());
//        pipeline.addLast(new CustomDecoder());
        pipeline.addLast(new CodecServerHandler());
    }
}
