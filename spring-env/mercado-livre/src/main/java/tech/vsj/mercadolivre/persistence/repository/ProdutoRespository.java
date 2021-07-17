package tech.vsj.mercadolivre.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.vsj.mercadolivre.persistence.model.Produto;

public interface ProdutoRespository extends JpaRepository<Produto, Long> {

}
