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

       /*Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        SensorData _sensor = new SensorData(1.111, new Date(System.currentTimeMillis()));

        //KafkaProducer<String, SensorData> producer = new KafkaProducer<String, SensorData>(props);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //ProducerRecord<String, SensorData> record = new ProducerRecord<>("teste",_sensor);

        //producer.send(record);
        producer.send(new ProducerRecord<String,String>("teste1","value"));*/

        /*Properties props = new Properties();

        SensorData _sensor = new SensorData(1.111, new Date(System.currentTimeMillis()));

        //props.put("bootstrap.servers", "localhost:9092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        Producer<String,SensorData> kafkaProducer =
                new KafkaProducer<>(
                        props,
                        new StringSerializer(),
                        new KafkaJsonSerializer()
                );

        Producer<String,String> kafkaString = new KafkaProducer<String, String>(props,new StringSerializer(),new StringSerializer());

        //kafkaProducer.send(new ProducerRecord<>("teste", "0", _sensor ));
        kafkaString.send(new ProducerRecord<String,String>("teste", "000000","aasdas"));*/

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
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

        /*for(int i=0;i<50;i++){
            //SensorData _sensor = new SensorData(1.111, new Date(System.currentTimeMillis()));

            double valor_double = Utils.returnRandomDouble(5,25);
            int id = Utils.returnRandomInt(1,3);

            SensorData dados_sensor = new SensorData(id,valor_double,Utils.return_current_date(),Utils.return_current_time());

            ProducerRecord<String,SensorData> record = new ProducerRecord<String,SensorData>("testeSensorData",String.valueOf(id),dados_sensor);

            producer.send(record);

            System.out.println("Enviado!");

        }*/
        //producer.close();

        System.out.println("FIM!");

        /*SensorData _sensor = new SensorData(1,1.111, new Date(System.currentTimeMillis()));

        ProducerRecord<String,SensorData> record = new ProducerRecord<String,SensorData>("teste","0",_sensor);

        producer.send(record);

        System.out.println("Enviado!");
        producer.close();*/
    }
}
