package com.shiluns.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的 Scattering 和 Gathering
 * Java NIO开始支持scatter/gather，scatter/gather用于描述从Channel（译者注：Channel在中文经常翻译为通道）中读取或者写入到Channel的操作。
 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
 * 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。
 * @author fengchuanbo
 */
public class NioTest7 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8899));

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffer = new ByteBuffer[3];
        buffer[0] = ByteBuffer.allocate(2);
        buffer[1] = ByteBuffer.allocate(3);
        buffer[2] = ByteBuffer.allocate(4);
        while (true) {
            SocketChannel accept = serverSocketChannel.accept();
            int bytesRead = 0;
            while (bytesRead < messageLength){
                long read = accept.read(buffer);
                bytesRead += read;
                System.out.println("bytesRead=" + bytesRead);
                Arrays.asList(buffer).stream().map(item -> "position=" + item.position() + "; limit=" + item.limit()).forEach(System.out::println);
            }

            Arrays.asList(buffer).forEach(item -> item.flip());

            long byteWrite = 0;
            while (byteWrite < messageLength){
                long r = accept.write(buffer);
                byteWrite += r;
            }
            Arrays.asList(buffer).forEach(item -> item.clear());

            System.out.println("bytesRead=" + bytesRead + ";byteWrite=" + byteWrite + ";messageLength=" + messageLength);
        }
    }
}
