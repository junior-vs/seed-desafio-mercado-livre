package tech.vsj.mercadolivre.controllers;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.mercadolivre.domain.dto.ProdutoDTO;
import tech.vsj.mercadolivre.form.ProdutoRequestForm;
import tech.vsj.mercadolivre.persistence.model.Usuario;
import tech.vsj.mercadolivre.persistence.repository.UsuarioRepository;
import tech.vsj.mercadolivre.services.RunAsService;
import tech.vsj.mercadolivre.validation.ProibeCarecteristicasIguaisValidator;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);

  @PersistenceContext
  private EntityManager manager;

  @Autowired
  private RunAsService runas;

  @Autowired
  private UsuarioRepository userRepo;
  
  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(new ProibeCarecteristicasIguaisValidator());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoRequestForm novoProduto) {

    log.info(novoProduto.toString());
    Optional<Usuario> usuarioLogado = userRepo.findByUsername(runas.getCurrentUser().getName());

    var produto = novoProduto.map(manager, usuarioLogado.get());
    manager.persist(produto);
    
    log.info(produto.toString());
    var dto = new ProdutoDTO(produto);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("{id:[0-9]+}")
  public String buscaPorId(@PathVariable Long id) {
    return "ok";
  }

}
