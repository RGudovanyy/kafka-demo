package net.anviprojects.kafkademo.complex.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.common.serialization.UUIDSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.*
import kotlin.collections.HashMap

@Configuration
@Profile("complex")
class ProducerConfiguration(
        @Value("\${kafka.bootstrapAddress}")
        private val bootstrapAddress : String
) {

    @Bean
    fun producerFactory() : ProducerFactory<UUID, Any> {
        val props = HashMap<String, Any>();
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = UUIDSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<UUID, Any> {
        return KafkaTemplate(producerFactory())
    }
}