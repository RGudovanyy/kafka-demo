package net.anviprojects.kafkademo.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class ProducerConfig(@Value("\${kafka.bootstrapAddress}") private val bootstrapAddress : String) {

    // для задания темплейта нужно определить конфиг и фабрику
    @Bean
    fun producerConfig() : Map<String, Any> {
        val props = HashMap<String, Any>();
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::javaClass
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::javaClass
        return props
    }

    @Bean
    fun producerFactory() : ProducerFactory<Int, String> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<Int, String> {
        return KafkaTemplate(producerFactory())
    }
}