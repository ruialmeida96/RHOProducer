import Serializer.JSONSerializer;
import Model.SensorData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import Utils.*;

public class RHOProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.0.0.3:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class);

        KafkaProducer<String, SensorData> producer = new KafkaProducer<String, SensorData>(props);

        Thread[] dispatchers = new Thread[3];
        for (int i = 1; i <= 3; i++) {
            dispatchers[i - 1] = new Thread(new Transporta(producer, "testeSensorData", i));
            dispatchers[i - 1].start();
        }
        try {
            for (Thread t : dispatchers)
                t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        System.out.println("FIM!");
    }
}
