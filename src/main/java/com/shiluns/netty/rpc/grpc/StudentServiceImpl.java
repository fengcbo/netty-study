package com.shiluns.netty.rpc.grpc;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自己的服务端业务类
 * @author fengchuanbo
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    private static final List<StudentResponse> DATA = new ArrayList<>();

    static {
        DATA.add(StudentResponse.newBuilder().setName("zhangsan").setAge(26).build());
        DATA.add(StudentResponse.newBuilder().setName("lisi").setAge(23).build());
        DATA.add(StudentResponse.newBuilder().setName("wangwu").setAge(18).build());
        DATA.add(StudentResponse.newBuilder().setName("zhaoliu").setAge(22).build());
        DATA.add(StudentResponse.newBuilder().setName("fengchuanbo").setAge(26).build());
        DATA.add(StudentResponse.newBuilder().setName("testest").setAge(26).build());
    }

    @Override
    public void getByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端请求：" + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        DATA.stream().filter(item -> item.getAge() == request.getAge()).forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getByAges(StreamObserver<StudentResponseList> responseObserver) {

        return new StreamObserver<StudentRequest>() {
            private List<Integer> list = new ArrayList<>();
            @Override
            public void onNext(StudentRequest value) {
                int age = value.getAge();
                list.add(age);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                List<StudentResponse> collect = DATA.stream().filter(item -> list.contains(item.getAge())).collect(Collectors.toList());
                StudentResponseList responseList = StudentResponseList.newBuilder().addAllStudent(collect).build();
                responseObserver.onNext(responseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println("收到客户端请求：" + value.getRequestInfo());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo("1").build());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo("2").build());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo("3").build());
                responseObserver.onCompleted();
            }
        };
    }
}
