package tech.vsj.mercadolivre.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vsj.mercadolivre.form.UsuarioRequestForm;

/**
 * CDD = 1
 * @author Valdir Junior
 *
 */

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

  @PersistenceContext
  private EntityManager manager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping
  @Transactional
  public ResponseEntity<?> cadastro(@RequestBody @Valid final UsuarioRequestForm novoUsuario) {
    var entity = novoUsuario.map(passwordEncoder);
    manager.persist(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
