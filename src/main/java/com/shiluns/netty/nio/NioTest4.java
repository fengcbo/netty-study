package com.shiluns.netty.nio;

import java.nio.ByteBuffer;

/**
 * @author fengchuanbo
 */
public class NioTest4 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.putInt(15);
        buffer.putLong(1000L);
        buffer.putDouble(13.3);
        buffer.putChar('你');
        buffer.putShort((short)1);

        buffer.flip();


        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
