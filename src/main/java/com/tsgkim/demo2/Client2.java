package com.tsgkim.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.tsgkim.common.AMQConnectionFactory;
import org.apache.commons.lang.StringUtils;

import java.util.Scanner;

/**
 * @Description:
 * @author: shiguang.tu
 * @create: 2017/12/20 下午4:04
 */
public class Client2 {
    private static final String TASK_QUEUE_NAME = "task_my_queue";

    public static void main(String[] argv) throws Exception {
        Connection connection = AMQConnectionFactory.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        System.out.println("请输入字符串：");
        Scanner scanner = new Scanner(System.in);
        // 获取输入的字符串
        String msg = getMessage(scanner.nextLine());
        System.out.println("输入的字符串为：" + msg);

        channel.basicPublish("", TASK_QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                msg.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + msg + "'");

        channel.close();
        connection.close();
    }

    private static String getMessage(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            return msg;
        } else {
            return "Hello World!";
        }

    }

}
