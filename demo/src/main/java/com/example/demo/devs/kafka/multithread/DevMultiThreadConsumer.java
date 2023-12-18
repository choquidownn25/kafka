package com.example.demo.devs.kafka.multithread;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DevMultiThreadConsumer {
    public static void main(String[] args){
        Properties props=new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","devs4j-group"); //Multiples clientes
        props.setProperty("enable.auto.commit","true");
        props.setProperty("auto.commit.interval.ms","1000");
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        //Config properties
        ExecutorService executor=
                Executors.newFixedThreadPool(5);
        for(int i= 0;i< 5;i++) {
            Runnable worker= new
                    DevThreadConsumer(new KafkaConsumer<>(props) );
            executor.execute(worker);
        }
        executor.shutdown(); while(!executor.isTerminated()) ;
    }

}
