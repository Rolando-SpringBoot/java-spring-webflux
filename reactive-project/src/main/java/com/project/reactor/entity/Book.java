package com.project.reactor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "book_details", schema = "private")
public class Book {

  @Id
  @Column("book_id")
  private Integer bookId;
  private String name;
  @Column("book_desc")
  private String description;
  private String publisher;
  private String author;

}
