package tech.vsj.mercadolivre.controllers;

import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.vsj.mercadolivre.domain.dto.CategoriaDTO;
import tech.vsj.mercadolivre.form.CategoriaRequestForm;
import tech.vsj.mercadolivre.persistence.model.Categoria;
import tech.vsj.mercadolivre.persistence.repository.CategoriaRepository;

@RequestMapping("/api/categorias")
@RestController
public class CatogoriaController {


  private static final Logger log = LoggerFactory.getLogger(CatogoriaController.class);

  @Autowired
  private CategoriaRepository repo;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody @Valid CategoriaRequestForm novoCategoria) {


    var categoria = novoCategoria.map(repo);
    repo.save(categoria);

    var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(categoria.getId()).toUri();

    return ResponseEntity.created(location).body(new CategoriaDTO(categoria));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<Categoria> found = repo.findById(id);
    return ResponseEntity.ok(new CategoriaDTO(found.get()));
  }

}
