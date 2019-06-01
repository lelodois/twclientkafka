package br.com.lelodois.twproducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class TwProducer {

    private final KafkaProducer<String, String> producer;
    private String topic;

    public TwProducer(String topic) {
        Properties setter = new Properties();
        setter.setProperty(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        setter.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        setter.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(setter);
        this.topic = topic;
    }

    public void send(String message, Callback callback) {
        producer.send(
                new ProducerRecord<>(topic, message),
                callback);
    }
}
