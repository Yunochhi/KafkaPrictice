package com.example.kafkaPractice

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/kafka")
class MessageController (private val kafkaProducer: KafkaProducer) {
    data class KafkaMessageRequest(val topic: String, val key: String, val value: String, val headers: JsonNode)

    @PostMapping("/send")
    fun sendMessage(@RequestBody message: KafkaMessageRequest){
        kafkaProducer.sendMessage(message.topic, message.key, message.value, message.headers)
    }
}