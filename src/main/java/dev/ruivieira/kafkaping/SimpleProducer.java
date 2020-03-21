package dev.ruivieira.kafkaping;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Properties;

@ApplicationScoped
public class SimpleProducer {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    String bootstrap;

    final static String applicationId = "kafka-ping";

    private Producer<Long, String> producer;

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        bootstrap = System.getenv("KAFKA_BROKER");
        System.out.println("Bootstrap = " + bootstrap);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, applicationId);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(props);
    }

    public void sendMessage(String topic, String message) {
        ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(topic, message);
        producer.send(record);
        LOGGER.info("Sent message " + message + " to topic: " + topic);
    }


}