package com.library.bookservice.domain.book

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Book(
        @Id
        var id: String = UUID.randomUUID().toString(),
        val name: String,
        val author: String
)
