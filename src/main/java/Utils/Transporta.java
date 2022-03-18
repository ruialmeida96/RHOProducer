package Utils;

import Model.SensorData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Transporta implements Runnable {
    private final KafkaProducer<String, SensorData> producer;
    private final String topicName;
    private final int sensor_id;
    private double last_value;

    public Transporta(KafkaProducer<String, SensorData> producer, String topic, int sensor_id) {
        this.producer = producer;
        this.topicName = topic;
        this.sensor_id = sensor_id;
    }

    public void run() {
        last_value = 0;
        System.out.println("THREAD STARTING -->" + sensor_id);
        while (true) {
            try {
                double valor_double = Utils.returnRandomDouble(5, 25);

                double difference = last_value - valor_double;
                if (difference > 0.01 || difference < -0.01) {
                    SensorData dados_sensor = new SensorData(sensor_id, valor_double, Utils.return_current_date(), Utils.return_current_time());
                    System.out.println("Sensor id: " + sensor_id + " | valor: " + dados_sensor.getValor());
                    producer.send(new ProducerRecord<String, SensorData>(topicName, String.valueOf(sensor_id), dados_sensor));

                    last_value = valor_double;
                }
                else{
                    System.out.println("Valor a enviar não tinha diferença de pelomenos 0.01");
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
