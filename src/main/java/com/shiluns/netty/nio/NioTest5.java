package com.shiluns.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fengchuanbo
 */
public class NioTest5 {

    public static void main(String[] args) throws IOException {
        // System.out.println(System.getProperty("user.dir"));
        FileInputStream fis = new FileInputStream("input.txt");
        FileOutputStream fos = new FileOutputStream("output.txt");

        FileChannel inputChannel = fis.getChannel();
        FileChannel outputChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true){
            // limit=capacity position=0  => limit=10 position=0
            buffer.clear();
            // limit=capacity position=21
            int read = inputChannel.read(buffer);
            System.out.println(read);
            if (-1 == read) {
                break;
            }
            // limit=position=21 position=0
            buffer.flip();

            // limit=21 position=21
            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
