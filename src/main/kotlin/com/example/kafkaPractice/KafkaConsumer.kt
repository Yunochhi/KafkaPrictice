package com.example.kafkaPractice

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {
    @KafkaListener(topics = ["TestTopic"], groupId = "myGroup")
    fun listen(record: ConsumerRecord<String, String>)
    {
        val headers = record.headers()
        println("Received: ")
        println("Message: key: ${record.key()}, value: ${record.value()}")

        for (header in headers)
        {
            println("Headers: key: ${header.key()}, value: ${String(header.value())}")
        }
    }
}