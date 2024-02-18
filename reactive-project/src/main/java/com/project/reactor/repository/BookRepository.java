package com.project.reactor.repository;

import com.project.reactor.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

  Mono<Book> findByName(String name);
  Flux<Book> findByAuthor(String author);
  Flux<Book> findByPublisher(String publisher);
  Flux<Book> findByNameAndAuthor(String name, String author);

  @Query("select * from private.book_details where name = :name and author = :auth")
  Flux<Book> getAllBooksByAuthor(@Param("name") String name, @Param("auth") String author);

  @Query("select * from private.book_details where lower(name) like lower(concat('%',concat(:title,'%')))")
  Flux<Book> searchBookByTitle(@Param("title") String title);

}
