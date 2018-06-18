package com.shiluns.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fengchuanbo
 * @date 2018/6/18
 */
public class CodecServerHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("server: " + msg);
        ctx.writeAndFlush(987654321L);
    }
}
