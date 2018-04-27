package com.shiluns.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Arrays;

/**
 * @author fengchuanbo
 */
public class TestServer {

    /**
     * netty使用的基本流程
     * 1. 启动一个Bootstrap服务器，Bootstrap里面会关联两个事件循环组(bossGroup、workerGroup)
     * 2. bossGroup 用于获取链接
     * 3. workerGroup 用于处理链接
     * 4. 服务器启动时关联一个处理器(ChannelHandler)
     * 5. 在ChannelHandler中添加若干个自定义或者netty提供的处理器
     * 6. 在自定义的处理器(ChannelHandler)中返回响应
     * 7. 整个请求处理完毕
     * @param args
     */
    public static void main(String[] args) {
        // bossGroup 不断的从客户端接受链接，但不会做任何处理，直接转给 workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // workerGroup 从 bossGroup 接收到链接，然后获取参数等进行处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TestServerInitializer());
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
