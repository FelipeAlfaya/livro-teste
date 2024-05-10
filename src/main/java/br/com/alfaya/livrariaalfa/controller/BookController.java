package br.com.alfaya.livrariaalfa.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alfaya.livrariaalfa.model.Book;
import br.com.alfaya.livrariaalfa.model.dto.BookDTO;
import br.com.alfaya.livrariaalfa.model.form.BookForm;
import br.com.alfaya.livrariaalfa.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  public List<BookDTO> getAllBooks() {
    List<BookDTO> dtoBooks = new ArrayList<>();
    List<Book> books = bookService.getAllBooks();
    for (Book book : books) {
      dtoBooks.add(new BookDTO(book));
    }
    return dtoBooks;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
    Book book = bookService.getBook(id);
    if (book != null) {
      return ResponseEntity.ok(new BookDTO(book));
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<BookDTO> saveBook(@RequestBody BookForm bookForm, UriComponentsBuilder builder) {
    Book book = bookService.createBook(bookForm);
    URI uri = builder.path("/books/{id}").buildAndExpand(book.getId()).toUri();
    return ResponseEntity.created(uri).body(new BookDTO(book));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookForm bookForm) {
    Book book = bookService.updateBook(id, bookForm);
    if (book != null) {
      return ResponseEntity.ok(new BookDTO(book));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable Long id) {
    if (bookService.canDeleteBook(id)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
