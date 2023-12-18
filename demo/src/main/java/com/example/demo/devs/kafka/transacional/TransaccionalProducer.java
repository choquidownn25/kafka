package com.example.demo.devs.kafka.transacional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class TransaccionalProducer {
    public static final Logger log= LoggerFactory.getLogger(TransaccionalProducer.class);
    public static void main(String[] args) {
        //Medir el tiempo
        long starTime   = System.currentTimeMillis();
        Properties props=new Properties();
        props.put("bootstrap.servers","localhost:9092");//Broker de kafka que vamos a conectar
        props.put("acks","all"); //
        props.put("transactional.id","devs4j-producer");
        //props.put("buffer.memory","33554432");
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");//formato de serializacion
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms ", "0");
        //Creacion del objeto
        try(Producer<String, String> producer=new KafkaProducer<>(props);) {
            producer.initTransactions();
            producer.beginTransaction(); //inicia la transacion

            for(int i= 0; i< 100 ;i++) {
                //Envio de mensajes, de forma asigcrona
                producer.send(new
                        ProducerRecord<>("devs4j-topic","key","message"));
            }
            producer.commitTransaction();
            producer.flush();
        }
        log.info("Prossing time = {} ms", System.currentTimeMillis()-starTime  );
    }
}
