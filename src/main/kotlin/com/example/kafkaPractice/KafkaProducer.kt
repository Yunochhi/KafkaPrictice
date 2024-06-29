package com.example.kafkaPractice


import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.Headers
import org.apache.kafka.common.header.internals.RecordHeaders
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class KafkaProducer (private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun sendMessage(topic: String, key: String, value: String, headers: JsonNode)
    {
        val recordHeaders: Headers = RecordHeaders()

        headers.fields().forEach {
            header -> recordHeaders.add(header.key, header.value.asText().toByteArray())
        }

        val produceRecord = ProducerRecord(topic, 0, key, value, recordHeaders)

        kafkaTemplate.send(produceRecord)
    }
}
