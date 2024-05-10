package br.com.alfaya.livrariaalfa.model.form;

import java.util.Optional;

import br.com.alfaya.livrariaalfa.model.Book;
import br.com.alfaya.livrariaalfa.model.Publisher;
import br.com.alfaya.livrariaalfa.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookForm {
  private String name;
  private String author;
  private String releaseDate;
  private String language;
  private String dimensions;
  private String isbn10;
  private String isbn13;
  private Integer pagesLength;
  private Long publisherId;

  public Book build(PublisherRepository publisherRepository) {
    Book book = new Book();

    updateProperties(book);

    Publisher publisher = getPublisher(publisherRepository);
    if (publisher != null) {
      book.setPublisher(publisher);
    }

    return book;
  }

  public Book update(Book book, PublisherRepository publisherRepository) {
    updateProperties(book);

    Publisher publisher = getPublisher(publisherRepository);
    if (publisher != null) {
      book.setPublisher(publisher);
    }

    return book;
  }

  private void updateProperties(Book book) {
    book.setName(name);
    book.setAuthor(author);
    book.setReleaseDate(releaseDate);
    book.setLanguage(language);
    book.setDimensions(dimensions);
    book.setIsbn10(isbn10);
    book.setIsbn13(isbn13);
    book.setPagesLength(pagesLength);
  }

  public Publisher getPublisher(PublisherRepository publisherRepository) {
    Optional<Publisher> publisher = publisherRepository.findById(publisherId);
    if (publisher.isPresent()) {
      return publisher.get();
    }
    return null;
  }
}
