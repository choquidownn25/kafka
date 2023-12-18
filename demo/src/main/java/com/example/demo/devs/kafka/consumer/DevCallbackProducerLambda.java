package com.example.demo.devs.kafka.consumer;

import com.example.demo.devs.kafka.producer.DevProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
//Entrega de envio de mensajes
public class DevCallbackProducerLambda {
    public static final Logger log= LoggerFactory.getLogger(DevCallbackProducerLambda.class);

    public static void main(String[] args) {
        //Medir el tiempo
        long starTime   = System.currentTimeMillis();
        Properties props=new Properties();
        props.put("bootstrap.servers","localhost:9092");//Broker de kafka que vamos a conectar
        props.put("acks","all"); //
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");//formato de serializacion
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms ", "0");
        //Creacion del objeto
        try(Producer<String, String> producer=new
                KafkaProducer<>(props);) {
            for(int i= 0; i< 100 ;i++) {
                //Envio de mensajes, de forma asigcrona
                producer.send(new
                        ProducerRecord<>("devs4j-topic","key","message"),
                        //Utilizamos lambda
                        (metadata,exception) ->{
                        if(exception!=null){
                            log.error("Error sending the message", exception);
                        }
                            log.info("Partition = {} Offset ={}", metadata.partition(), metadata.offset());

                        });
            }
            producer.flush();
        }
        log.info("Prossing time = {} ms", System.currentTimeMillis()-starTime  );
    }
}
