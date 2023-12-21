package com.example.demokafkaautocommint.service;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaConsumer {
    public static final Logger log= LoggerFactory.getLogger(KafkaConsumer.class);
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers; // Value = 'SpringAppCluste
    @KafkaListener(topics = "devs4j-topic", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, String> record) {
        //int numero = record.key().length();
        log.info("Consumer Menssage : ", record.value());
        Properties props=new Properties();
        //props.put("bootstrap.servers", "localhost:9092");
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.setProperty("group.id","devs4j-group"); //Multiples clientes
       // props.setProperty("enable.auto.commit","true");
        //props.setProperty("auto.commit.interval.ms","1000");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, String.valueOf(false));
        try(org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer=new
                org.apache.kafka.clients.consumer.KafkaConsumer<>(props);) {
            consumer.subscribe(Arrays.asList("devs4j-topic"));

            while(true) {
                ConsumerRecords<String,String> records=
                        consumer.poll(Duration.ofMillis(1));
                for(ConsumerRecord<String, String> recordt
                        :records)
                    log.info("offset = {}, key = {}, value ={}",
                            record.offset(),record.key(),record.value());
                consumer.commitSync();
                System.out.println("Consumed message or Received Message : " + record.value());
            }
        }

            //System.out.println("Consumed message or Received Message : " + record.value());



    }
}
