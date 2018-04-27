package com.shiluns.netty.rpc.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fengchuanbo
 */
public class GrpcClient {

    private StudentServiceGrpc.StudentServiceBlockingStub stub;
    private ManagedChannel channel;

    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build());
    }

    GrpcClient(ManagedChannel channel){
        this.channel = channel;
        stub = StudentServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {
        MyRequest request = MyRequest.newBuilder().setUsername(name).build();
        MyResponse response;
        try {
            response = stub.getByUsername(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return;
        }
    }

    public void getByAge(int age){
        StudentRequest request = StudentRequest.newBuilder().setAge(age).build();
        Iterator<StudentResponse> response;
        try {
            response = stub.getByAge(request);
            response.forEachRemaining(System.out::println);
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
            return;
        }
    }

    public void getByAges(String host, int port,List<Integer> ages){

        StreamObserver<StudentResponseList> responseObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentList().forEach(System.out::println);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        StreamObserver<StudentRequest> request = StudentServiceGrpc.newStub(channel).getByAges(responseObserver);
        ages.stream().forEach(item -> request.onNext(StudentRequest.newBuilder().setAge(item).build()));
        request.onCompleted();
    }


    public void biTask(String host, int port,List<String> info){
        StreamObserver<StreamResponse> responseObserver = new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println("收到服务端响应：" + value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        };
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        StreamObserver<StreamRequest> request = StudentServiceGrpc.newStub(channel).biTalk(responseObserver);
        info.stream().forEach(item -> request.onNext(StreamRequest.newBuilder().setRequestInfo(item).build()));
        request.onCompleted();
    }


    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("localhost", 50051);
        try {
            // client.getByAge(26);
            // client.getByAges("localhost", 50051, Arrays.asList(23,26));
            client.biTask("localhost", 50051, Arrays.asList("feng","chuanbo"));
            Thread.sleep(5000);
        } finally {
            client.shutdown();
        }
    }

}
