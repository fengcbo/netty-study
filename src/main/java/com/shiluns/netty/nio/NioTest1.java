package com.shiluns.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author fengchuanbo
 */
public class NioTest1 {

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            intBuffer.put(secureRandom.nextInt());
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
