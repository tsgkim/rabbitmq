package com.tsgkim.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description: 连接 AMQ 工厂类
 * @author: shiguang.tu
 * @create: 2017/12/20 下午3:43
 */
public class AMQConnectionFactory {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("118.123.6.216");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(11067);
        factory.setVirtualHost("/");
        return factory.newConnection();
    }
}
