package net.anviprojects.kafkademo.complex

import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

/**
 * A list of DTO classes used in application
 */

data class PaymentDto(
        val id : UUID,
        val sender : String,
        val subject : String,
        val date : LocalDate,
        val amount : BigDecimal
        )

data class ContractDto(
        val id : UUID,
        val sender : String,
        val subject: String,
        val receiver : String,
        val date : LocalDate
)