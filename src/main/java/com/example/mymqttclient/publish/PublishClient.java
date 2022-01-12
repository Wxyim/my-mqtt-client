package com.example.mymqttclient.publish;

import com.example.mymqttclient.callbackClass.MyCallback;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_3_1_1;

public class PublishClient {

    MqttClient pClient;
    MqttConnectOptions options;

    MqttTopic mqttTopic;
    String topic = "topic37";

    MqttMessage mqttMessage;

    public PublishClient() throws MqttException {
        pClient = new MqttClient("tcp://broker.emqx.io:1883", "publish1", new MemoryPersistence());
        connectToBroker();
    }

    private void connectToBroker() throws MqttException {
        options = new MqttConnectOptions();
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(60);
        options.setCleanSession(true);
        options.setAutomaticReconnect(false);
        options.setMqttVersion(MQTT_VERSION_3_1_1);

        pClient.setCallback(new MyCallback());
        pClient.connect(options);

        mqttTopic = pClient.getTopic(topic);
    }

    public void publishToBroker(MqttTopic mqttTopic, MqttMessage mqttMessage) throws MqttException {
        MqttDeliveryToken mqttDeliveryToken = mqttTopic.publish(mqttMessage);
        mqttDeliveryToken.waitForCompletion();
        System.out.println("传输是否完成？"+mqttDeliveryToken.isComplete());
    }

    public static void main(String[] args) throws MqttException {
        PublishClient publishClient = new PublishClient();
        publishClient.mqttMessage = new MqttMessage();
        publishClient.mqttMessage.setQos(2);
        publishClient.mqttMessage.setPayload("hello mqtt2!".getBytes(StandardCharsets.UTF_8));
        publishClient.publishToBroker(publishClient.mqttTopic, publishClient.mqttMessage);
    }
}
