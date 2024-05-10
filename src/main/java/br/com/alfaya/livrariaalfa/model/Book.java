package br.com.alfaya.livrariaalfa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String author;
  private String releaseDate;
  private String language;
  private String dimensions;
  private String isbn10;
  private String isbn13;
  private Integer pagesLength;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Publisher publisher;
}
