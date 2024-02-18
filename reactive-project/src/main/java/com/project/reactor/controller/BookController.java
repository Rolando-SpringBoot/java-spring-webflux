package com.project.reactor.controller;

import com.project.reactor.entity.Book;
import com.project.reactor.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping
  public Mono<Book> create(@RequestBody Book book) {
    return this.bookService.create(book);
  }

  @GetMapping()
  public Flux<Book> getAll() {
    return this.bookService.getAll();
  }

  @GetMapping("/{id}")
  public Mono<Book> get(@PathVariable("id") int bookId) {
    return this.bookService.get(bookId);
  }

  @PutMapping("/{id}")
  public Mono<Book> update(@RequestBody Book book, @PathVariable("id") int bookId) {
    return this.bookService.update(book, bookId);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") int bookId) {
    return this.bookService.delete(bookId);
  }

  @GetMapping("/search")
  public Flux<Book> searchBooks(
      @RequestParam("query") String query

  ) {
    return this.bookService.searchBooks(query);
  }

}
