package tech.vsj.mercadolivre.form;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonCreator;
import tech.vsj.mercadolivre.persistence.model.CaracteristicaProduto;
import tech.vsj.mercadolivre.persistence.model.Produto;

public class CaracteristicaRequestForm {

  @NotBlank
  private String nome;
  @NotBlank
  private String descricao;  

  @JsonCreator
  public CaracteristicaRequestForm(@NotBlank String nome, @NotBlank String descricao) {
    super();
    this.nome = nome;
    this.descricao = descricao;
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  @Override
  public String toString() {
    return String.format("CaracteristicaRequestForm [nome=%s, descricao=%s]", nome, descricao);
  }

  public CaracteristicaProduto toModel(Produto produto) {
    return new CaracteristicaProduto(this.nome, this.descricao, produto);
  }


}
