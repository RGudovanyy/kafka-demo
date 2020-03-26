package net.anviprojects.kafkademo.complex.consumer

import net.anviprojects.kafkademo.complex.PaymentDto
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@Profile("complex")
class Consumer {

    val logger  = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = arrayOf("payments"), clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
    fun listenAsObject(payment : PaymentDto) {
        logger.info("[JSON] New payment: {$payment}")
    }

    @KafkaListener(topics = arrayOf("payments"), clientIdPrefix = "string", containerFactory = "kafkaListenerStringContainerFactory")
    fun listenAsString(payment: String) {
        logger.info("[String] New payment: {${payment}}")
    }




}