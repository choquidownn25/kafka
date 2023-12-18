package com.example.demo.devs.kafka.consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
public class DevConsumerAutoCommit {
    public static final Logger log= LoggerFactory.getLogger(DevConsumerAutoCommit.class);

    public static void main(String[] args) {
        Properties props=new Properties();
        //props.put("bootstrap.servers", "localhost:9092");
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id","devs4j-group"); //Multiples clientes
        props.setProperty("enable.auto.commit","true");
        props.setProperty("auto.commit.interval.ms","1000");
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, String.valueOf(false));
        try(KafkaConsumer<String, String> consumer=new
                KafkaConsumer<>(props);) {
            consumer.subscribe(Arrays.asList("devs4j-topic"));

            while(true) {
                ConsumerRecords<String,String> records=
                        consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String, String> record
                        :records)
                    log.info("offset = {}, key = {}, value ={}",
                            record.offset(),record.key(),record.value());
                consumer.commitSync();
            }
        }
    }
}
