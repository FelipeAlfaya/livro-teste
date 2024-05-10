package br.com.alfaya.livrariaalfa.model.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alfaya.livrariaalfa.model.Book;
import br.com.alfaya.livrariaalfa.model.Publisher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherDTO {
  private Long id;
  private String name;
  private String country;
  private String state;
  private Integer releaseYear;
  private List<BookDTO> books = new ArrayList<>();

  public PublisherDTO(Publisher publisher) {
    this.id = publisher.getId();
    this.name = publisher.getName();
    this.country = publisher.getCountry();
    this.state = publisher.getState();
    this.releaseYear = publisher.getReleaseYear();
    this.books = new ArrayList<>();

    for (Book book : publisher.getBooks()) {
      this.books.add(new BookDTO(book));
    }
  }
}
