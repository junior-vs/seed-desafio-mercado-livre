package tech.vsj.mercadolivre.controllers;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.mercadolivre.form.ComentariosRequestForm;
import tech.vsj.mercadolivre.persistence.model.Comentario;
import tech.vsj.mercadolivre.persistence.model.Produto;
import tech.vsj.mercadolivre.persistence.model.Usuario;
import tech.vsj.mercadolivre.persistence.repository.ProdutoRespository;
import tech.vsj.mercadolivre.persistence.repository.UsuarioRepository;
import tech.vsj.mercadolivre.services.RunAsService;

@RequestMapping("/api/comentarios")
@RestController
public class ComentarioController {

  @Autowired
  private UsuarioRepository userRepo;

  @Autowired
  private RunAsService loggedAs;

  @Autowired
  private ProdutoRespository produtoRespository;

  @PersistenceContext
  private EntityManager manager;



  @PostMapping(value = "/produto/{id:[0-9]+}")
  @Transactional
  public ResponseEntity<?> incluiComentario(@PathVariable Long id,
      @RequestBody ComentariosRequestForm comentario) {

    Optional<Usuario> usuarioLogado = userRepo.findByUsername(loggedAs.getCurrentUser().getName());
    Optional<Produto> found = produtoRespository.findById(id);
    if (found.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Comentario comentarioEntity = comentario.toModel(found.get(), usuarioLogado.orElseThrow());
    manager.persist(comentarioEntity);

    return ResponseEntity.ok(new ComentarioDTO(comentarioEntity));
  }


}
