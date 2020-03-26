package net.anviprojects.kafkademo.simple.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.common.config.TopicConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@Profile("simple")
class AdminConfig(@Value("\${kafka.bootstrapAddress}") private val bootstrapAddress : String) {

    @Bean
    fun admin() : KafkaAdmin {
        val configs = HashMap<String, Any>()
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
        return KafkaAdmin(configs)
    }

    @Bean
    fun topic1() : NewTopic {
        return TopicBuilder.name("topic1")
                .partitions(2)
                .replicas(1)
                .compact()
                .build()
    }

    @Bean
    fun topic2() : NewTopic {
        return TopicBuilder.name("topic2")
                .partitions(3)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build()
    }

    @Bean
    fun topic3() : NewTopic {
        return TopicBuilder.name("topic3")
                .partitions(2)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build()
    }
}