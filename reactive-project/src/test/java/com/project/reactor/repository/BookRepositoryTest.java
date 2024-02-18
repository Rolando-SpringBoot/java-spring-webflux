package com.project.reactor.repository;


import com.project.reactor.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
class BookRepositoryTest {

  @Autowired
  private BookRepository bookRepository;

  @Test
  void findNyName() {
    Mono<Book> nameMono = this.bookRepository.findByName("Angular front end")
        .log();
    StepVerifier.create(nameMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void findByAuthor() {
    Flux<Book> authorFlux = this.bookRepository.findByAuthor("ravi rampal");
    StepVerifier.create(authorFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void findByNameAndAuthor() {
    /*
      as() -> Transform this Flux into a target type.
     */
    this.bookRepository.findByNameAndAuthor("Angular front end", "ravi rampal")
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete();
  }

  @Test
  void getAllBooksByAuthorQuery() {
    this.bookRepository.getAllBooksByAuthor("Basic Python Book","Python rocker")
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete();
  }

  @ValueSource(strings = {"front", "FRONT"})
  @ParameterizedTest
  void searchBookByTitleQuery(String title) {
    this.bookRepository.searchBookByTitle(title)
        .log()
        .as(StepVerifier::create)
        .expectNextCount(2)
        .verifyComplete();
  }

}