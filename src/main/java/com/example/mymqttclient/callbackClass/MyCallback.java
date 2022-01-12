package com.example.mymqttclient.callbackClass;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("与代理broker的连接丢失了！");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("消息已到达！主题topic是："+topic);
        System.out.println("消息已到达！负载payload是："+new String(message.getPayload()));
        System.out.println("消息已到达！传输质量等级qos是："+message.getQos());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("传送完成！状态是："+token.isComplete());
    }
}
