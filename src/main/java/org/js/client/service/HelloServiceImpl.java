package org.js.client.service;

import org.js.client.grpc.SimpleClient;
import org.js.client.grpc.SimpleGrpc;
import org.js.client.grpc.SimpleOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author JiaShun
 * @date 2018/8/10 22:22
 */
@Service
public class HelloServiceImpl implements IHelloService{
    private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Value("${gRPC.host}")
    private String host;
    @Value("${gRPC.port}")
    private int port;

    @Override
    public String sayHello(String name) {
        SimpleClient client = new SimpleClient(host,port);
        String replay = client.sayHello(name);
        try {
            client.shutdown();
        } catch (InterruptedException e) {
            logger.error("channel关闭异常：err={}",e.getMessage());
        }
        return replay;
    }

}
