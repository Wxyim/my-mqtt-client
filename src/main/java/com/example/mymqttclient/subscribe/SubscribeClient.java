package com.example.mymqttclient.subscribe;

import com.example.mymqttclient.callbackClass.MyCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_3_1_1;

public class SubscribeClient {

    MqttClient sClient;
    MqttConnectOptions options;

    String[] topic = {"topic37"};
    int[] qos = {2};

    public void subscribeFromBroker() throws MqttException {
        sClient = new MqttClient("tcp://broker.emqx.io:1883", "subscribe1", new MemoryPersistence());
        options = new MqttConnectOptions();
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(60);
        options.setCleanSession(true);
        options.setAutomaticReconnect(false);
        options.setMqttVersion(MQTT_VERSION_3_1_1);

        sClient.setCallback(new MyCallback());
        sClient.connect();

        sClient.subscribe(topic, qos);
    }

    public static void main(String[] args) throws MqttException {
        SubscribeClient subscribeClient = new SubscribeClient();
        subscribeClient.subscribeFromBroker();
    }

}
