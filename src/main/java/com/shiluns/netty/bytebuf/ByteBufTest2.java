package com.shiluns.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author fengchuanbo
 * @date 2018/6/9
 */
public class ByteBufTest2 {

    public static void main(String[] args) {
        Charset utf8 = Charset.forName("utf-8");
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", utf8);
        if (byteBuf.hasArray()){
            byte[] array = byteBuf.array();
//            byte[] bytes = "hello world".getBytes(utf8);
//            System.out.println(Arrays.toString(array));
//            System.out.println(Arrays.toString(bytes));

            System.out.println(new String(array, utf8));

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readableBytes());

//            for (int i = 0; i < byteBuf.readableBytes(); i++) {
//                System.out.println((char) byteBuf.getByte(i));
//            }
        }
    }
}
