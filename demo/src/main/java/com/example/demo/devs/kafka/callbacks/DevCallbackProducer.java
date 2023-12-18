package com.example.demo.devs.kafka.callbacks;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class DevCallbackProducer {
    public static final Logger log= LoggerFactory.getLogger(DevCallbackProducer.class);
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
                                ProducerRecord<>("devs4j-topic", "key", "message"),
                        new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                if(e!=null) {
                                    log.error("Error sending the message"
                                            ,e);
                                }
                                log.info("Partition = {} Offset ={}", recordMetadata.partition(),recordMetadata.offset());

                            }
                        });
            }
            producer.flush();
        }
        log.info("Prossing time = {} ms", System.currentTimeMillis()-starTime  );
    }
}
