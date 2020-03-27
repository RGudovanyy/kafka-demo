package net.anviprojects.kafkademo.complex.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@Profile("complex")
class AdminConfig(@Value("\${kafka.bootstrapAddress}") private val bootstrapAddress : String) {

    @Bean
    fun admin() : KafkaAdmin {
        val configs = HashMap<String, Any>()
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        return KafkaAdmin(configs)
    }

    @Bean
    fun paymentsTopic() : NewTopic = TopicBuilder.name("payments").partitions(2).compact().build()

    @Bean
    fun contractsTopic() : NewTopic = TopicBuilder.name("contracts").partitions(2).compact().build()
}