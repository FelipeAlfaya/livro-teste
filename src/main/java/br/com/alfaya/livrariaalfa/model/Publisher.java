package br.com.alfaya.livrariaalfa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String country;
  private String state;
  private Integer releaseYear;

  @OneToMany(mappedBy = "publisher")
  private List<Book> books;

  public Publisher() {
    this.books = new ArrayList<>();
  }
}
