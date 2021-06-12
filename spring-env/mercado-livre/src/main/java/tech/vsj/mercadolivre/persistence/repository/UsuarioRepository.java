package tech.vsj.mercadolivre.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.vsj.mercadolivre.persistence.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByUsername(String username);

}
