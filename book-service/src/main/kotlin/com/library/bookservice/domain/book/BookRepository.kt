package com.library.bookservice.domain.book

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, String>, Specification<Book> {
    @Query("FROM Book where author like :query or name like :query")
    fun findBookByAuthorOrName(query: String): List<Book>
}