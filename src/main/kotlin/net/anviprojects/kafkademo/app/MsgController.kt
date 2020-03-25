package net.anviprojects.kafkademo.app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MsgController (@Autowired val producer : Producer) {

    @PostMapping("/single")
    fun handleSingleRequest(req : RequestEntity<String>) : ResponseEntity<Void>  {
        if (req.body.isNullOrBlank()) {
            return ResponseEntity.badRequest().build()
        }

        producer.sendAndForget(req.body!!)
        return ResponseEntity.ok().build()
    }


    @PostMapping("/withAnswer")
    fun handleRequestWithAnswer(req : RequestEntity<String>) : ResponseEntity<String>  {
        if (req.body.isNullOrBlank()) {
            return ResponseEntity.badRequest().build()
        }

        val result = producer.sendSync(req.body!!)
        return ResponseEntity.ok().body(result);
    }
}