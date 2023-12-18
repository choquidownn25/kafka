package com.example.demo.devs.kafka.multithread;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

//Para consumir mensajes de kafka a través de múltiples
//threads
public class DevThreadConsumer  extends  Thread{
    private final KafkaConsumer<String,
                String> consumer;
    private final AtomicBoolean closed = new
            AtomicBoolean(false);
    public static final Logger log=
            LoggerFactory.getLogger(DevThreadConsumer
                    .class);

    public DevThreadConsumer(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try{
            consumer.subscribe(Arrays.asList("devs4j-topic")); while(!closed.get()) {
                ConsumerRecords<String, String>records=
                        consumer.poll(Duration.ofMillis(10000));
                for(ConsumerRecord<String, String>record
                        :records) {
                    log.info("offset = {}, key = {}, value = {}",record.offset(),record.key(),record.value());
                }
            }
        }catch(Exception e) {
            if(!closed.get())
                throw e;
        }finally{
            consumer.close();
        }
    }
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }

}
