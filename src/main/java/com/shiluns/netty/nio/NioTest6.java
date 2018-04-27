package com.shiluns.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author fengchuanbo
 */
public class NioTest6 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("input.txt", "rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        mappedBuffer.put(0, (byte)'a');

        mappedBuffer.put(3, (byte)'a');
        file.close();
    }
}
