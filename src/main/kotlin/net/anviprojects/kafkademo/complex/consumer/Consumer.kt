package net.anviprojects.kafkademo.complex.consumer

import net.anviprojects.kafkademo.complex.ContractDto
import net.anviprojects.kafkademo.complex.PaymentDto
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
@Profile("complex")
class Consumer {

    val logger  = LoggerFactory.getLogger(Consumer::class.java)

    @KafkaListener(topics = arrayOf("payments"), clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory", groupId = "payment_group")
    fun listenAsObject(payment : PaymentDto, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition : Int, @Header(KafkaHeaders.GROUP_ID) group : String) {
        logger.info("[JSON] New payment on {$group}-{$partition}: {$payment}")
    }

    @KafkaListener(topics = arrayOf("payments"), clientIdPrefix = "string", containerFactory = "kafkaListenerStringContainerFactory", groupId = "payment_group")
    fun listenAsString(payment: String, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition : Int, @Header(KafkaHeaders.GROUP_ID) group : String) {
        logger.info("[String] New payment on {$group}-{$partition}: {${payment}}")
    }

    @KafkaListener(topics = arrayOf("contracts"), clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory", groupId = "contract_group")
    fun listenAsObjectContract(contract: ContractDto, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition : Int, @Header(KafkaHeaders.GROUP_ID) group : String) {
        logger.info("[JSON] New contract on {$group}-{$partition}: {$contract}")
    }

    @KafkaListener(topics = arrayOf("contracts"), clientIdPrefix = "json", containerFactory = "kafkaListenerStringContainerFactory", groupId = "contract_group")
    fun listenAsStringContract(contract: String, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition : Int, @Header(KafkaHeaders.GROUP_ID) group : String) {
        logger.info("[String] New contract on {$group}-{$partition}: {${contract}}")
    }




}