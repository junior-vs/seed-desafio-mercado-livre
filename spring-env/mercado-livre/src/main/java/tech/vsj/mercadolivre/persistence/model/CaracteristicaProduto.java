package tech.vsj.mercadolivre.persistence.model;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
public class CaracteristicaProduto {

  @Id
  @GeneratedValue(generator = "CARACTERISTICAPRODUTO_SEQ_ID", strategy = SEQUENCE)
  @SequenceGenerator(sequenceName = "CARACTERISTICAPRODUTO_SEQ_ID",
      name = "CARACTERISTICAPRODUTO_SEQ_ID", allocationSize = 1)
  private Long id;

  private @NotBlank String nome;

  private @NotBlank String descricao;


  public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao) {
    this.nome = nome;
    this.descricao = descricao;
  }

  @Deprecated
  public CaracteristicaProduto() {
    // Auto-generated constructor stub
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  @Override
  public String toString() {
    return String
        .format("CaracteristicaProduto [id=%s, nome=%s, descricao=%s]", id, nome, descricao);
  }
}
