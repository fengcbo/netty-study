package com.shiluns.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author fengchuanbo
 */
public class ProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setAddress("北京").setAge(25).setName("张三").build();
        System.out.println(student);
        byte[] data = student.toByteArray();
        DataInfo.Student stu = DataInfo.Student.parseFrom(data);
        System.out.println(stu);
    }
}
