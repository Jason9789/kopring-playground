package com.playground.kopringplayground.feature.Message

import com.playground.kopringplayground.core.domain.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, String> {
}