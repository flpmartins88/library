package com.library.bookservice.domain.book

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
data class BookService(
        private val bookRepository: BookRepository
) {
    fun findBookById(id: String): Book? = bookRepository.findByIdOrNull(id)

    fun findByAuthorOrName(query: String): List<Book> = bookRepository.findBookByAuthorOrName("%$query%")

    fun registerBook(book: Book): Book = bookRepository.save(book)
}