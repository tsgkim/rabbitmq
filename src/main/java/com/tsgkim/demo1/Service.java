package com.tsgkim.demo1;

import com.rabbitmq.client.*;
import com.tsgkim.common.AMQConnectionFactory;

import java.io.IOException;

/**
 * @Description: 服务端
 * @author: shiguang.tu
 * @create: 2017/12/20 下午3:51
 */
public class Service {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        Connection connection = AMQConnectionFactory.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
