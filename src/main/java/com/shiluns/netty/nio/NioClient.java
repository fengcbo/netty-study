package com.shiluns.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fengchuanbo
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                try {
                    if (selectionKey.isConnectable()){
                        SocketChannel client = (SocketChannel)selectionKey.channel();
                        // client.configureBlocking(false);
                        if (client.isConnectionPending()){
                            client.finishConnect();
                            client.register(selector, SelectionKey.OP_READ);
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            buffer.put((LocalDateTime.now() + " 链接成功").getBytes());
                            buffer.flip();
                            client.write(buffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.submit(() -> {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                while (true){
                                    try{
                                        writeBuffer.clear();
                                        String data = reader.readLine();
                                        writeBuffer.put(data.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }else if (selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = client.read(buffer);
                        if (read == -1){
                            client.close();
                            continue;
                        }else if(read > 0){
                            String msg = new String(buffer.array(), 0, read);
                            System.out.println(msg);
                        }
                    }
                }catch (Exception e){

                }finally {
                    iterator.remove();
                }
            }
        }
    }

}
