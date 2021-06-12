package tech.vsj.mercadolivre.domain.dto;

import tech.vsj.mercadolivre.persistence.model.CaracteristicaProduto;

public class CaracteristicaProdutoDTO {

  private String nome;
  private String descricao;

  public CaracteristicaProdutoDTO(CaracteristicaProduto caracteristicaProduto) {
    this.nome = caracteristicaProduto.getNome();
    this.descricao = caracteristicaProduto.getDescricao();
  }

  public String getNome() {
    return nome;
  }

  public String getDescricao() {
    return descricao;
  }

  @Override
  public String toString() {
    return String.format("CaracteristicaProdutoDTO [nome=%s, descricao=%s]", nome, descricao);
  }



}
