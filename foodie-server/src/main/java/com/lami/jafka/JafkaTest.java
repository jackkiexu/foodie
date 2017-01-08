package com.lami.jafka;

import com.sohu.jafka.producer.Producer;
import com.sohu.jafka.producer.ProducerConfig;
import com.sohu.jafka.producer.StringProducerData;
import com.sohu.jafka.producer.serializer.StringEncoder;

import java.util.Properties;

/**
 * Created by xjk on 11/24/16.
 */
public class JafkaTest {

    public static void main(String[] args) {
        Properties pros = new Properties();
        pros.put("zk.connect", "192.168.1.248:2181");
        pros.put("serializer.class", StringEncoder.class.getName());

        //
        ProducerConfig config = new ProducerConfig(pros);
        Producer<String, String> producer = new Producer<String, String>(config);

        //
        StringProducerData data = new StringProducerData("demo");
        for(int i = 0; i < 100; i++){
            data.add("Hello world #" + i);
        }

        try {
            for(int i = 0; i < 100; i++){
                producer.send(data);
            }
        } finally {
            producer.close();
        }

    }

}
