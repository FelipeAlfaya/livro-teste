package br.com.alfaya.livrariaalfa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alfaya.livrariaalfa.model.Publisher;
import br.com.alfaya.livrariaalfa.model.form.PublisherForm;
import br.com.alfaya.livrariaalfa.repository.PublisherRepository;

@Service
@Transactional
public class PublisherService {

  @Autowired
  private PublisherRepository publisherRepository;

  public List<Publisher> getAllPublishers() {
    return publisherRepository.findAll();
  }

  public Publisher getPublisher(Long id) {
    Optional<Publisher> publisher = publisherRepository.findById(id);
    if (publisher.isPresent()) {
      return publisher.get();
    }
    return null;
  }

  public Publisher createPublisher(PublisherForm publisherForm) {
    Publisher publisher = publisherForm.build();
    publisherRepository.save(publisher);
    return publisher;
  }

  public Publisher updatePublisher(Long id, PublisherForm publisherForm) {
    Optional<Publisher> publisher = publisherRepository.findById(id);
    if (publisher.isPresent()) {
      publisherForm.update(publisher.get());
      return publisher.get();
    }
    return null;
  }

  public boolean canDeletePublisher(Long id) {
    Optional<Publisher> publisher = publisherRepository.findById(id);
    if (publisher.isPresent()) {
      publisherRepository.delete(publisher.get());
      return true;
    }
    return false;
  }
}
