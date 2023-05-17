package ba.tim2.systemevents.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: kino.proto")
public final class eventGrpc {

  private eventGrpc() {}

  public static final String SERVICE_NAME = "event";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ba.tim2.systemevents.grpc.LogRequest,
      ba.tim2.systemevents.grpc.APIResponse> getLogMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "log",
      requestType = ba.tim2.systemevents.grpc.LogRequest.class,
      responseType = ba.tim2.systemevents.grpc.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ba.tim2.systemevents.grpc.LogRequest,
      ba.tim2.systemevents.grpc.APIResponse> getLogMethod() {
    io.grpc.MethodDescriptor<ba.tim2.systemevents.grpc.LogRequest, ba.tim2.systemevents.grpc.APIResponse> getLogMethod;
    if ((getLogMethod = eventGrpc.getLogMethod) == null) {
      synchronized (eventGrpc.class) {
        if ((getLogMethod = eventGrpc.getLogMethod) == null) {
          eventGrpc.getLogMethod = getLogMethod = 
              io.grpc.MethodDescriptor.<ba.tim2.systemevents.grpc.LogRequest, ba.tim2.systemevents.grpc.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "event", "log"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ba.tim2.systemevents.grpc.LogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ba.tim2.systemevents.grpc.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new eventMethodDescriptorSupplier("log"))
                  .build();
          }
        }
     }
     return getLogMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static eventStub newStub(io.grpc.Channel channel) {
    return new eventStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static eventBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new eventBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static eventFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new eventFutureStub(channel);
  }

  /**
   */
  public static abstract class eventImplBase implements io.grpc.BindableService {

    /**
     */
    public void log(ba.tim2.systemevents.grpc.LogRequest request,
        io.grpc.stub.StreamObserver<ba.tim2.systemevents.grpc.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLogMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ba.tim2.systemevents.grpc.LogRequest,
                ba.tim2.systemevents.grpc.APIResponse>(
                  this, METHODID_LOG)))
          .build();
    }
  }

  /**
   */
  public static final class eventStub extends io.grpc.stub.AbstractStub<eventStub> {
    private eventStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventStub(channel, callOptions);
    }

    /**
     */
    public void log(ba.tim2.systemevents.grpc.LogRequest request,
        io.grpc.stub.StreamObserver<ba.tim2.systemevents.grpc.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class eventBlockingStub extends io.grpc.stub.AbstractStub<eventBlockingStub> {
    private eventBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventBlockingStub(channel, callOptions);
    }

    /**
     */
    public ba.tim2.systemevents.grpc.APIResponse log(ba.tim2.systemevents.grpc.LogRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class eventFutureStub extends io.grpc.stub.AbstractStub<eventFutureStub> {
    private eventFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private eventFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected eventFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new eventFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ba.tim2.systemevents.grpc.APIResponse> log(
        ba.tim2.systemevents.grpc.LogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOG = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final eventImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(eventImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOG:
          serviceImpl.log((ba.tim2.systemevents.grpc.LogRequest) request,
              (io.grpc.stub.StreamObserver<ba.tim2.systemevents.grpc.APIResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class eventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    eventBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ba.tim2.systemevents.grpc.Kino.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("event");
    }
  }

  private static final class eventFileDescriptorSupplier
      extends eventBaseDescriptorSupplier {
    eventFileDescriptorSupplier() {}
  }

  private static final class eventMethodDescriptorSupplier
      extends eventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    eventMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (eventGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new eventFileDescriptorSupplier())
              .addMethod(getLogMethod())
              .build();
        }
      }
    }
    return result;
  }
}
