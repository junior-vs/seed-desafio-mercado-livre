package tech.vsj.mercadolivre.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import tech.vsj.mercadolivre.persistence.model.Categoria;

public class CategoriaDTO {

  private Long id;
  
  private String nomeCategoria;
  
  @JsonInclude(Include.NON_NULL)
  private CategoriaDTO categoriaMae;

  public CategoriaDTO(Categoria categoria) {
    super();
    this.id = categoria.getId();
    this.nomeCategoria = categoria.getNomeCategoria();
    if (categoria.getCategoriaMae() != null) {
      this.categoriaMae = new CategoriaDTO(categoria.getCategoriaMae());
    }
  }

  public Long getId() {
    return id;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public CategoriaDTO getCategoriaMae() {
    return categoriaMae;
  }

  @Override
  public String toString() {
    return String.format("CategoriaDTO [id=%s, nomeCategoria=%s, categoriaMae=%s]", id,
        nomeCategoria, categoriaMae);
  }

}
