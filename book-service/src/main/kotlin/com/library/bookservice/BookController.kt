package com.library.bookservice

import com.library.bookservice.domain.book.Book
import com.library.bookservice.domain.book.BookService
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
        private val bookService: BookService
) {

    @GetMapping("/{id}")
    fun findBookById(@PathVariable id: String): ResponseEntity<Book> {
        bookService.findBookById(id)?.let {
            return ResponseEntity.ok(it)
        }
        return ResponseEntity.notFound().build()
    }

    @GetMapping("/search")
    fun search(@RequestParam query: String): ResponseEntity<List<Book>> =
            bookService.findByAuthorOrName(query).let {
                return if (it.isEmpty()) {
                    ResponseEntity.notFound().build()
                } else {
                    ResponseEntity.ok(it)
                }
            }

    @PostMapping
    fun registerBook(@RequestBody book: Book): ResponseEntity<Book> =
            ResponseEntity.ok(bookService.registerBook(book))
}