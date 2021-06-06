package tech.vsj.mercadolivre.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_SEQ_ID")
  @SequenceGenerator(sequenceName = "CATEGORIA_SEQ_ID", name = "CATEGORIA_SEQ_ID",
      allocationSize = 1)
  private Long id;

  @Column(unique = true)
  @NotNull
  private String nomeCategoria;

  @OneToOne
  @JoinColumn(name = "categoria_mae")
  private Categoria categoriaMae;


  public Categoria() {
    // Auto-generated constructor stub
  }

  public Categoria(@NotNull String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }

  public void setCategoriaMae(Categoria categoriaMae) {
    this.categoriaMae = categoriaMae;
  }

  public Long getId() {
    return id;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public Categoria getCategoriaMae() {
    return categoriaMae;
  }

  @Override
  public String toString() {
    return String.format("Categoria [id=%s, nomeCategoria=%s, categoriaMae=%s]", id, nomeCategoria,
        categoriaMae);
  }


}
