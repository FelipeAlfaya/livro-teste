package br.com.alfaya.livrariaalfa.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alfaya.livrariaalfa.model.Publisher;
import br.com.alfaya.livrariaalfa.model.dto.PublisherDTO;
import br.com.alfaya.livrariaalfa.model.form.PublisherForm;
import br.com.alfaya.livrariaalfa.service.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

  @Autowired
  private PublisherService publisherService;

  @GetMapping
  public List<PublisherDTO> getAllPublishers() {
    List<PublisherDTO> dtoPublishers = new ArrayList<PublisherDTO>();
    List<Publisher> publishers = publisherService.getAllPublishers();
    for (Publisher publisher : publishers) {
      dtoPublishers.add(new PublisherDTO(publisher));
    }
    return dtoPublishers;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PublisherDTO> getPublisher(@PathVariable Long id) {
    Publisher publisher = publisherService.getPublisher(id);
    if (publisher != null) {
      return ResponseEntity.ok(new PublisherDTO(publisher));
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<PublisherDTO> savePublisher(@RequestBody PublisherForm publisherForm,
      UriComponentsBuilder builder) {
    Publisher publisher = publisherService.createPublisher(publisherForm);
    URI uri = builder.path("/Publishers/{id}").buildAndExpand(publisher.getId()).toUri();
    return ResponseEntity.created(uri).body(new PublisherDTO(publisher));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable Long id, @RequestBody PublisherForm publisherForm) {
    Publisher publisher = publisherService.updatePublisher(id, publisherForm);
    if (publisher != null) {
      return ResponseEntity.ok(new PublisherDTO(publisher));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePublisher(@PathVariable Long id) {
    if (publisherService.canDeletePublisher(id)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
