package br.com.alfaya.livrariaalfa.repository;

import javax.persistence.Cacheable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alfaya.livrariaalfa.model.Publisher;

@Cacheable
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
