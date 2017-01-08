package com.lami.jafka;

import com.sohu.jafka.consumer.Consumer;
import com.sohu.jafka.consumer.ConsumerConfig;
import com.sohu.jafka.consumer.ConsumerConnector;
import com.sohu.jafka.consumer.MessageStream;
import com.sohu.jafka.producer.serializer.StringDecoder;
import com.sohu.jafka.utils.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xjk on 11/24/16.
 */
public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("zk.connect", "192.168.1.248:2181");
        props.put("groupid", "test_group");

        //
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        ConsumerConnector connector = Consumer.create(consumerConfig);

        Map<String, List<MessageStream<String>>> topicMessageStreams = connector.createMessageStreams(ImmutableMap.of("demo", 2), new StringDecoder());
        final List<MessageStream<String>> streams = topicMessageStreams.get("demo");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        final AtomicInteger count = new AtomicInteger();
        for(final MessageStream<String> stream : streams){
            executor.submit(new Callable<Object>() {
                public Object call() throws Exception {
                    for(String message : stream)
                        System.out.println(count.incrementAndGet() + " => " + message);
                    return null;
                }
            });
        }

//        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}
