package net.anviprojects.kafkademo.complex

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

/**
 * A list of DTO classes used in application
 */

data class PaymentDto(
        @JsonProperty("id")
        val id : UUID,
        @JsonProperty("sender")
        val sender : String,
        @JsonProperty("subject")
        val subject : String,
        @JsonProperty("date")
        val date : LocalDate,
        @JsonProperty("amount")
        val amount : BigDecimal
        )

data class ContractDto(
        @JsonProperty("id")
        val id : UUID,
        @JsonProperty("sender")
        val sender : String,
        @JsonProperty("subject")
        val subject: String,
        @JsonProperty("receiver")
        val receiver : String,
        @JsonProperty("date")
        val date : LocalDate
)