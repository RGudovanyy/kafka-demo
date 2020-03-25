package net.anviprojects.kafkademo.app

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Producer (val kafkaTemplate: KafkaTemplate<Int, String>) {

    fun sendAndForget(message : String) {
        val record = ProducerRecord<Int, String>("topic1", 1, message)
        kafkaTemplate.send(record)
    }

    fun sendSync(message : String) : String {
        val record = ProducerRecord<Int, String>("topic2", 1, message)
        val sendRes = kafkaTemplate.send(record).get()
        return sendRes.producerRecord.value()
    }

    /*fun sendAsync() {
        val record = ProducerRecord<Int, String>("topic1", 1, "Sync message")
        kafkaTemplate.send(record).addCallback()
    }*/


}