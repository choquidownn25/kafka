package com.example.demo.devs.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class DevProducer {
    public static final Logger log= LoggerFactory.getLogger(DevProducer.class);
    public static void main(String[] args){
        //Medir el tiempo
        long starTime   = System.currentTimeMillis();
        Properties props=new Properties();
        props.put("bootstrap.servers","localhost:9092");//Broker de kafka que vamos a conectar
        props.put("group.id", "devs4j-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("acks","all"); //
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");//formato de serializacion
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms ", "0");
        //Creacion del objeto
        try(Producer<String, String>producer=new
                KafkaProducer<>(props);) {
            for(int i= 0; i< 100 ;i++) {
                //Envio de mensajes, de forma asigcrona
                producer.send(new ProducerRecord<>("devs4j-topic","key","message"));
//                producer.send(new
//                        ProducerRecord<>("devs4j-topic",
//                        (i % 2 ==0 )?"key-2.1":"key-3.1",
//                        String.valueOf(i)));
            }
            producer.flush();
        }
        log.info("Prossing time = {} ms", System.currentTimeMillis()-starTime  );
    }
}
