package br.com.alfaya.livrariaalfa.model.dto;

import br.com.alfaya.livrariaalfa.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
  private Long id;
  private String name;
  private String author;
  private String releaseDate;
  private String language;
  private String dimensions;
  private String isbn10;
  private String isbn13;
  private Integer pagesLength;
  private Long publisherId;

  public BookDTO(Book book) {
    this.id = book.getId();
    this.name = book.getName();
    this.author = book.getAuthor();
    this.releaseDate = book.getReleaseDate();
    this.language = book.getLanguage();
    this.dimensions = book.getDimensions();
    this.isbn10 = book.getIsbn10();
    this.isbn13 = book.getIsbn13();
    this.pagesLength = book.getPagesLength();
    this.publisherId = book.getPublisher().getId();
  }
}
