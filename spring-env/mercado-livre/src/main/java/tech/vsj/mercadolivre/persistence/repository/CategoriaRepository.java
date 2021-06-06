package tech.vsj.mercadolivre.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.vsj.mercadolivre.persistence.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria , Long>{

  Categoria findByNomeCategoria(String categoriaMae);

}
