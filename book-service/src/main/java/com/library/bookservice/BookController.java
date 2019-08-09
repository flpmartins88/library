package com.library.bookservice;

import com.library.bookservice.domain.book.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @GetMapping("/{book}")
    public ResponseEntity<Book> get(@PathVariable Book book) {
        return ResponseEntity.ok(book);
    }

}
