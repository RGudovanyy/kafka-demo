package net.anviprojects.kafkademo.complex.producer

import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Profile("complex")
class MsgController(val producer: Producer) {

    @PostMapping("/start")
    fun startSending() : ResponseEntity<Void> {
        producer.start()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/stop")
    fun stopSending() : ResponseEntity<Void> {
        producer.stop()
        return ResponseEntity.ok().build()
    }

}