package net.anviprojects.kafkademo.complex.producer

import net.anviprojects.kafkademo.complex.ContractDto
import net.anviprojects.kafkademo.complex.PaymentDto
import org.springframework.context.annotation.Profile
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

/**
 * A producer that send a message with DTO payload based on random generator
 */
@Component
@Profile("complex")
class Producer(val kafkaTemplate: KafkaTemplate<UUID, Any>) {

    fun produceMessages() {
        while (true) {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val random = Random.nextInt()
        val id = UUID.randomUUID()
        if (random % 3 == 0) {
            kafkaTemplate.send("payments", id, generateRandomPayment(id))
        } else {
            kafkaTemplate.send("contracts", id, generateRandomContract(id))
        }
    }

    private fun generateRandomContract(id : UUID): ContractDto {
        return ContractDto(id, sender(), subject(), receiver(), LocalDate.now())
    }

    private fun generateRandomPayment(id : UUID): PaymentDto {
        return PaymentDto(id, sender(), subject(), LocalDate.now(), amount())
    }

    private fun sender() = "Sender-{$Random.nextInt(10)}"

    private fun subject() =  "Subject-{$Random.nextInt(10)}"

    private fun receiver() = "Receiver-{$Random.nextInt(10)}"

    private fun amount() = BigDecimal.valueOf(Random.nextDouble())
}