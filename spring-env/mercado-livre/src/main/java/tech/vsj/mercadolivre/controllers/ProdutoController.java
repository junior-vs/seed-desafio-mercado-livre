package tech.vsj.mercadolivre.controllers;

import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.mercadolivre.components.Uploader;
import tech.vsj.mercadolivre.domain.dto.ProdutoDTO;
import tech.vsj.mercadolivre.form.NovasImagensRequestForm;
import tech.vsj.mercadolivre.form.ProdutoRequestForm;
import tech.vsj.mercadolivre.persistence.model.Produto;
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
  private RunAsService loggedAs;

  @Autowired
  private UsuarioRepository userRepo;

  @Autowired
  private Uploader uploaderFake;

  @InitBinder(value = "ProdutoRequestForm")
  public void init(WebDataBinder binder) {
    binder.addValidators(new ProibeCarecteristicasIguaisValidator());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoRequestForm novoProduto) {

    log.info(novoProduto.toString());
    Optional<Usuario> usuarioLogado = userRepo.findByUsername(loggedAs.getCurrentUser().getName());

    var produto = novoProduto.map(manager, usuarioLogado.get());
    manager.persist(produto);

    log.info(produto.toString());
    var dto = new ProdutoDTO(produto);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("{id:[0-9]+}")
  public ResponseEntity<ProdutoDTO> buscaPorId(@PathVariable Long id) {
    Produto find = manager.find(Produto.class, id);

    return ResponseEntity.ok(new ProdutoDTO(find));
  }

  
  @PostMapping(value="{id:[0-9]+}/imagens")  
  @Transactional
  public ResponseEntity<?> cadastraImagem(@PathVariable Long id,
      @Valid NovasImagensRequestForm novasImagens) {
    Optional<Usuario> usuarioLogado = userRepo.findByUsername(loggedAs.getCurrentUser().getName());

    Optional<Produto> produtoFound = buscaProduto(id, usuarioLogado.get());
    if (produtoFound.isEmpty())
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    Set<String> links = uploaderFake.envia(novasImagens.getImagens());
    Produto produto = produtoFound.get();
    produto.associaImagem(links);
    manager.merge(produto);

    var dto = new ProdutoDTO(produto);
    return ResponseEntity.ok(dto);

  }

  private Optional<Produto> buscaProduto(Long id, Usuario usuario) {
    try {
      Produto singleResult = manager
          .createNamedQuery("Produto.buscaPorIdeUsuario", Produto.class).setParameter("id", id)
          .setParameter("usuarioDono", usuario).getSingleResult();
      log.info(singleResult.toString());
      return Optional.of(singleResult);
    } catch (NoResultException e) {
      log.error("Não encontrada a imagens Solicitada");
      return Optional.empty();
    }

  }



}
