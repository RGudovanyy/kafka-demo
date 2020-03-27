package net.anviprojects.kafkademo.complex.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.UUIDDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.util.*
import kotlin.collections.HashMap

@Configuration
@EnableKafka
@Profile("complex")
class ConsumerConfiguration(
        @Value("\${kafka.bootstrapAddress}")
        private val bootstrapAddress : String) {


    @Bean
    fun paymentConsumerFactory() : ConsumerFactory<UUID, Any> {
        val jsonDeserializer = JsonDeserializer<Any>()
        jsonDeserializer.addTrustedPackages("*")

        return DefaultKafkaConsumerFactory(getConsumerProps(), UUIDDeserializer(), jsonDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<UUID, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<UUID, Any>()
        factory.consumerFactory = paymentConsumerFactory()
        return factory
    }

    @Bean
    fun stringConsumerFactory() : ConsumerFactory<UUID, String> {
        return DefaultKafkaConsumerFactory(getConsumerProps(), UUIDDeserializer(), StringDeserializer())
    }

    @Bean
    fun kafkaListenerStringContainerFactory() : ConcurrentKafkaListenerContainerFactory<UUID, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<UUID, String>()
        factory.consumerFactory = stringConsumerFactory()
        return factory
    }

    private fun getConsumerProps(): HashMap<String, Any> {
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        return props
    }



}
