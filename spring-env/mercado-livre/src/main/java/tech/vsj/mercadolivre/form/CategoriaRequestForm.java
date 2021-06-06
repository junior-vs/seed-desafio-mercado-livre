package tech.vsj.mercadolivre.form;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import tech.vsj.mercadolivre.persistence.model.Categoria;
import tech.vsj.mercadolivre.persistence.repository.CategoriaRepository;
import tech.vsj.mercadolivre.validation.ExistValue;
import tech.vsj.mercadolivre.validation.UniqueValue;

public class CategoriaRequestForm {

  @NotNull
  @UniqueValue(domainClass = Categoria.class, fieldName = "nomeCategoria")
  private String nomeCategoria;

  @ExistValue(domainClass = Categoria.class, fieldName = "nomeCategoria")
  @JsonInclude(Include.NON_NULL)
  private String categoriaMae;

  public CategoriaRequestForm(@NotNull String nomeCategoria, String categoriaMae) {
    super();
    this.nomeCategoria = nomeCategoria;
    this.categoriaMae = categoriaMae;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public String getCategoriaMae() {
    return categoriaMae;
  }

  @Override
  public String toString() {
    return String.format("CategoriaRequestForm [nomeCategoria=%s, categoriaMae=%s]", nomeCategoria,
        categoriaMae);
  }

  public Categoria map(CategoriaRepository repo) {
    var categoria = new Categoria(this.nomeCategoria);
    if (this.categoriaMae != null) {
      var maeCategoria = repo.findByNomeCategoria(this.categoriaMae);
      categoria.setCategoriaMae(maeCategoria);
    }
    return categoria;

  }

}
