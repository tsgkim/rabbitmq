package com.tsgkim.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tsgkim.common.AMQConnectionFactory;

/**
 * @Description: 客户端
 * @author: shiguang.tu
 * @create: 2017/12/20 下午2:27
 */
public class Client {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        Connection connection = AMQConnectionFactory.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
