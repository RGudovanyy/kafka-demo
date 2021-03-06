package net.anviprojects.kafkademo.simple

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@Profile("simple")
class Consumer () {

    val logger  = LoggerFactory.getLogger("Consumer")


    @KafkaListener(topics = arrayOf("topic1"), groupId = "group1")
    fun listen(message : String) {
        logger.info("Received {} in topic 1", message)
    }

    @KafkaListener(topics = arrayOf("topic2"), groupId = "group1")
    fun listenAndAnswer(message : String) : String {
        logger.info("Received {} in topic 2", message)
        return "Gotcha!"
    }

}