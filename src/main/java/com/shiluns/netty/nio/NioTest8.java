package com.shiluns.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author fengchuanbo
 */
public class NioTest8 {

    public static void main(String[] args) throws IOException {
        int[] ports = {5000,5001,5002,5003,5004};

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            // serverSocketChannel.bind(new InetSocketAddress(ports[i]));
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(ports[i]));

            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口：" + ports[i]);
        }

        while (true){
            int select = selector.select();
            System.out.println("numbers: " + select);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys:" + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获得客户端连接：" + channel);
                }else if (selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    int byteRead = 0;
                    while (true){
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        int read = channel.read(buffer);
                        if (read <= 0){
                            break;
                        }
                        buffer.flip();
                        channel.write(buffer);
                        byteRead += read;
                    }
                    System.out.println("获取到 " + byteRead + ",来自于：" + channel);
                    iterator.remove();
                }
            }
        }
    }
}
