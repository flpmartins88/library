package com.library.user

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User (

        @Id
        val id: String = UUID.randomUUID().toString(),

        val nome: String

)