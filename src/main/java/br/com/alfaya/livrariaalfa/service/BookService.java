package br.com.alfaya.livrariaalfa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alfaya.livrariaalfa.model.Book;
import br.com.alfaya.livrariaalfa.model.form.BookForm;
import br.com.alfaya.livrariaalfa.repository.BookRepository;
import br.com.alfaya.livrariaalfa.repository.PublisherRepository;

@Service
@Transactional
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private PublisherRepository publisherRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book getBook(Long id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      return book.get();
    }
    return null;
  }

  public Book createBook(BookForm bookForm) {
    Book book = bookForm.build(publisherRepository);
    bookRepository.save(book);
    return book;
  }

  public Book updateBook(Long id, BookForm bookForm) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      bookForm.update(book.get(), publisherRepository);
      return book.get();
    }
    return null;
  }

  public boolean canDeleteBook(Long id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isPresent()) {
      bookRepository.delete(book.get());
      return true;
    }
    return false;
  }
}
