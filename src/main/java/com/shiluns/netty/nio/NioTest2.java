package com.shiluns.netty.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fengchuanbo
 */
public class NioTest2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("settings.gradle");
        FileChannel channel = fis.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(512);
        channel.read(bb);

        bb.flip();

        while (bb.hasRemaining()){
            System.out.println((char)bb.get());;
        }
    }
}
