package com.example.demo.devs.kafka.transacional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
public class TransaccionalProducerAbort {
    public static final Logger log= LoggerFactory.getLogger(TransaccionalProducerAbort.class);
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
            try{
                producer.initTransactions();
                producer.beginTransaction(); //inicia la transacion

                for(int i= 0; i< 100 ;i++) {
                    //Envio de mensajes, de forma asigcrona
                    producer.send(new
                            ProducerRecord<>("devs4j-topic","key","message"));
                    //esto es error es producido, para que aborte
                    if (i== 50){
                        throw new Exception("Unexpected Exception");
                    }
                }
                producer.commitTransaction(); //Hace que muestre los mensajas de kafka
                producer.flush();
            }catch(Exception e){
                log.error("Error en tranasacion y se aborta :", e.getMessage());
                producer.abortTransaction();
            }


        }
        log.info("Prossing time = {} ms", System.currentTimeMillis()-starTime  );
    }
}
