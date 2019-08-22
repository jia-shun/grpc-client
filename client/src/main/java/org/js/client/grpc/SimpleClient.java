package org.js.client.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author JiaShun
 * @date 2018/8/11 12:11
 */
public class SimpleClient {

    private final ManagedChannel channel;
    private final SimpleGrpc.SimpleBlockingStub blockingStub;

    public SimpleClient(String host, int port){
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    private SimpleClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = SimpleGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String sayHello(String name) {
        SimpleOuterClass.HelloRequest req = SimpleOuterClass.HelloRequest.newBuilder().setName(name).build();
        SimpleOuterClass.HelloReplay replay = blockingStub.sayHello(req);
        return replay.getMessage();
    }
}
