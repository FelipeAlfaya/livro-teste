package br.com.alfaya.livrariaalfa.repository;

import javax.persistence.Cacheable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alfaya.livrariaalfa.model.Book;

@Cacheable
public interface BookRepository extends JpaRepository<Book, Long> {
}
