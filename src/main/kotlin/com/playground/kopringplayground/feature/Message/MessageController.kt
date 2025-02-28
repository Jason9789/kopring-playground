package com.playground.kopringplayground.feature.Message

import com.playground.kopringplayground.core.domain.Message
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/messages")
class MessageController (
    private val service: MessageService
) {

    @GetMapping("/index")
    fun index (
        @RequestParam(name = "name", required = false) name: String
    ) = "Hello $name"

    @GetMapping("/{id}")
    fun findMessage(@PathVariable id: String): ResponseEntity<Message> = service.findMessageById(id).toResponseEntity()

    @GetMapping("/list")
    fun listMessages() = service.findMessages()

    @PostMapping
    fun post (
        @RequestBody message: Message
    ): ResponseEntity<Message> {
        val savedMessage = service.save(message)

        return ResponseEntity.created(URI("/${savedMessage.id}"))
            .body(savedMessage)
    }

    private fun Message?.toResponseEntity(): ResponseEntity<Message> =
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

}