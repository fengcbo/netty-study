package com.shiluns.netty.rpc.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.shiluns.netty.rpc.grpc.StudentService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.shiluns.netty.rpc.grpc.MyRequest,
      com.shiluns.netty.rpc.grpc.MyResponse> METHOD_GET_BY_USERNAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.shiluns.netty.rpc.grpc.StudentService", "GetByUsername"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.MyRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.MyResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.shiluns.netty.rpc.grpc.StudentRequest,
      com.shiluns.netty.rpc.grpc.StudentResponse> METHOD_GET_BY_AGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "com.shiluns.netty.rpc.grpc.StudentService", "GetByAge"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.StudentRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.StudentResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.shiluns.netty.rpc.grpc.StudentRequest,
      com.shiluns.netty.rpc.grpc.StudentResponseList> METHOD_GET_BY_AGES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "com.shiluns.netty.rpc.grpc.StudentService", "GetByAges"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.StudentRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.StudentResponseList.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.shiluns.netty.rpc.grpc.StreamRequest,
      com.shiluns.netty.rpc.grpc.StreamResponse> METHOD_BI_TALK =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "com.shiluns.netty.rpc.grpc.StudentService", "BiTalk"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.StreamRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.shiluns.netty.rpc.grpc.StreamResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getByUsername(com.shiluns.netty.rpc.grpc.MyRequest request,
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BY_USERNAME, responseObserver);
    }

    /**
     */
    public void getByAge(com.shiluns.netty.rpc.grpc.StudentRequest request,
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BY_AGE, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentRequest> getByAges(
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_GET_BY_AGES, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_BI_TALK, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_BY_USERNAME,
            asyncUnaryCall(
              new MethodHandlers<
                com.shiluns.netty.rpc.grpc.MyRequest,
                com.shiluns.netty.rpc.grpc.MyResponse>(
                  this, METHODID_GET_BY_USERNAME)))
          .addMethod(
            METHOD_GET_BY_AGE,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.shiluns.netty.rpc.grpc.StudentRequest,
                com.shiluns.netty.rpc.grpc.StudentResponse>(
                  this, METHODID_GET_BY_AGE)))
          .addMethod(
            METHOD_GET_BY_AGES,
            asyncClientStreamingCall(
              new MethodHandlers<
                com.shiluns.netty.rpc.grpc.StudentRequest,
                com.shiluns.netty.rpc.grpc.StudentResponseList>(
                  this, METHODID_GET_BY_AGES)))
          .addMethod(
            METHOD_BI_TALK,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.shiluns.netty.rpc.grpc.StreamRequest,
                com.shiluns.netty.rpc.grpc.StreamResponse>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getByUsername(com.shiluns.netty.rpc.grpc.MyRequest request,
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BY_USERNAME, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getByAge(com.shiluns.netty.rpc.grpc.StudentRequest request,
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_BY_AGE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentRequest> getByAges(
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_GET_BY_AGES, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_BI_TALK, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.shiluns.netty.rpc.grpc.MyResponse getByUsername(com.shiluns.netty.rpc.grpc.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BY_USERNAME, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.shiluns.netty.rpc.grpc.StudentResponse> getByAge(
        com.shiluns.netty.rpc.grpc.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_BY_AGE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.shiluns.netty.rpc.grpc.MyResponse> getByUsername(
        com.shiluns.netty.rpc.grpc.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BY_USERNAME, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BY_USERNAME = 0;
  private static final int METHODID_GET_BY_AGE = 1;
  private static final int METHODID_GET_BY_AGES = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BY_USERNAME:
          serviceImpl.getByUsername((com.shiluns.netty.rpc.grpc.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.MyResponse>) responseObserver);
          break;
        case METHODID_GET_BY_AGE:
          serviceImpl.getByAge((com.shiluns.netty.rpc.grpc.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getByAges(
              (io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StudentResponseList>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<com.shiluns.netty.rpc.grpc.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class StudentServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.shiluns.netty.rpc.grpc.StudentInfo.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceDescriptorSupplier())
              .addMethod(METHOD_GET_BY_USERNAME)
              .addMethod(METHOD_GET_BY_AGE)
              .addMethod(METHOD_GET_BY_AGES)
              .addMethod(METHOD_BI_TALK)
              .build();
        }
      }
    }
    return result;
  }
}
