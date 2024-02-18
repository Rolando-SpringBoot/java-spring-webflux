package com.project.reactor.service.impl;

import com.project.reactor.entity.Book;
import com.project.reactor.repository.BookRepository;
import com.project.reactor.service.BookService;
import java.time.Duration;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public Mono<Book> create(Book book) {
    return this.bookRepository.save(book);
  }

  @Override
  public Flux<Book> getAll() {
    System.out.println(Thread.currentThread().getName());
    return this.bookRepository.findAll()
        .map(book -> {
          book.setName(book.getName().toUpperCase());
          return book;
        })
        .sort(Comparator.comparing(Book::getBookId, Comparator.naturalOrder()))
        .delayElements(Duration.ofSeconds(2));
  }

  @Override
  public Mono<Book> get(int bookId) {
    return this.bookRepository.findById(bookId);
  }

  @Override
  public Mono<Book> update(Book book, int bookId) {
    Mono<Book> oldBook = this.bookRepository.findById(bookId);
    return oldBook.flatMap(item -> {
      item.setName(book.getName());
      item.setPublisher(book.getPublisher());
      item.setAuthor(book.getAuthor());
      item.setDescription(book.getDescription());

      return this.bookRepository.save(item);
    });
  }

  @Override
  public Mono<Void> delete(int bookId) {
    return this.bookRepository
        .findById(bookId)
        .flatMap(book -> this.bookRepository.deleteById(bookId));
  }

  @Override
  public Flux<Book> searchBooks(String title) {
    return this.bookRepository.searchBookByTitle(title)
        .filter(book -> book.getName().toLowerCase().startsWith("react"));
  }

}
