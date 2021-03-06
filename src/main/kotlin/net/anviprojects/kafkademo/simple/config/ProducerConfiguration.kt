package net.anviprojects.kafkademo.simple.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
@Profile("simple")
class ProducerConfiguration(@Value("\${kafka.bootstrapAddress}") private val bootstrapAddress : String) {

    @Bean
    fun producerFactory() : ProducerFactory<String, String> {
        val props = HashMap<String, Any>();
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }
}