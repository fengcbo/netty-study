package com.shiluns.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fengchuanbo
 */
public class NioServer {

    public static final ConcurrentHashMap<String, SocketChannel> CLIENTS = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        // 1. 获取ServerSocketChannel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 2. 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        // 3. 获取ServerSocket
        ServerSocket serverSocket = socketChannel.socket();
        // 4. 绑定端口到ServerSocket
        serverSocket.bind(new InetSocketAddress(8899));

        // 5. 获取Selector
        Selector selector = Selector.open();
        // 6. 将ServerSocketChannel注册到Selector上
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                // 7. 监听事件
                selector.select();
                // 8. 获取事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                // 9. 编译事件进行处理
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    try {
                        SelectionKey selectionKey = iterator.next();
                        // 10. 如果链接建立
                        if (selectionKey.isAcceptable()){
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                            // 11. 获取客户端Channel
                            SocketChannel client = serverSocketChannel.accept();
                            // 12. 客户端Channel设置为非阻塞的
                            client.configureBlocking(false);
                            // 13. 将客户端Channel注册到Selector上，关注读事件
                            client.register(selector, SelectionKey.OP_READ);

                            CLIENTS.put(UUID.randomUUID().toString(), client);
                        // 14. 如果有数据进来
                        }else if (selectionKey.isReadable()){
                            // 15. 获取客户端Channel
                            SocketChannel client = (SocketChannel) selectionKey.channel();

                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int count = client.read(buffer);
                            if (count == -1){
                                String uuid = CLIENTS.searchEntries(100, entry -> client.isOpen() && client == entry.getValue() ? entry.getKey() : null);
                                if (uuid != null) {
                                    CLIENTS.remove(uuid);
                                }
                                client.close();
                                continue;
                            }
                            if (count > 0){
                                buffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String read = String.valueOf(charset.decode(buffer).array());
                                System.out.println(client + ":" + read);

                                String uuid = CLIENTS.searchEntries(100, entry -> client.isOpen() && client == entry.getValue() ? entry.getKey() : null);

                                for (Map.Entry<String, SocketChannel> entry : CLIENTS.entrySet()){
                                    if (!entry.getValue().isOpen()){
                                        CLIENTS.remove(entry.getKey(),entry.getValue());
                                        continue;
                                    }
                                    buffer.clear();
                                    buffer.put((uuid + ":" + read).getBytes(charset));
                                    buffer.flip();
                                    entry.getValue().write(buffer);
                                }
                            }
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    } finally {
                        // 最后删除已处理的事件
                        iterator.remove();
                    }

                }
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }
}
