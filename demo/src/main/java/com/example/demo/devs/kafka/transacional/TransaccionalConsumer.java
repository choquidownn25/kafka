package com.example.demo.devs.kafka.transacional;

import com.example.demo.devs.kafka.consumer.DevConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class TransaccionalConsumer {
    public static final Logger log= LoggerFactory.getLogger(TransaccionalConsumer.class);
    public static void main(String[] args)  {
        Properties props=new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","devs4j-group"); //Multiples clientes
        props.setProperty("enable.auto.commit","true");
        props.setProperty("isolation.level", "read_committed");
        props.setProperty("auto.commit.interval.ms","1000");
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        try(KafkaConsumer<String, String> consumer=new
                KafkaConsumer<>(props);) {
            consumer.subscribe(Arrays.asList("devs4j-topic")); while(true) {
                ConsumerRecords<String,String> records=
                        consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String, String> record
                        :records)
                    log.info("offset = {}, key = {}, value ={}",
                            record.offset(),record.key(),record.value());
            }
        }
    }
}
